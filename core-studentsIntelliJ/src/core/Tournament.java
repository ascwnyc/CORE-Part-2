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
    
    
    //**************** CORE ************************** 
    /** Constructor requires the name of the player
     * @param pl the name of the player
     */  
    public Tournament(String pl)
    {
       
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
        
        return "";
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
        
       return 0;
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
        
       return 0;
    }
    
        
    /** Returns true if the champion with the name is in 
     * the player's team, false otherwise.
     * @param nme is the name of the champion
     * @return  true if the champion with the name
     * is in the player's team, false otherwise.
     **/
    public boolean isInPlayersTeam(String nme){
        
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
        
       return 0;
    }
        
        
    /**Returns a String representation of the champions in the player's team
     * or the message "No champions entered"
     * @return a String representation of the champions in the player's team
     **/
    public String getTeam(){
        
       return "";
    }
    
    
//**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the number of the challenge
     * @return true if the number represents a challenge
     **/
     public boolean isChallenge(int num){
        
       return false;
    }
     
    /** Provides a String representation of an challenge given by 
     * the challenge number
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num){
        
       return "";
    }
    
    /** Provides a String representation of all challenges 
     * @return returns a String representation of all challenges
     **/
    public String getAllChallenges(){
        
       return "";
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
}



