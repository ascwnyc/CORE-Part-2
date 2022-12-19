package core;


/**
 * Details of your team
 * 
 * @author (Alex Williams, Terrence Corner)
 * @version (v1.0.0 19/12/22)
 */
public class Teamwork
{
    private String[] details = new String[7];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team
        // It will help us if the surname of programmer1 comes
        // alphabetically before the surname of programmer2
        details[0] = "TCAW";
        details[1] = "Williams";
        details[2] = "Alex";
        details[3] = "19045324";
        details[4] = "Corner";
        details[5] = "Terrence";
        details[6] = "19039667";
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
        
