package core;
import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from the CORE
* as required for 6COM1037 Cwk Assignment - Nov 2022
 * 
 * @author 
 * @version 05/10/22
 */

public class Tournament implements CORE
{
    // Fields
    private String playerName;
    private ArrayList<Champion> championList;
    private ArrayList<Challenge> challengeList;
    private int treasury;
    
    //**************** CORE ************************** 
    /** Constructor requires the name of the player
     * @param pl the name of the player
     */  
    public Tournament(String pl)
    {
       playerName = pl;
       championList = new ArrayList<Champion>();
       challengeList = new ArrayList<Challenge>();
       treasury = 1000;
    }

    //******* Implements interface CORE *******************
    /**Returns a String representation of the state of the game,
     * including the name of the player, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     * 
     * @return a String representation of the state of the game,
     * including the name of the player, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     */
    public String toString() {
        return playerName + Integer.toString(treasury) + String.valueOf(isDefeated()) + getTeam();
   }

    /** returns true if Treasury <=0 and the player's team has no 
     * champions which can be withdrawn. 
     * @return true if Treasury <=0 and the player's team has no 
     * champions which can be decommissioned. 
     */
    public boolean isDefeated(){
       return false;
    }

    /** returns the amount of money in the Treasury
     * @return the amount of money in the Treasury
     */
    public int getMoney(){
        
       return treasury;
    }    
    
    /**Returns a String representation of all champions in reserve
     * @return a String representation of all champions in reserve
     **/
    public String getReserve(){
        
       return "";
    }
       
    /** Returns details of any champion with the given name
     * @param nme is the name of the champion
     * @return details of any champion with the given name
     **/
    public String getChampionDetails(String nme){
        
       return "";
    }
    
    /** returns whether champion is in reserve
    * @param nme is the name of the champion
    * @return true if champion in reserve, false otherwise
    */
    public boolean isInReserve(String nme) {

        for (int i = 0; i < championList.size(); i++)  //can't use the for..each as no index
        {
            Champion temp = championList.get(i);
            if((temp.getName() == nme))
            {
                return true;
            }
        }
        return false;
    }

 // ***************** Players Team************************   
    /** Allows a champion to be entered for the player's team, if there 
     * is enough money in the Treasury for the entry fee.The champion's 
     * state is set to "active"
     * 0 if champion is entered in the player's team, 
     * 1 if champion is not in reserve, 
     * 2 if not enough money in the treasury, 
     * -1 if there is no such champion 
     * @param nme represents the name of the champion
     * @return as shown above
     **/        
    public int enterChampion(String nme){

        for (Champion temp : championList)
        {
            if (temp.getName() == nme)
            {
                ChampionState state = temp.getState();
                if (state != ChampionState.WAITING)
                { return 1; }

                if (state == ChampionState.WAITING)
                { return 2; }

                temp.alterState("active") ;
                return 0;
            }
        }
        return -1;
    }
    
        
    /** Returns true if the champion with the name is in 
     * the player's team, false otherwise.
     * @param nme is the name of the champion
     * @return  true if the champion with the name
     * is in the player's team, false otherwise.
     **/
    public boolean isInPlayersTeam(String nme){
        for (Champion temp : championList)  //can't use the for..each as no index
        {
            if((temp.getName() == nme))
            {
                if (temp.getState() == ChampionState.DEAD)
                { return true; }
            }
        }
        return false;
    }

    /** Removes a champion from the team to the reserves (if they are in the team)
     * Pre-condition: isChampion()
     * 0 - if champion is retired to reserves
     * 1 - if champion not retired because dead
     * 2 - if champion not retired because not in team
     * -1 - if no such champion
     * @param nme is the name of the champion
     * @return as shown above 
     **/
    public int retireChampion(String nme){
        for (Champion temp : championList)
        {
            if (temp.getName() == nme)
            {
                ChampionState state = temp.getState();
                if (state == ChampionState.DEAD)
                { return 1; }

                if (state == ChampionState.WAITING)
                { return 2; }

                temp.alterState("in reserve") ;
                return 0;
            }
        }
        return -1;
    }

    /**Returns a String representation of the champions in the player's team
     * or the message "No champions entered"
     * @return a String representation of the champions in the player's team
     **/
    public String getTeam(){
        String ss = "";
        for(Champion temp : championList) // get each item in turn
        {
            ChampionState state = temp.getState();
            if (state == ChampionState.ACTIVE) {
                ss = ss + temp.toString() + "\n";
            }
        }
        return ss;
    }
    
    
//**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the number of the challenge
     * @return true if the number represents a challenge
     **/
     public boolean isChallenge(int num){

         for (int i = 0; i < challengeList.size(); i++)  //can't use the for..each as no index
         {
             Challenge temp = challengeList.get(i);
             if((temp.getChallengeNo() == num))
             {
                 return true;
             }
         }
         return false;
    }
     
