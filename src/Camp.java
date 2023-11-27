
import java.time.*;
import java.util.ArrayList;

public class Camp {
    private String campName;
    private LocalDate campDate;
    private LocalDateTime closingTime;
    private Faculty userGroup;
    private String location;

    private int totalSlots;
    private int campComSlots;
    private int slotsLeft;

    private String description;
    private String staffId;
    private boolean isVisible;

    private ArrayList<Student> listOfStudents = new ArrayList<Student>(totalSlots);
    private ArrayList<Student> listOfCamCom = new ArrayList<Student>(totalSlots);
    private ArrayList<String> listOfRemovedStudents = new ArrayList<String>(totalSlots);

    private ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
    private ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
    
    public Camp(String campName, String staffId){
        this.campName = campName;
        this.staffId = staffId;
        this.isVisible = true; //by default visible is true
    }

    
    /** 
     * @return Camp name
     */
    public String getCampName() {
        return this.campName;
    }
    
    /** 
     * Sets the camp name
     * @param campName
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }
    
    /** 
     * @return campdate 
     */
    public LocalDate getCampDate() {
        return this.campDate;
    }
    
    /** 
     * Sets the camp date
     * @param campDate
     */
    public void setCampDate(LocalDate campDate) {
        this.campDate = campDate;
    }
    
    /** 
     * @return Sign up deadline
     */
    public LocalDateTime getClosingTime() {
        return this.closingTime;
    }
    
    /** 
     * Sets the sign up deadline
     * @param closingTime
     */
    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }
    
    /** 
     * @return Faculty
     */
    public Faculty getUserGroup() {
        return this.userGroup;
    }
    
    /** 
     * Set faculty
     * @param userGroup
     */
    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }
    
    /**
     * @return Location of camp
     */
    public String getLocation() {
        return this.location;
    }
    
    /** 
     * sets location of camp
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }
     
     /** 
      * @return total slots available
      */
     public int getTotalSlots() {
        return this.totalSlots;
     }
     
     /** 
      * sets total slots available
      * @param totalSlots
      */
     public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
     }
    
    /** 
     * @return camp com slots left
     */
    public int getCampComSlots() {
        return this.campComSlots;
    }
    
    /** 
     * set camp com slots left
     * @param campComSlots
     */
    public void setCampComSlots(int campComSlots) {
        this.campComSlots = campComSlots;
    }
    
    /** 
     * @return slots available
     */
    public int getSlotsLeft(){
        return this.slotsLeft;
    }
    
    /** 
     * sets slots left
     * @param slotsleft
     */
    public void setSlotsLeft(int slotsLeft){ //i dont think this will be needed? just total-campcomslots
        this.slotsLeft = slotsLeft;
    }
    
    /** 
     * @return description
     */
    public String getDescription(){
        return this.description;
    }

    /** 
     * Sets the description of camp
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /** 
     * @return StaffId
     */
    public String getStaffId() {
        return this.staffId;
    }
    
    /** 
     * Sets StaffId
     * @param staffId
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    /** 
     * @return visibility of camp
     */
    public boolean isVisible(){
        return isVisible;
    }
    
    /** 
     * Sets visibility of camp
     * @param isVisible
     */
    public void toggleVisibility(boolean isVisible){
        this.isVisible = isVisible;
    }
    
    /** 
     * Adds suggestion to array
     * @param suggestion
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }
    
    /** 
     * @return ArrayList<Suggestion>
     */
    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }
    
    /** 
     * @return int
     */
    public int getCampComTaken(){
        return listOfCamCom.size();
    }
    
    
    /** 
     * Add camp com slots to listofcampcom if there is slots for camp com members
     * @param student
     * @return boolean
     */
    public boolean applyForCampCom(Student student) {
        if (campComSlots > 0) {
            listOfCamCom.add(student);
            campComSlots--;
            return true;
        } else {
            System.out.println("No available slots for Camp Committee.");
            return false;
        }
    }

    
    /** 
     * adds student and decreases slots left
     * @param student
     */
    public void addStudent(Student student){
        if (!listOfStudents.contains(student) && slotsLeft > 0) {
            listOfStudents.add(student);
            slotsLeft--;
        } else {
            System.out.println("Student already added or no slots left.");
        }
    }

    
    /** 
     * add student from database
     * @param student
     * @param student
     * @return int
     */
    public int addStudent(Student student, int number){ //from database
            listOfStudents.add(student);
            return listOfStudents.indexOf(student);
    }

    
    /** 
     * remove student and free a slot
     * @param student
     */
    public void removeStudent(Student student){
        listOfStudents.remove(student);
        listOfRemovedStudents.add(student.getName());
        slotsLeft++;
    }

    
    /** 
     * Finds student within list of student
     * @param student
     * @return Boolean
     */
    public Boolean findStudent(Student student){
        if(listOfStudents.contains(student)){return true;}
        return false;
    }

    
    /** 
     * find removed student from list of removed students
     * @param student
     * @return Boolean
     */
    public Boolean findRemovedStudent(String student){
        if(listOfRemovedStudents.contains(student)){return true;}
        return false;
    }

    
    /** 
     * @return ArrayList<Student>
     */
    public ArrayList<Student> getListStudents(){
        return listOfStudents;
    }

    
    /** 
     * @return ArrayList<Enquiry>
     */
    public ArrayList<Enquiry> getEnquiries(){
        return enquiries;
    }
    

    public void printCampDetails(){
        System.out.println("//////////////////////////////////////////");
        System.out.println("Camp name: " + getCampName()); 
        System.out.println("Location: " + getLocation());
        System.out.println("Total Slots: " + getTotalSlots());
        System.out.println("Slots remaining: " + getSlotsLeft());
        System.out.println("Camp Committee Slots Taken: " + listOfCamCom.size());
        System.out.println("Camp Committee Slots Left: " + getCampComSlots());
        System.out.println("Date: " + getCampDate());
        System.out.println("Closing Date time: " + getClosingTime());
        System.out.println("Visibility: " + isVisible()); 
    }
}
