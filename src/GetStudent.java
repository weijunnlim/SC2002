
// Java Program to Extract Content from a Excel sheet

// As we are reading the excel file, java.io package is
// compulsorily required
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.time.*;

// Below imports are required to access Apache POI
// The usermodel package maps HSSF low level structures to
// familiar workbook/sheet model
// org.apache.poi.hssf.usermodel
// But we are using higher excel formats hence,
// org.apache.poi.ss.usermodel is used To determine the type
// of cell content
import org.apache.poi.ss.usermodel.Cell;
// each and every row of excel is taken and stored in this
// row format
import org.apache.poi.ss.usermodel.Row;

// excel sheet is read in this sheet format
import org.apache.poi.ss.usermodel.Sheet;

// excel Workbook is read in this Workbook format
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
// XSSFWorkbook denotes the API is for working with Excel
// 2007 and later.
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// POJO class having 3 fields matching with the given excel
// class to assign the cell value once it is getting done to
// read from excel sheet It can be String/Boolean/Numeric
public class GetStudent {
	
	/** 
	 * Checks the type of data in excel cell
	 * @param cell
	 * @return Object
	 */
	private Object getCellValue(Cell cell)
	{
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();

		case BOOLEAN:
			return cell.getBooleanCellValue();

		case NUMERIC:
			return cell.getNumericCellValue();
			
		default: break;
		}
		System.out.println(cell.getDateCellValue());

