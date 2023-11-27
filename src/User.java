
public abstract class User {
    private String userId;
    private String name;
    private String password;
    private Faculty facultyInfo;

    public User(String userId, String name, String password, Faculty facultyInfo){
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.facultyInfo = facultyInfo;
    }
    
    
    /** 
     * @return String
     */
    public String getUserId() {
        return this.userId;
    }

    
    /** 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    
    /** 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    /** 
     * @return Faculty
     */
    public Faculty getFacultyInfo() {
        return this.facultyInfo;
    }

    
    /** 
     * @param facultyInfo
     */
    public void setFacultyInfo(Faculty facultyInfo) {
        this.facultyInfo = facultyInfo;
    }
    //Function to view camp list
    public abstract void generateCampReport(Camp camp, int number);
}