    /** Provides a String representation of an challenge given by 
     * the challenge number
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num){

        for (Challenge temp : challengeList)
        {
            if((temp.getChallengeNo() == num))
            {
                return temp.toString();
            }
        }
        return "";
    }
    
    /** Provides a String representation of all challenges 
     * @return returns a String representation of all challenges
     **/
    public String getAllChallenges(){

        String ss = "";
        for (Challenge temp : challengeList)
        {
                ss = ss + temp.toString();
        }
        return ss;
    }
    
    /** Retrieves the challenge represented by the challenge 
     * number.Finds a champion from the team which can challenge the
     * challenge. The results of fighting an challenge will be 
     * one of the following:  
     * 0 - challenge won, add reward to the treasury, 
     * 1 - challenge lost on battle skills  - deduct reward from
     * treasury and record champion as "dead"
     * 2 - challenge lost as no suitable champion is  available, deduct
     * the reward from treasury 
     * 3 - If a challenge is lost and player completely defeated (no money and 
     * no champions to withdraw) 
     * -1 - no such challenge 
     * @param chalNo is the number of the challenge
     * @return an int showing the result(as above) of fighting the challenge
     */ 
    public int fightChallenge(int chalNo){
        
       return 0;
    }
  
//// These methods are not needed until Task 4.4
//    // ***************   file write/read  *********************
//    /** Writes whole game to the specified file
//     * @param fname name of file storing requests
//     */
//    public void saveGame(String fname){
//
//
//    }
//
//    /** reads all information about the game from the specified file
//     * and returns a CORE reference to a Tournament object
//     * @param fname name of file storing the game
//     * @return the game (as a Tournament object)
//     */
//    public CORE loadGame(String fname){
//
//       return null;
//    }
//
//    /**
//     * reads challenges from a comma-separated textfile and stores
//     * @param filename of the comma-separated textfile storing information about challenges
//     */
//    public void readChallenges(String filename){
//
//    }

    private void setupChampions() {
        Champion cp0 = new Champion("Ganfrank", 7, true, 400, "transmutation", null, false);
        championList.add(cp0);
        Champion cp1 = new Champion("Rudolf", 6, true, 400, "invisibility", null, false);
        championList.add(cp1);
        Champion cp2 = new Champion("Elblond", 1, false, 150, "", "sword", false);
        championList.add(cp2);
        Champion cp3 = new Champion("Flimsi", 2, false, 200, "", "bow", false);
        championList.add(cp3);
        Champion cp4 = new Champion("Drabina", 7, false, 500, "", "", false);
        championList.add(cp4);
        Champion cp5 = new Champion("Golum", 7, false, 500, "", "sword", true);
        championList.add(cp5);
        Champion cp6 = new Champion("Argon", 9, false, 900, "", "mace", false);
        championList.add(cp6);
        Champion cp7 = new Champion("Neon", 2, false, 300, "translocation", "", false);
        championList.add(cp7);
        Champion cp8 = new Champion("Xenon",7, false, 500, "", "", true);
        championList.add(cp8);
        Champion cp9 = new Champion("Atlanta", 5, false, 500, "", "bow", false);
        championList.add(cp9);
        Champion cp10 =  new Champion("Krypton", 8, false, 300, "fireballs", "", false);
        championList.add(cp10);
        Champion cp11 = new Champion("Hedwig", 1, true, 400, "flying", "", false);
        championList.add(cp11);

    }

    private void setupChallenges() {
        Challenge ch0 = new Challenge(ChallengeType.MAGIC, "Borg", 3, 100);
        challengeList.add(ch0);
        Challenge ch1 = new Challenge(ChallengeType.FIGHT, "Huns", 3, 120);
        challengeList.add(ch1);
        Challenge ch2 = new Challenge(ChallengeType.MYSTERY, "Ferengi", 3, 150);
        challengeList.add(ch2);
        Challenge ch3 = new Challenge(ChallengeType.MAGIC, "Vandal", 9, 200);
        challengeList.add(ch3);
        Challenge ch4 = new Challenge(ChallengeType.MYSTERY, "Borg", 7, 90);
        challengeList.add(ch4);
        Challenge ch5 = new Challenge(ChallengeType.FIGHT, "Goth", 8, 45);
        challengeList.add(ch5);
        Challenge ch6 = new Challenge(ChallengeType.MAGIC, "Frank", 10, 200);
        challengeList.add(ch6);
        Challenge ch7 = new Challenge(ChallengeType.FIGHT, "Sith", 10, 170);
        challengeList.add(ch7);
        Challenge ch8 = new Challenge(ChallengeType.MYSTERY, "Cardashian", 9, 300);
        challengeList.add(ch8);
        Challenge ch9 = new Challenge(ChallengeType.FIGHT, "Jute", 2, 300);
        challengeList.add(ch9);
        Challenge ch10 = new Challenge(ChallengeType.MAGIC, "Celt", 2, 250);
        challengeList.add(ch10);
        Challenge ch11 = new Challenge(ChallengeType.MYSTERY, "Celt", 1, 250);
        challengeList.add(ch11);
    }
}



