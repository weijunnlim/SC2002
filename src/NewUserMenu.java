

import java.util.Scanner;

public class NewUserMenu implements MainUI {
    private Scanner sc;
    private Database database;
    private Password encrypt;

    public NewUserMenu(Database database) {
        this.sc = new Scanner(System.in);
        this.database = database;
        this.encrypt = new Password();
    }

    @Override
    public void displayMenu() {
        System.out.println("Create New User Page");
        System.out.println("1: New Student");
        System.out.println("2: New Staff");
        System.out.println("3: Return");
    }

    
    /** 
     * @return int
     */
    @Override
    public int selectOption() {
        int choice = sc.nextInt();
        sc.nextLine(); 
        handleChoice(choice);
        return choice;
    }

    
    /** 
     * @param choice
     */
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createNewStudent();
                break;
            case 2:
                createNewStaff();
                break;
            case 3:
                System.out.println("Exiting new user interface.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void createNewStudent() {
        System.out.println("Name:");
        String name = sc.nextLine();
        System.out.println("Username:");
        String username = sc.nextLine();
        String password1, password2;
        do{
            System.out.println("Enter new password:");
            password1 = sc.nextLine();
            System.out.println("Re-enter password:");
            password2 = sc.nextLine();
            if(!password1.equals(password2)){System.out.println("Passwords do not match!");}
        }while(!password1.equals(password2));
        System.out.println("Faculty:"); //Do another function for gods sake
        String faculty = sc.nextLine();
        Student tempStudent = new Student(username, name, encrypt.hash(password1.toCharArray()), Faculty.valueOf(faculty));
        database.addStudent(tempStudent);
        System.out.println("Student Creation Successful!");
    }

    private void createNewStaff() {
        System.out.println("Name:");
        String name = sc.nextLine();
        System.out.println("Username:");
        String username = sc.nextLine();
        String password1, password2;
        do{
            System.out.println("Enter new password:");
            password1 = sc.nextLine();
            System.out.println("Re-enter password:");
            password2 = sc.nextLine();
            if(!password1.equals(password2)){System.out.println("Passwords do not match!");}
        }while(!password1.equals(password2));
        System.out.println("Faculty:"); //Do another function for gods sake
        String faculty = sc.nextLine();
        Staff tempStaff = new Staff(username, name, encrypt.hash(password1.toCharArray()), Faculty.valueOf(faculty));
        database.addStaff(tempStaff);
        System.out.println("Staff Creation Successful!");
    }

    @Override
    public void exit() {
        System.out.println("Exiting New User Interface.");
        sc.close();
    }
}
