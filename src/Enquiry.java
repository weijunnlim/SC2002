
public class Enquiry {
	private static int enquiryCounter;
	
    private String enquiryText;
    private String replyText;
    private boolean status; //true = replied, false = not replied
    private int enquiryIndex;
    private String owner; // ID or name of the user who made the enquiry
    private String campName;

    public Enquiry(String enquiryText, String owner, String campName) {
        this.enquiryText = enquiryText;
        this.enquiryIndex = enquiryCounter;
        enquiryCounter++;
        this.owner = owner;
        this.status = false; // Default status not replied yet
        this.campName = campName;
    }

    
    /** 
     * @return String
     */
    public String getEnquiryText() {
        return enquiryText;
    }

    
    /** 
     * @param enquiryText
     */
    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }
    
    
    /** 
     * @return String
     */
    public String getReplyText() {
    	return replyText;
    }
    
    
    /** 
     * @param replyText
     */
    public void setReplyText(String replyText) {
    	this.replyText = replyText;
    }

    
    /** 
     * @return boolean
     */
    public boolean getStatus() {
        return status;
    }

    
    /** 
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    /** 
     * @return int
     */
    public int getEnquiryIndex() {
    	return enquiryIndex;
    }
    
    
    /** 
     * @param enquiryIndex
     */
    public void setEnquiryIndex(int enquiryIndex) {
    	this.enquiryIndex = enquiryIndex;
    }

    
    /** 
     * @return String
     */
    public String getOwner() {
        return owner;
    }

    
    /** 
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    
    /** 
     * @return String
     */
    public String getCampName(){
        return campName;
    }

    
    /** 
     * @param campName
     */
    public void setCampName(String campName){
        this.campName = campName;
    }

    /** 
     * Displays the enquiry
     */
    public void displayEnquiry() {
    	System.out.println("Enquiry Index: " + (enquiryIndex+1) );
        System.out.println("Enquiry: " + enquiryText + " by " + owner);
        System.out.println("Status: " + (status ? "Replied" : "Not Replied"));
        if (status) {
            System.out.println("Reply: " + replyText);
        }
    }

    public void deleteEnquiry(){
        enquiryCounter--;
    }//for future implementation if not there will be too many enquiries
}
