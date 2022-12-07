package core;


/**
 * Details of your team
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[7];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team
        // It will help us if the surname of programmer1 comes
        // alphabetically before the surname of programmer2
        details[0] = "team name";
        details[1] = "surname of programmer1";
        details[2] = "first name of programmer1";
        details[3] = "SRN of programmer1";
        details[4] = "surname of programmer2";
        details[5] = "first name of programmer2";
        details[6] = "SRN of programmer2";
    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
