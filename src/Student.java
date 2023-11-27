
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
    private CampCom campCom;
    
    public Student(String userId, String name, String password, Faculty facultyInfo){
        super(userId, name, password, facultyInfo);
        campCom = new CampCom();
    }

    
    /** 
     * View enquires by camp
     * @param sc
     * @param camp
     */
    public void viewEnquires(Scanner sc, Camp camp){
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        int choice; int x = 0;
        System.out.println("//////////////////////////////////////////");
        for(int i = 0; i < camp.getEnquiries().size(); i++){
            if(camp.getEnquiries().get(i).getOwner().equals(super.getUserId())){
                x++;
                System.out.println(x + ": Enquiry " + x);
                tempArray.add(i);//added the locaton of enquiry to end of list
            }
        }
        System.out.println(++x + ": Add a new enquiry");
        System.out.println(++x + ": Return"); 
        System.out.println("//////////////////////////////////////////");
        choice = Integer.parseInt(sc.nextLine()); //havent debugged if i didnt put proper input
        if(choice == x){return;}
        else if(choice == x-1){ //adding enquiry
            System.out.println("Enter new enquiry: ");
            String newString = sc.nextLine();
            Enquiry newEnquiry = new Enquiry(newString, super.getUserId(), camp.getCampName());
            camp.getEnquiries().add(newEnquiry);
            System.out.println("New enquiry set!");
        }
        else{ //chosen inquiry and we want to view it
            x = tempArray.get(choice-1); //storing the location of enquiry
            System.out.println("//////////////////////////////////////////");
            camp.getEnquiries().get(x).displayEnquiry();
            System.out.println("1: Edit Enquiry");
            System.out.println("2: Delete Enquiry");
            System.out.println("3: Return");
            System.out.println("//////////////////////////////////////////");
            try{choice = Integer.parseInt(sc.nextLine());}
            catch(Exception e){
                System.err.println("Not a valid input");
                choice = 3;
            }
            if(choice == 1){
                System.out.println("Enter a new enquiry: ");
                String newEnquiry = sc.nextLine();
                camp.getEnquiries().get(x).setEnquiryText(newEnquiry);
                System.out.println("New enquiry set!");
            }
            else if(choice == 2){
                camp.getEnquiries().get(x).deleteEnquiry();
                camp.getEnquiries().remove(x);
                System.out.println("Bye bye Enquiry!");
            }
        }
    }

    
    /** 
     * @return CampCom
     */
    public CampCom getCampCom(){
        return this.campCom;
    }

    
    /** 
     * Interface for camp committee members to access about their own camp
     * @param sc
     * @param camp
     */
    public void campComInterface(Scanner sc,Camp camp){
        int choice;
        do{
            System.out.println("//////////////////////////////////////////");
            System.out.println("You are a member of this camp committee");
            System.out.println("1: View camp details");
            System.out.println("2: View enquires");
            System.out.println("3: Reply to enquiries");
            System.out.println("4: Manage Suggestions"); //view, edit, delete
            System.err.println("5: Print camp details");
            System.err.println("6: Return");
            System.out.println("//////////////////////////////////////////");
            try{choice = Integer.parseInt(sc.nextLine());}
            catch(Exception e){
                System.err.println("Not a valid input");
                choice = 0;
                continue;
            }
            switch(choice){
                case 1:
                    getCampCom().viewCampDetails(camp); break;
                case 2:
                    getCampCom().viewAllEnquiries(camp); break;
                case 3:
                    getCampCom().replyEnquiries(sc, camp); break;
                case 4:
                	manageSuggestions(sc, camp); break;
                case 5:
                    System.out.println("How to print?");
                    System.out.println("1: Attendees only");
                    System.out.println("2: Camp committee only");
                    System.out.println("3: Both");
                    int number;
                    try{number = Integer.parseInt(sc.nextLine());}
                    catch(Exception e){
                    System.err.println("Not a valid input");
                    number = 0;
                    continue;
                    }
                    generateCampReport(camp, number);
                    break;
                default:
                    break;
            }
        }while(choice != 6);
    }
    
    
    /** 
     * manage suggestions according to camp
     * @param sc
     * @param camp
     */
    private void manageSuggestions(Scanner sc, Camp camp) {
        System.out.println("Manage suggestions");
        System.out.println("1: Submit Suggestions");
        System.out.println("2: View Suggestions");
        System.out.println("3: Edit Suggestions");
        System.out.println("4: Delete Suggestions");
        System.out.println("5: Return");

        int choice2 = Integer.parseInt(sc.nextLine());
        switch(choice2) {
            case 1:
                campCom.submitSuggestions(sc, camp, this);
                break;
            case 2:
                campCom.viewSuggestions(camp);
                break;
            case 3:
                campCom.editSuggestion(sc, camp);
                break;
            case 4:
                campCom.deleteSuggestion(sc, camp);
                break;
            default:
            	break;
        }
    }

    
    /** 
     * user interface for student
     * @param sc
     * @param database
     */
    public void informationInterface(Scanner sc, Database database){
        int choice;
        do{
        System.out.println("//////////////////////////////////////////");
        System.out.println("Information");
        System.out.println("1: Change password");
        System.out.println("2: View enquiries");
        System.out.println("3: View Suggestions");
        System.err.println("4: Return");
        System.out.println("//////////////////////////////////////////");
        try{choice = Integer.parseInt(sc.nextLine());}
        catch(Exception e){
            System.err.println("Not a valid input");
            choice = 0;
            continue;
        }
        switch (choice) {
            case 1:
                String password1, password2;
                do{
                    System.out.println("Enter new password:");
                    password1 = sc.nextLine();
                    System.out.println("Re-enter password:");
                    password2 = sc.nextLine();
                    if(!password1.equals(password2)){System.out.println("Passwords do not match!");}
                }while(!password1.equals(password2));
                Password encrypt = new Password();
                setPassword(encrypt.hash(password1.toCharArray()));
                System.out.println("New password set!");
                break;
            case 2:
            	viewAllEnquiries(database);
                break;
            case 3:
                if(getCampCom().getIsCampCom()){
                    if(database.getCamp(campCom.getCamp()).getSuggestions().size() == 0){
                        System.out.println("No Suggestions yet");
                    }
                    for(int i = 0; i < database.getCamp(campCom.getCamp()).getSuggestions().size(); i++){
                    database.getCamp(campCom.getCamp()).getSuggestions().get(i).displaySuggestion();
                    }
                }
                else{
                    System.out.println("You are not a member of the camp committee");
                }
                break;
            default:
                System.out.println("default");
                break;
        }
        }while(choice != 4);
    }
    
    
    /** 
     * @param database
     */
    private void viewAllEnquiries(Database database) {
        boolean hasEnquiries = false;
        for (Camp camp : database.getCamps()) {
            for (Enquiry enquiry : camp.getEnquiries()) {
                if (enquiry.getOwner().equals(super.getUserId())) {
                    if (!hasEnquiries) {
                        System.out.println("Your enquiries in all camps:");
                        hasEnquiries = true;
                    }
                    System.out.println("Camp: " + camp.getCampName());
                    enquiry.displayEnquiry();
                }
            }
        }
        if (!hasEnquiries) {
            System.out.println("You have no enquiries in any camp.");
        }
    }
    
    
    /** 
     * add student as campcom member
     * @param camp
     */
    public void submitCampComApplication(Camp camp){
        if(camp.applyForCampCom(this)){
            this.getCampCom().setIsCampCom(true);
            this.getCampCom().setCamp(camp.getCampName());
        }
    }

    
    /** 
     * generate camp report and store into txt file
     * @param camp
     * @param number
     */
    public void generateCampReport(Camp camp, int number){
        try {
        FileWriter myWriter = new FileWriter("student.txt");
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
     * Student interface camp for selected options
     * @param sc
     * @param database
     * @param student
     * @param join
     */
    public void studentInterfaceCamp(Scanner sc, Database database, Student student, Boolean join){
        int choice;
        ArrayList<Camp> tempCamp = new ArrayList<Camp>(10);
        System.out.println("//////////////////////////////////////////");
        if(join.equals(false)){
            //Need to create an array for each student that stores the names of the camps that they have joined.
            database.listOfCamps(tempCamp, student.getFacultyInfo());
            if(tempCamp.size() == 0){System.out.println("No available for your faculty camps"); return;}
            System.out.println("Here is the list of available camps for " + student.getFacultyInfo() + " students."); 
        }
        else{
            database.listOfCamps(tempCamp, student);
            if(tempCamp.size() == 0){System.out.println("You have no joined camps"); 
            System.out.println("//////////////////////////////////////////");
            return;}
            System.out.println("Here is the list of joined camps."); 
        }

        for(int i = 0; i < tempCamp.size(); i++){
            System.out.println(i+1 + ": " + tempCamp.get(i).getCampName());
        }
        System.out.println("//////////////////////////////////////////");
        do{
            choice = scan(sc); //choice chooses the camp we would like to access.
            if(choice > tempCamp.size() || choice < 1){System.out.println("Not a valid option");}
        }while(choice > tempCamp.size() || choice < 1);
        join = tempCamp.get(choice-1).findStudent(student);
        Boolean time = false; //true if contradict timings
        LocalDate campDate = tempCamp.get(choice-1).getCampDate();
        for(int i = 0; i < tempCamp.size(); i++){
            if(i == choice-1){continue;}
            if(tempCamp.get(i).getCampDate().equals(campDate)){time = true;};
        }
        studentInterfaceCampInterface(sc, tempCamp.get(choice-1), student, join, time);
        //once we leave this function returns to student interface
    }

    
    /** 
     * Student view of a selected camp and options to interact with it
     * @param sc
     * @param camp
     * @param student
     * @param join
     * @param time
     */
    public void studentInterfaceCampInterface(Scanner sc, Camp camp, Student student, Boolean join, Boolean time){
        //Specific to the selected camp
        int choice;
        do{
            camp.printCampDetails();
            if(join == true){System.out.println("You have joined this camp");} //or not we need to check with the student obj
            else{
                if(camp.findStudent(student)){System.out.println("You have joined this camp");}
                else{System.out.println("You have not joined this camp");}
            }
            //if student is camp com state if camp com. (can make camp com store the camp he is com of) 
            //print more choices if camp com
            if(join == true){System.out.println("1: Leave this camp");}
            else{System.out.println("1: Join this camp");}
            System.out.println("2: Manage enquiries"); // (have it be able to change own enquires)
            System.out.println("3: Camp Committee tasks");
            System.out.println("4: Return");
            System.out.println("//////////////////////////////////////////");
            choice = scan(sc);
            switch(choice){
                case 1:
                    if(join == false){
                        if(camp.findRemovedStudent(student.getName())){System.out.println("You have left the camp before"); break;} //WTF
                        if(camp.getSlotsLeft() == 0){System.out.println("There are no more slots left in the camp"); break;}
                        if(time == true){System.out.println("You cannot register as the timings clash with another register camp"); break;}
                        camp.addStudent(student);
                        System.out.println("You have joined the Camp!");
                        join = true;
                    }
                    else{
                        if(student.getCampCom().getIsCampCom() == true && student.getCampCom().getCamp().equals(camp.getCampName())){
                            System.out.println("You cannot leave as you are a camp committee member"); break;
                        }
                        System.out.println("Do you want to leave this camp?");
                        System.out.println("You will be unable to rejoin this camp.");
                        System.out.println("1: Leave the camp");
                        System.out.println("2: Return");
                        choice = Integer.parseInt(sc.nextLine());
                        if(choice == 1){
                            camp.removeStudent(student);
                            System.out.println("You have left the Camp.");
                            join = false;
                        }
                    }
                    
                    break;
                case 2:
                    student.viewEnquires(sc, camp); break;
                case 3:
                    if(student.getCampCom().getIsCampCom()){
                        if(student.getCampCom().getCamp().equals(camp.getCampName())){
                            student.campComInterface(sc, camp);
                        }
                        else{
                        System.out.println("You are a Committee member elsewhere."); break;
                        }
                    }
                    else{
                        System.out.println("You are not a member of this camp committee");
                        System.out.println("Submit an application?");
                        System.out.println("1: Yes");
                        System.out.println("2: Return");
                        int temp = Integer.parseInt(sc.nextLine());
                        if(temp == 1){
                        	student.submitCampComApplication(camp);
                            System.out.println("Congratulations you are now a camp committee of this camp!");
                        }
                    }
                    break;
                default:
            }
        }while(choice != 4);
    }
    
    
    /** 
     * Scanner to catch exceptions if not Integer
     * @param e
     * @return int
     */
    public static int scan(Scanner sc){ //catches exceptions
        try{return Integer.parseInt(sc.nextLine());}
        catch(Exception e){
            System.err.println("Not a valid input");
            return 0;
        }
    }
}
