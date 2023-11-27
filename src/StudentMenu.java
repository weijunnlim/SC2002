
import java.util.Scanner;

public class StudentMenu implements MainUI {
    private Student student;
    private Scanner sc;
    private Database database;

    public StudentMenu(Student student, Database database) {
        this.student = student;
        this.sc = new Scanner(System.in);
        this.database = database;
    }

    @Override
    public void displayMenu() {
        System.out.println("Student Interface - Choose an option:");
        System.out.println("1: See available list of camps"); 
        System.out.println("2: List of camps that you have joined"); 
        System.out.println("3: Student information"); 
        System.out.println("4: Return"); 
    }

    @Override
    public int selectOption() {
        int choice = sc.nextInt();
        sc.nextLine();
        handleChoice(choice);
        return choice;
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
            	student.studentInterfaceCamp(sc, database, student, false); 
            	break;
            case 2:
            	student.studentInterfaceCamp(sc, database, student, true); 
            	break;
            case 3:
            	student.informationInterface(sc, database);
                break;
            case 4:
            	System.out.println("Exiting Student Interface.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    @Override
    public void exit() {
        System.out.println("Exiting Student Interface.");
        sc.close();
    }
    
    

    // Additional methods specific to the StudentMenu can be added here
}
