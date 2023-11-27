
public class Suggestion {
  private static int suggestionCounter = 0;
	
  private String suggestionText;
  private boolean status; //accepted or rejected
  private int suggestionIndex;
  private String owner; // ID or name of the user who made the enquiry
  private String campName;

  public Suggestion(String suggestionText, String owner, String campName){
    this.suggestionText = suggestionText;
    this.suggestionIndex = suggestionCounter;
    suggestionCounter++;
    status = false;  //initialise suggestion as not accepted
    this.owner = owner;
    this.campName = campName;
  }

  
  /** 
   * @return String
   */
  public String getSuggestionText() {
      return suggestionText;
  }

  
  /** 
   * @param suggestionText
   */
  public void setSuggestionText(String suggestionText) {
      this.suggestionText = suggestionText;
  }

  
  /** 
   * @return boolean
   */
  public boolean getStatus(){
    return status;
  }

  
  /** 
   * @param status
   */
  public void setStatus(boolean status){
    this.status = status;
  }
  
  
  /** 
   * @return int
   */
  public int getSuggestionIndex() {
	  return suggestionIndex;
  }
  
  
  /** 
   * @param suggestionIndex
   */
  public void setSuggestionIndex(int suggestionIndex) {
	  this.suggestionIndex = suggestionIndex;
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
  public String getCampName() {
	  return campName;
  }

  
  /** 
   * @param campName
   */
  public void setCampName(String campName) {
	  this.campName = campName;
  }
  
  
  public void displaySuggestion() {
      System.out.println("Suggestion Index: " + (suggestionIndex + 1) );
      System.out.println("Suggestion: " + suggestionText + " by " + owner);
      System.out.println("Status: " + (status ? "Approved" : "Rejected"));
  }
}
    
