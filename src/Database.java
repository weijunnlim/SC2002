
    import java.util.ArrayList;

    public class Database {
        private static int CAMP_CAPACITY = 10;
        private static int MAX_STUDENT = 100;
        private static int MAX_STAFF = 10;
        private ArrayList<Camp> camps = new ArrayList<Camp>(CAMP_CAPACITY);
        private ArrayList<Student> students = new ArrayList<Student>(MAX_STUDENT);
        private ArrayList<Staff> staffs = new ArrayList<Staff>(MAX_STAFF);

        public Database(){}

        
        /** 
         * Get specified camp
         * @param campName
         * @return Camp
         */
        public Camp getCamp(String campName) {
            for(int i = 0; i < camps.size(); i++){
                if(camps.get(i).getCampName().equals(campName)){return camps.get(i);}
            }
            return null; //need write exceptions
        }

        
        /** 
         * add camp to database
         * @param camp
         */
        public void addCamp(Camp camp) {
            camps.add(camp);
        }

        
        /** 
         * remove camp from database
         * @param camp
         */
        public void removeCamp(Camp camp) {
            for(int i = 0; i < camps.size(); i++){
                if(camps.get(i).getCampName().equals(camp.getCampName())){
                    camps.remove(i);
                    return;
                }
            }
            System.out.println("No camp found");
        }
        
        /** 
         * View all camps that are availabe
         */
        public void viewCampList() {
        	if (camps.isEmpty()) {
        		System.out.println("No camp found");
        		return;
        	}
            System.out.println("All camps: ");
            for (int i = 0; i < camps.size(); i++) {
                if (camps.get(i) != null) {
                    System.out.println(i + 1 + ": " + camps.get(i).getCampName());
                }
            }
        }
        
        /** 
         * Get specified student from database
         * @param studentName
         * @return Student
         */
        public Student getStudent(String studentName) {
            for(int i = 0; i < students.size(); i++){
                if(students.get(i).getUserId().equals(studentName)){return students.get(i);}
            }
            return null; //need write exceptions
        }

        
        /** 
         * add student to database
         * @param student
         */
        public void addStudent(Student student) {
            students.add(student);
        }

        
        /** 
         * remove student from database
         * @param student
         */
        public void removeStudent(Student student) {
            for(int i = 0; i < students.size(); i++){
                if(students.get(i).getUserId().equals(student.getUserId())){
                    students.remove(i);
                    return;
                }
            }
            System.out.println("No student found");
        }

        
        /** 
         * get specified staff from database
         * @param staffName
         * @return Staff
         */
        public Staff getStaff(String staffName) {
            for(int i = 0; i < staffs.size(); i++){
                if(staffs.get(i).getUserId().equals(staffName)){return staffs.get(i);}
            }
            return null; //need write exceptions
        }

        
        /** 
         * adds staff to database
         * @param staff
         */
        public void addStaff(Staff staff) {
            staffs.add(staff);
        }

        
        /** 
         * remove staff from database
         * @param staff
         */
        public void removeStaff(Staff staff) {
            for(int i = 0; i < staffs.size(); i++){
                if(staffs.get(i).getUserId().equals(staff.getUserId())){
                    staffs.remove(i);
                    return;
                }
            }
            System.out.println("No staff found");
        }

        /**
         * @param inputCamp
         * @param faculty
         * Runs through the camps to return camps belonging to a sepcific faculty and returns an arraylist
        */
        public ArrayList<Camp> listOfCamps(ArrayList<Camp> inputCamp, Faculty faculty){
            for(int i = 0; i < camps.size(); i++){
                if(camps.get(i).getUserGroup().equals(faculty)&& camps.get(i).isVisible()){inputCamp.add(camps.get(i));}
            }
            return inputCamp;
        }
        /**
         * @param inputCamp
         * @param student
         * Runs through the camps stored in database and checks for if joined camps present,
         * then it puts the camp into arraylist so that we have the array of joined camps
         */
        public ArrayList<Camp> listOfCamps(ArrayList<Camp> inputCamp, Student student){
            for(int i = 0; i < camps.size(); i++){
                if(camps.get(i).findStudent(student)){inputCamp.add(camps.get(i));};
            }
            return inputCamp;
        }

        
        /** 
         * @return ArrayList<Camp>
         */
        public ArrayList<Camp> getCamps() {
            return this.camps;
        }

        
        /** 
         * @return ArrayList<Student>
         */
        public ArrayList<Student> getStudents() {
            return this.students;
        }

        
        /** 
         * @return ArrayList<Staff>
         */
        public ArrayList<Staff> getStaffs() {
            return this.staffs;
        }
    }