		return null;
	}
	
	/** 
	 * 	Read the excel sheet contents for students and get the contents in a list
	 * @param excelFilePath
	 * @return List<Student>
	 * @throws IOException
	 */
	public List<Student>
	readBooksFromExcelFileStudent(String excelFilePath)
		throws IOException
	{
		List<Student> listStudent
			= new ArrayList<Student>();
		FileInputStream inputStream
			= new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator
				= nextRow.cellIterator();
            Student stu = new Student("userId","name", "password",Faculty.SCSE);


			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				// System.out.println("--------------------------");
				// System.out.println(nextCell);
				// System.out.println("--------------------------");
				int columnIndex = nextCell.getColumnIndex();
				// System.out.println(columnIndex);
				switch (columnIndex) {
				case 0:
					stu.setName((String)getCellValue(nextCell));
					break;
				case 1:
                    String result = (String)getCellValue(nextCell);
					stu.setUserId(
						result.split("@",2)[0]);
					break;
				case 2:
					stu.setFacultyInfo
						(Faculty.valueOf((String)getCellValue(nextCell)));
					break;
                case 3:
                    //this would be the password
                    stu.setPassword(
                        (String)getCellValue(nextCell));
					// Password encrypt = new Password();
					// String password = (String)getCellValue(nextCell);
					// char[] newpass = password.toCharArray();
					// password = encrypt.hash(newpass);
					// stu.setPassword(password);
                    break;
				default: System.out.println("fail"); break;
				}
			}
			listStudent.add(stu);
		}
        workbook.close();
		inputStream.close();

		return listStudent;
	}

    
	/** 
	 * Read the excel sheet contents for staff and get the contents in a list
	 * @param excelFilePath
	 * @return List<Staff>
	 * @throws IOException
	 */
	public List<Staff>
	readBooksFromExcelFileStaff(String excelFilePath)
		throws IOException
	{
		List<Staff> listStaff
			= new ArrayList<Staff>();
		FileInputStream inputStream
			= new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(1);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator
				= nextRow.cellIterator();
            Staff stu = new Staff("userId","name","password",Faculty.SCSE);

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					stu.setName((String)getCellValue(nextCell));
					break;
				case 1:
                 	String result = (String)getCellValue(nextCell);
					stu.setUserId(
						result.split("@",2)[0]);
					break;
				case 2:
					stu.setFacultyInfo
						(Faculty.valueOf((String)getCellValue(nextCell)));
					break;
                case 3:
                    //this would be the password
                    stu.setPassword(
                        (String)getCellValue(nextCell));
					// Password encrypt = new Password();
					// String password = (String)getCellValue(nextCell);
					// stu.setPassword(encrypt.hash(password.toCharArray()));
                    break;
				default: System.out.println("fail"); break;
				}
			}
			listStaff.add(stu);
		}
        workbook.close();
		inputStream.close();

		return listStaff;
	}

    
	/** 
	 * Read the excel sheet contents for camps and get the contents in a list
	 * @param excelFilePath
	 * @return List<Camp>
	 * @throws IOException
	 */
	public List<Camp>
	readBooksFromExcelFileCamp(String excelFilePath)
		throws IOException
	{
		List<Camp> listCamp
			= new ArrayList<Camp>();
		FileInputStream inputStream
			= new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(2);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator
				= nextRow.cellIterator();
            Camp camp = new Camp("default", "userID");

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0: //Camp name
                    camp.setCampName(
                        (String)getCellValue(nextCell));
					break;
				case 1: //StaffId
					camp.setStaffId(
                        (String)getCellValue(nextCell));
					break;
				case 2: //faculty
					camp.setUserGroup(
						Faculty.valueOf((String)getCellValue(nextCell)));
					break;
                case 3: //Total Slots
					try{
						double cellValue = (Double)getCellValue(nextCell);
                    	int number = (int) cellValue;
						camp.setTotalSlots(number);}
					catch(Exception e){                    
						double cellValue = Double.parseDouble((String)getCellValue(nextCell));
						int number = (int) cellValue;
						camp.setTotalSlots(number);}
                    break;
                case 4: //Slots left
					try{
						double cellValue = (Double)getCellValue(nextCell);
                    	int number = (int) cellValue;
						camp.setSlotsLeft(number);}
					catch(Exception e){                    
						double cellValue = Double.parseDouble((String)getCellValue(nextCell));
						int number = (int) cellValue;
						camp.setSlotsLeft(number);}
                    break;
                case 5: //Date
                    // SerialDate sd = new SerialDate(Double.parseDouble((String)getCellValue(nextCell)));
                    // LocalDate ld = LocalDate.ofEpochDay(sd.toEpochDays());
					//if(((String)getCellValue(nextCell)).equals("null")){break;}
					try{                    
						camp.setCampDate(
						LocalDate.parse((String)getCellValue(nextCell)));
					}
					catch(Exception e){
						SerialDate sd = new SerialDate((Double)getCellValue(nextCell));
						LocalDate ld = LocalDate.ofEpochDay(sd.toEpochDays());
						camp.setCampDate(
						ld);
					}
                    break;
                case 6: //Closing time
                    // sd = new SerialDate(Double.parseDouble((String)getCellValue(nextCell)));
                    // LocalDateTime ldt = LocalDateTime.of(
                    // LocalDate.ofEpochDay(sd.toEpochDays()),
                    // LocalTime.ofSecondOfDay(sd.toDaySeconds()));
					if(((String)getCellValue(nextCell)).equals("null")){break;}
                    camp.setClosingTime(
						LocalDateTime.parse((String)getCellValue(nextCell)));
                    break;
                case 7: //Location
                    camp.setLocation(
                        (String)getCellValue(nextCell));
                    break;
                case 8: //CampCom slots
					try{
						double cellValue = (Double)getCellValue(nextCell);
                    	int number = (int) cellValue;
						camp.setCampComSlots(number);}
					catch(Exception e){                    
						double cellValue = Double.parseDouble((String)getCellValue(nextCell));
						int number = (int) cellValue;
						camp.setCampComSlots(number);}
                    break;
                case 9: //Description
                    camp.setDescription(
                        (String)getCellValue(nextCell));
                    break;
                case 10:
                    camp.toggleVisibility(
                        Boolean.valueOf((String)getCellValue(nextCell)));
                    break;
				default: System.out.println("fail"); break;
				}
			}
			listCamp.add(camp);
		}
        workbook.close();
		inputStream.close();

		return listCamp;
	}
	
	
	/** 
	 * Read the excel sheet contents for students in camps and adds the students to their camps.
	 * @param excelFilePath
	 * @param database
	 * @throws IOException
	 */
	public void
	readBooksFromExcelFileCampAttendees(String excelFilePath, Database database)
		throws IOException
	{
		FileInputStream inputStream
			= new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(3);
		Iterator<Row> iterator = firstSheet.iterator();
		int pos = 0;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator
				= nextRow.cellIterator();
			int studentPos = 0;
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				Camp tempCamp = database.getCamps().get(pos);
				Student tempStudent;
				switch (columnIndex%2) {
				case 0: //Student name
					tempStudent = database.getStudent((String)getCellValue(nextCell));
					studentPos = tempCamp.addStudent(tempStudent, 1); //add student without changing space in the camp
					break;
				case 1: //Is camp com
					try{
						if(Boolean.valueOf((String)getCellValue(nextCell))){
							tempCamp.getListStudents().get(studentPos).submitCampComApplication(tempCamp);
							tempCamp.setCampComSlots(tempCamp.getCampComSlots()+1);
						}
						else{
							tempCamp.getListStudents().get(studentPos).getCampCom().setIsCampCom(
							false);
						}
					}
					catch(Exception e){
						if((Boolean)getCellValue(nextCell)){
							tempCamp.getListStudents().get(studentPos).submitCampComApplication(tempCamp);
							tempCamp.setCampComSlots(tempCamp.getCampComSlots()+1);
						}
						else{
							tempCamp.getListStudents().get(studentPos).getCampCom().setIsCampCom(
							false);
						}
					}
					// System.out.println(tempCamp.getListStudents().get(studentPos).getName());
					// System.out.println(tempCamp.getListStudents().get(studentPos).getCampCom().getIsCampCom());
					// System.out.println(tempCamp.getListStudents().get(studentPos).getCampCom().getCamp());
					break;
				default: System.out.println("fail"); break;
				}
			}
			pos++;
		}
        workbook.close();
		inputStream.close();

	}

	
	/** 
	 * Code to store the data from database into specified file
	 * @param database
	 * @param pathName
	 * @throws IOException
	 */
	public void storeWorkbook(Database database, String pathName)
			throws IOException
	{

		XSSFWorkbook workbook = new XSSFWorkbook(); 
  
        // spreadsheet object 
        XSSFSheet spreadsheet1 
            = workbook.createSheet(" Student Data "); 
        XSSFSheet spreadsheet2 
            = workbook.createSheet(" Staff Data "); 
		XSSFSheet spreadsheet3 
            = workbook.createSheet(" Camp Data "); 
		XSSFSheet spreadsheet4 
            = workbook.createSheet(" Individual camp data "); 
        // creating a row object 
        XSSFRow row; 
  
        // This data needs to be written (Object[]) 
        Map<String, Object[]> studentData 
            = new TreeMap<String, Object[]>(); 
		
		for(int i = 0; i < database.getStudents().size();i++){
			String userId = database.getStudents().get(i).getUserId();
			String name = database.getStudents().get(i).getName();
			String password = database.getStudents().get(i).getPassword();
			String faculty = database.getStudents().get(i).getFacultyInfo().toString();
			studentData.put(String.valueOf(i), new Object[]{name, userId+"@e.ntu.edu.sg", faculty,password});
		}

        Map<String, Object[]> staffData 
            = new TreeMap<String, Object[]>(); 
		
		for(int i = 0; i < database.getStaffs().size();i++){
			String userId = database.getStaffs().get(i).getUserId();
			String name = database.getStaffs().get(i).getName();
			String password = database.getStaffs().get(i).getPassword();
			String faculty = database.getStaffs().get(i).getFacultyInfo().toString();
			staffData.put(String.valueOf(i), new Object[]{name, userId+"@e.ntu.edu.sg", faculty,password});
		}

		Map<String, Object[]> campData 
            = new TreeMap<String, Object[]>(); 
		
		for(int i = 0; i < database.getCamps().size();i++){ //havent finished
			String campName = database.getCamps().get(i).getCampName();
			String creator = database.getCamps().get(i).getStaffId();
			String faculty = database.getCamps().get(i).getUserGroup().toString();
			String totalSlots = String.valueOf(database.getCamps().get(i).getTotalSlots());
			String slotsLeft = String.valueOf(database.getCamps().get(i).getSlotsLeft());
			String date = String.valueOf(database.getCamps().get(i).getCampDate());
			String endDate = String.valueOf(database.getCamps().get(i).getClosingTime());
			String location = database.getCamps().get(i).getLocation();
			String campComSlots = String.valueOf(database.getCamps().get(i).getCampComSlots());
			String description = database.getCamps().get(i).getDescription();
			String visibility = String.valueOf(database.getCamps().get(i).isVisible());
			campData.put(String.valueOf(i), new Object[]{campName, creator, faculty, totalSlots, 
				slotsLeft, date, endDate, location, campComSlots, description, visibility});
		}

        Set<String> keyid1 = studentData.keySet(); 
  
        int rowid = 0; 
  
        // writing the data into the sheets... 
  
        for (String key : keyid1) { 
  
            row = spreadsheet1.createRow(rowid++); 
            Object[] objectArr = studentData.get(key); 
            int cellid = 0; 
  
            for (Object obj : objectArr) { 
                Cell cell = row.createCell(cellid++); 
                cell.setCellValue((String)obj); 
            } 
        } 

		Set<String> keyid2 = staffData.keySet(); 

		rowid = 0; 

        for (String key : keyid2) { 
  
            row = spreadsheet2.createRow(rowid++); 
            Object[] objectArr = staffData.get(key); 
            int cellid = 0; 
  
            for (Object obj : objectArr) { 
                Cell cell = row.createCell(cellid++); 
                cell.setCellValue((String)obj); 
            } 
        } 

  		Set<String> keyid3 = campData.keySet(); 

		rowid = 0; 

        for (String key : keyid3) { 
  
            row = spreadsheet3.createRow(rowid++); 
            Object[] objectArr = campData.get(key); 
            int cellid = 0; 
  
            for (Object obj : objectArr) { 
                Cell cell = row.createCell(cellid++); 
                cell.setCellValue((String)obj); 
            } 
        } 


		rowid = 0; 
		
		for(int i = 0; i < database.getCamps().size();i++){
			row = spreadsheet4.createRow(rowid++); 
			int cellid = 0; 
			Camp tempCamp = database.getCamps().get(i);
			for(int h = 0; h < tempCamp.getListStudents().size();h++){
				Cell cell = row.createCell(cellid++); 
                cell.setCellValue(tempCamp.getListStudents().get(h).getUserId()); 
				cell = row.createCell(cellid++); 
				cell.setCellValue(String.valueOf(tempCamp.getListStudents().get(h).getCampCom().getIsCampCom())); 
			}
		}
        // .xlsx is the format for Excel Sheets... 
        // writing the workbook into the file... 
        FileOutputStream out = new FileOutputStream( 
            new File(pathName)); 
  
        workbook.write(out); 
        out.close(); 
		workbook.close();
    } 
}
