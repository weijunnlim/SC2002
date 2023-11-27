

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeParseException;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>();

    public Staff(String userId, String name, String password, Faculty facultyInfo){
        super(userId, name, password, facultyInfo);
        this.campsCreated = new ArrayList<Camp>();
    }

    
    /** 
     * Create a camp with all variables filled in
     * @param sc
     * @param database
     * @return Camp
     */
    public Camp createCamp(Scanner sc, Database database) {
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();

        // Check if a camp with the same name already exists
        for (Camp existingCamp : campsCreated) {
            if (existingCamp.getCampName().equalsIgnoreCase(campName)) {
                System.out.println("A camp with this name already exists. Please choose a different name.");
                return null; // Return null to indicate that no new camp was created
            }
        }

        Camp newCamp = new Camp(campName, super.getUserId());
        campsCreated.add(newCamp);
        database.addCamp(newCamp);
        
        boolean validDate = false;
        while (!validDate) {
            System.out.println("Enter new camp date (yyyy-MM-dd): ");
            String dateStr = sc.nextLine();
            try {
                LocalDate newDate = LocalDate.parse(dateStr);
                newCamp.setCampDate(newDate);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
            }
        }

        boolean validTime = false;
        while (!validTime) {
            System.out.println("Enter new closing time (yyyy-MM-ddTHH:mm): ");
            String timeStr = sc.nextLine();
            try {
                LocalDateTime newClosingTime = LocalDateTime.parse(timeStr);
                newCamp.setClosingTime(newClosingTime);
                validTime = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format.");
            }
        }

        boolean validGroup = false;
        while (!validGroup) {
            System.out.println("Enter new user group (e.g., SCSE, LKC): ");
            String facultyName = sc.nextLine().toUpperCase();
            try {
                Faculty newFaculty = Faculty.valueOf(facultyName);
                newCamp.setUserGroup(newFaculty);
                validGroup = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid faculty name.");
            }
        }

        
        System.out.println("Enter new location: ");
        newCamp.setLocation(sc.nextLine());
        
        System.out.println("Enter new total slots: ");
        newCamp.setTotalSlots(sc.nextInt());
        newCamp.setSlotsLeft(newCamp.getTotalSlots());
        
        System.out.println("Enter new camp committee slots: ");
        newCamp.setCampComSlots(sc.nextInt());sc.nextLine();
        
        System.out.println("Enter new description: ");
        newCamp.setDescription(sc.nextLine());
        
        System.out.println("Camp created successfully!");

        return newCamp;
    }

    
    /** 
     * add a camp to the created camps by staff
     * @param camp
     */
    public void addCamp(Camp camp){
        campsCreated.add(camp);
    }

    /** 
     * View camps created by staff
     */
    public void viewCampList(){
        if (campsCreated.isEmpty()){
            System.out.println("No camps available");
            return;
        }
    	System.out.println("All camps: ");
        for (int i = 0; i<campsCreated.size(); i++){
            System.out.println((i+1) + ": " + campsCreated.get(i).getCampName());
        }
    }

    
    /** 
     * delete camp from database
     * @param campName
     * @param database
     */
    public void deleteCamp(String campName, Database database) {
        Iterator<Camp> iterator = campsCreated.iterator();
        while (iterator.hasNext()) {
            Camp camp = iterator.next();
            if (campName.equals(camp.getCampName())) {
				database.removeCamp(camp);
                iterator.remove();
                System.out.println("Camp deleted: " + campName);
                return;
            }
        }
        System.out.println("Camp not found: " + campName);
    }

    
    /** 
     * edit camp in campsCreated
     * @param sc
     */
    public void editCamp(Scanner sc) {
        System.out.println("Enter the name of the camp you want to edit: ");
        String campName = sc.nextLine();

        // Find the camp
        Camp selectedCamp = null;
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                selectedCamp = camp;
                break;
            }
        }

        if (selectedCamp == null) {
            System.out.println("Camp not found.");
            return;
        }

        // Edit options
        System.out.println("Select what you want to edit: ");
        System.out.println("1. Camp Name\n2. Camp Date\n3. Closing Time\n4. User Group\n5. Location\n6. Total Slots\n7. Camp Committee Slots\n8. Description");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline

        switch (choice) {
            case 1:
                System.out.println("Enter new camp name: ");
                selectedCamp.setCampName(sc.nextLine());
                break;
            case 2:
                System.out.println("Enter new camp date (yyyy-MM-dd): ");
                String dateStr = sc.nextLine();
                try {
                    LocalDate newDate = LocalDate.parse(dateStr);
                    selectedCamp.setCampDate(newDate);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format.");
                }
                break;
            case 3:
                System.out.println("Enter new closing time (yyyy-MM-ddTHH:mm): ");
                String timeStr = sc.nextLine();
                try {
                    LocalDateTime newClosingTime = LocalDateTime.parse(timeStr);
                    selectedCamp.setClosingTime(newClosingTime);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date and time format.");
                }
                break;
            case 4:
                System.out.println("Enter new user group (e.g., SCSE, LKC): ");
                String facultyName = sc.nextLine().toUpperCase();
                try {
                    Faculty newFaculty = Faculty.valueOf(facultyName);
                    selectedCamp.setUserGroup(newFaculty);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid faculty name.");
                }
            break;
            case 5:
                System.out.println("Enter new location: ");
                selectedCamp.setLocation(sc.nextLine());
                break;
            case 6:
                System.out.println("Enter new total slots: ");
                selectedCamp.setTotalSlots(sc.nextInt());
                sc.nextLine(); // Consume the leftover newline
                break;
            case 7:
                System.out.println("Enter new camp committee slots: ");
                selectedCamp.setCampComSlots(sc.nextInt());
                sc.nextLine(); // Consume the leftover newline
                break;
            case 8:
                System.out.println("Enter new description: ");
                selectedCamp.setDescription(sc.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        selectedCamp.setSlotsLeft(selectedCamp.getTotalSlots() - selectedCamp.getCampComSlots());
        System.out.println("Camp updated successfully!");
    } //can edit campName, campDate, closingTime, userGroup, location, totalSlots, campComSlots, description
    
    
    /** 
     * Toggle visibility of a camp
     * @param sc
     * @param campName
     */
    public void toggleCampVisibility(Scanner sc, String campName) {
        for (Camp camp : campsCreated) {
            if (campName.equals(camp.getCampName())) {
                System.out.println("Do you want to toggle visibility on/off?");
                System.out.println("1: On");
                System.out.println("2: Off");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the leftover newline

                boolean visibility;
                if (choice == 1) visibility = true;
                else visibility = false;      // If choice is 1, visibility is true (On), otherwise false (Off)

                camp.toggleVisibility(visibility);
                String visibilityStatus = visibility ? "on" : "off";
                System.out.println("Visibility toggled " + visibilityStatus + " for " + campName);
                return;
            }
        }
        System.out.println("Camp not found: " + campName);
    } 
    
     
     /** 
      * Filter and display enquiry based on camp
      * @param campName
      * @return ArrayList<Enquiry>
      */
     //created new method to display enquiry based on campname
    private ArrayList<Enquiry> filterAndDisplayEnquiries(String campName) {
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                ArrayList<Enquiry> enquiries = camp.getEnquiries();
                ArrayList<Enquiry> filteredEnquiries = new ArrayList<>();
                
                for (Enquiry enquiry : enquiries) {
                    if (enquiry.getCampName().equals(campName)) {
                        filteredEnquiries.add(enquiry);
                    }
                }

                if (filteredEnquiries.isEmpty()) {
                    System.out.println("No enquiries available for camp: " + campName);
                } else {
                	System.out.println("Enquiries for camp: " + campName);
                	for (Enquiry enquiry : filteredEnquiries) {
                		enquiry.displayEnquiry();
                	}
                }
                return filteredEnquiries;
            }
        }
        System.out.println("Camp not found: " + campName);
        return new ArrayList<>(); //Return empty list if camp not found
    }
    
    
    /** 
     * view Enquiries
     * @param campName
     */
    public void viewEnquiries(String campName) {
        filterAndDisplayEnquiries(campName);
    }

    
    /** 
     * reply enquiry based on camp
     * @param sc
     * @param campName
     */
    public void replyEnquiries(Scanner sc, String campName) {
        ArrayList<Enquiry> filteredEnquiries = filterAndDisplayEnquiries(campName);

        if (filteredEnquiries.isEmpty()) {
            return;
        }

        System.out.println("Enter the number of the enquiry you wish to reply to:");
        int enquiryNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (enquiryNumber < 1 || enquiryNumber > filteredEnquiries.size()) {
            System.out.println("Invalid enquiry number.");
            return;
        }

        Enquiry selectedEnquiry = filteredEnquiries.get(enquiryNumber - 1);

        System.out.println("Enter your reply:");
        String reply = sc.nextLine();

        selectedEnquiry.setReplyText(reply);
        selectedEnquiry.setStatus(true); // Set the status as replied

        System.out.println("Reply sent successfully.");
        System.out.println("Updated Enquiry:");
        selectedEnquiry.displayEnquiry();
    }
    
    
    /** 
     * Filter suggestions based on camp
     * @param campName
     * @return ArrayList<Suggestion>
     */
    //created new method to display suggestion based on campname
    private ArrayList<Suggestion> filterAndDisplaySuggestions(String campName) {
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                ArrayList<Suggestion> suggestions = camp.getSuggestions();
                ArrayList<Suggestion> filteredSuggestions = new ArrayList<>();

                for (Suggestion suggestion : suggestions) {
                    if (suggestion.getCampName().equals(campName)) {
                        filteredSuggestions.add(suggestion);
                    }
                }

                if (filteredSuggestions.isEmpty()) {
                    System.out.println("No suggestions available for this camp.");
                } else {
                    System.out.println("Suggestions for camp: " + campName);
                    for (Suggestion suggestion : filteredSuggestions) {
                        suggestion.displaySuggestion();
                    }
                }
                return filteredSuggestions;
            }
        }
        System.out.println("Camp not found: " + campName);
        return new ArrayList<>(); // Return empty list if camp not found
    }

    
    /** 
     * View suggestions
     * @param campName
     */
    public void viewSuggestions(String campName) {
        filterAndDisplaySuggestions(campName);
    }

    
    /** 
     * Reply to the suggestion based on camp
     * @param sc
     * @param campName
     * @param database
     */
    public void replySuggestions(Scanner sc, String campName, Database database) {
        ArrayList<Suggestion> filteredSuggestions = filterAndDisplaySuggestions(campName);
        
        if (filteredSuggestions.isEmpty()) {
            return;
        }

        System.out.println("Enter the index of the suggestion to reply:");
        int suggestionNumber = sc.nextInt() - 1;
        sc.nextLine();

        if (suggestionNumber < 0 || suggestionNumber >= filteredSuggestions.size()) {
            System.out.println("Invalid suggestion index.");
            return;
        }

        System.out.println("Do you want to approve or reject the suggestion?");
        System.out.println("1: Approve");
        System.out.println("2: Reject");
        int choice = sc.nextInt();
        sc.nextLine();

        Suggestion suggestion = filteredSuggestions.get(suggestionNumber);

        if (choice == 1) {
            suggestion.setStatus(true);
            incrementCampComPoints(suggestion.getOwner(), database);//suggestion approved, increase camp com member points by 1
        } else if (choice == 2) {
            suggestion.setStatus(false);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        // Display the updated suggestion
        System.out.println("Updated Suggestion:");
        suggestion.displaySuggestion();
    }
    
    
    /** 
     * add points to camp com member
     * @param ownerName
     * @param database
     */
    private void incrementCampComPoints(String ownerName, Database database) {
        // Find the student (CampCom member) by owner name and increment their points
        for (Student student : database.getStudents()) {
            if (student.getName().equals(ownerName) && student.getCampCom().getIsCampCom()) {
                CampCom campCom = student.getCampCom();
                campCom.setPoints(campCom.getPoints() + 1);
                break;
            }
        }
    }
    
    
    /** 
     * Generate camp report by choice and add to a txt file
     * @param camp
     * @param number
     */
    public void generateCampReport(Camp camp, int number){
        try {
        FileWriter myWriter = new FileWriter("staff.txt");
        myWriter.write("Camp name: " + camp.getCampName());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Location: " + camp.getLocation());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Total Slots: " + camp.getTotalSlots());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Slots remaining: " + camp.getSlotsLeft());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Camp Committee Slots Taken: " + camp.getCampComTaken());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Camp Committee Slots Left: " + camp.getCampComSlots());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Date: " + camp.getCampDate());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Closing Date time: " + camp.getClosingTime());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Visibility: " + camp.isVisible());myWriter.write(System.getProperty( "line.separator" ));
        switch(number){
            case 3:
                for(int i = 0; i < camp.getListStudents().size(); i++){
                    Student tempStudent = camp.getListStudents().get(i);
                    if(tempStudent.getCampCom().getIsCampCom()){
                        myWriter.write(tempStudent.getName() + " is a camp committee member");myWriter.write(System.getProperty( "line.separator" ));
                    }
                    else{
                        myWriter.write(tempStudent.getName() + " is a participant");myWriter.write(System.getProperty( "line.separator" ));
                    }
                }
                break;
            case 2:
                for(int i = 0; i < camp.getListStudents().size(); i++){
                    Student tempStudent = camp.getListStudents().get(i);
                    if(tempStudent.getCampCom().getIsCampCom()){
                        myWriter.write(tempStudent.getName() + " is a camp committee member");myWriter.write(System.getProperty( "line.separator" ));
                    }
                }
                break;
            case 1:
                for(int i = 0; i < camp.getListStudents().size(); i++){
                    myWriter.write(camp.getListStudents().get(i).getName() + " is a participant");myWriter.write(System.getProperty( "line.separator" ));
                }
                break;
            default:
                System.out.println("Invalid choice.");
                myWriter.close();
                return;
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    } //generate list of students (attendees/campcom)


    
    /** 
     * Generate performance report by camp com members and add to txt file
     * @param camp
     */
    public void generatePerformanceReport(Camp camp){
        try {
        FileWriter myWriter = new FileWriter("performanceReport.txt");
        myWriter.write("Camp name: " + camp.getCampName());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Camp Committee Slots Taken: " + camp.getCampComTaken());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Camp Committee Slots Left: " + camp.getCampComSlots());myWriter.write(System.getProperty( "line.separator" ));
        for(int i = 0; i < camp.getListStudents().size(); i++){
            Student tempStudent = camp.getListStudents().get(i);
            if(tempStudent.getCampCom().getIsCampCom()){
                myWriter.write(tempStudent.getName() + " is a camp committee member with " + 
                tempStudent.getCampCom().getPoints() + " points.");
                myWriter.write(System.getProperty( "line.separator" ));
            }
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    } //generate performance report of campcom members

}



