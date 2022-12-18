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

public class Tournament implements CORE {
    // Fields
    private String playerName;
    private ArrayList<Champion> championList;
    private ArrayList<Challenge> challengeList;
    private int treasury;

    //**************** CORE ************************** 

    /**
     * Constructor requires the name of the player
     *
     * @param pl the name of the player
     */
    public Tournament(String pl) {
        playerName = pl;
        championList = new ArrayList<Champion>();
        challengeList = new ArrayList<Challenge>();
        treasury = 1000;
        setupChallenges();
        setupChampions();
    }

    //******* Implements interface CORE *******************

    /**
     * Returns a String representation of the state of the game,
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
        String defeated;
        if (!isDefeated()){
        defeated = "Is OK";}
        else {
            defeated = "Defeated";
        }
    String team = getTeam();
    if (team == ""){
        team = "No champions";
    }
        return "\n Player name: " + playerName + "\n Treasury: " + Integer.toString(treasury) + "\n Defeated?: " + defeated + "\n Team: " + team;
    }

    /**
     * returns true if Treasury <=0 and the player's team has no
     * champions which can be withdrawn.
     *
     * @return true if Treasury <=0 and the player's team has no
     * champions which can be decommissioned.
     */
    public boolean isDefeated() {
        if (getMoney() <= 0 && teamEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * returns the amount of money in the Treasury
     *
     * @return the amount of money in the Treasury
     */
    public int getMoney() {

        return treasury;
    }

    /**
     * Returns a String representation of all champions in reserve
     *
     * @return a String representation of all champions in reserve
     **/
    public String getReserve() {
        String ss = "";
        for (Champion temp : championList) {
            if (temp.getState() == ChampionState.WAITING) {

                ss += temp.toString();
            }
        }

        return ss;
    }

    /**
     * Returns details of any champion with the given name
     *
     * @param nme is the name of the champion
     * @return details of any champion with the given name
     **/
    public String getChampionDetails(String nme) {
        String ss = "";
        for (Champion temp : championList)
        {
            if (temp.getName().equals(nme)) {
                ss = temp.toString();
            }
        }
        return ss;
    }

    /**
     * returns whether champion is in reserve
     *
     * @param nme is the name of the champion
     * @return true if champion in reserve, false otherwise
     */
    public boolean isInReserve(String nme) {

        for (Champion temp : championList)
        {
            if ((temp.getName().equals(nme))) {
                if (temp.getState() == ChampionState.WAITING) {
                    return true;
                }
            }
        }
        return false;
    }

    // ***************** Players Team************************

    /**
     * Allows a champion to be entered for the player's team, if there
     * is enough money in the Treasury for the entry fee.The champion's
     * state is set to "active"
     * 0 if champion is entered in the player's team,
     * 1 if champion is not in reserve,
     * 2 if not enough money in the treasury,
     * -1 if there is no such champion
     *
     * @param nme represents the name of the champion
     * @return as shown above
     **/
    public int enterChampion(String nme) {

        for (Champion temp : championList) {
            if (temp.getName().equals(nme)) {
                ChampionState state = temp.getState();
                if (state != ChampionState.WAITING) {
                    return 1;
                }

                int gulden = temp.getEntryFee();
                if (!alterTreasury(-gulden)) {
                    return 2;
                }

                temp.alterState("active");
                return 0;
            }
        }
        return -1;
    }


    /**
     * Returns true if the champion with the name is in
     * the player's team, false otherwise.
     *
     * @param nme is the name of the champion
     * @return true if the champion with the name
     * is in the player's team, false otherwise.
     **/
    public boolean isInPlayersTeam(String nme) {
        for (Champion temp : championList)
        {
            if (temp.getName().equals(nme)) {
                if (temp.getState() == ChampionState.ACTIVE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes a champion from the team to the reserves (if they are in the team)
     * Pre-condition: isChampion()
     * 0 - if champion is retired to reserves
     * 1 - if champion not retired because dead
     * 2 - if champion not retired because not in team
     * -1 - if no such champion
     *
     * @param nme is the name of the champion
     * @return as shown above
     **/
    public int retireChampion(String nme) {
        for (Champion temp : championList) {
            if (temp.getName().equals(nme)) {
                ChampionState state = temp.getState();
                System.out.println(state.toString());
                if (state == ChampionState.DEAD) {
                    return 1;
                }

                if (state == ChampionState.WAITING) {
                    return 2;
                }

                temp.alterState("in reserve");
                alterTreasury(temp.getEntryFee()/2);
                return 0;
            }
        }
        return -1;
    }

    /**
     * Returns a String representation of the champions in the player's team
     * or the message "No champions entered"
     *
     * @return a String representation of the champions in the player's team
     **/
    public String getTeam() {
        String ss = "";
        for (Champion temp : championList) // get each item in turn
        {
            ChampionState state = temp.getState();
            if (state == ChampionState.ACTIVE) {
                ss = ss + temp.toString() + "\n";
            }
        }
        return ss;
    }


//**********************Challenges************************* 

    /**
     * returns true if the number represents a challenge
     *
     * @param num is the number of the challenge
     * @return true if the number represents a challenge
     **/
    public boolean isChallenge(int num) {
            if (num < challengeList.size() && num > 0) {
                return true;
            }
        return false;
    }

    /**
     * Provides a String representation of an challenge given by
     * the challenge number
     *
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by
     * the challenge number
     **/
    public String getChallenge(int num) {

        for (Challenge temp : challengeList) {
            if ((temp.getChallengeNo() == num)) {
                System.out.println("hey");
                return temp.toString();
            }
        }
        return "";
    }

    /**
     * Provides a String representation of all challenges
     *
     * @return returns a String representation of all challenges
     **/
    public String getAllChallenges() {

        String ss = "";
        for (Challenge temp : challengeList) {
            ss = ss + temp.toString();
        }
        return ss;
    }

    /**
     * Retrieves the challenge represented by the challenge
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
     *
     * @param chalNo is the number of the challenge
     * @return an int showing the result(as above) of fighting the challenge
     */
    public int fightChallenge(int chalNo) {
        if (isChallenge(chalNo)) {
            Challenge challenge = challengeList.get(chalNo-1);
            String challengeType = challenge.getType().toString().toLowerCase();
            boolean matchesType = false;
            for (Champion temp : championList)
            {
                if (temp.getState() == ChampionState.ACTIVE) {
                    if (challengeType.trim().equals("magic")) {
                        matchesType = temp.getType().isMagic();
                    }
                    if (challengeType.trim().equals("fight")) {
                        matchesType = temp.getType().isFight();
                    }
                    if (challengeType.trim().equals("mystery")) {
                        matchesType = temp.getType().isMystery();
                    }

                    if (matchesType) {
                        if (temp.getSkillLevel() >= challenge.getSkillRequired()) {
                            alterTreasury(challenge.getReward());
                            return 0;
                        } else {
                            alterTreasury(-challenge.getReward());
                            temp.alterState("dead");
                            if (isDefeated()) {
                                return 3;
                            }
                            return 1;
                        }
                }

                    alterTreasury(-challenge.getReward());
                    temp.alterState("dead");
                    if (isDefeated()) {
                        return 3;
                    }
                    return 1;


                }

            }

            if (isDefeated()) {
                return 3;
            }
            return 2;

        }
        return -1;
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
        Champion cp0 = new Champion("Ganfrank", 7, true, 400, "transmutation", null, false, ChampionType.WIZARD);
        championList.add(cp0);
        Champion cp1 = new Champion("Rudolf", 6, true, 400, "invisibility", null, false, ChampionType.WIZARD);
        championList.add(cp1);
        Champion cp2 = new Champion("Elblond", 1, false, 150, "", "sword", false, ChampionType.WARRIOR);
        championList.add(cp2);
        Champion cp3 = new Champion("Flimsi", 2, false, 200, "", "bow", false, ChampionType.WARRIOR);
        championList.add(cp3);
        Champion cp4 = new Champion("Drabina", 7, false, 500, "", "", false, ChampionType.DRAGON);
        championList.add(cp4);
        Champion cp5 = new Champion("Golum", 7, false, 500, "", "sword", true, ChampionType.TALKINGDRAGON);
        championList.add(cp5);
        Champion cp6 = new Champion("Argon", 9, false, 900, "", "mace", false, ChampionType.WARRIOR);
        championList.add(cp6);
        Champion cp7 = new Champion("Neon", 2, false, 300, "translocation", "", false, ChampionType.WIZARD);
        championList.add(cp7);
        Champion cp8 = new Champion("Xenon", 7, false, 500, "", "", true, ChampionType.TALKINGDRAGON);
        championList.add(cp8);
        Champion cp9 = new Champion("Atlanta", 5, false, 500, "", "bow", false, ChampionType.WARRIOR);
        championList.add(cp9);
        Champion cp10 = new Champion("Krypton", 8, false, 300, "fireballs", "", false, ChampionType.WIZARD);
        championList.add(cp10);
        Champion cp11 = new Champion("Hedwig", 1, true, 400, "flying", "", false, ChampionType.WIZARD);
        championList.add(cp11);

    }

    private void setupChallenges() {
        boolean reset;
        if (challengeList.size() == 0) {
        reset = true;
        }
        else {
            reset = false;
        }
        Challenge ch0 = new Challenge(ChallengeType.MAGIC, "Borg", 3, 100, reset);
        challengeList.add(ch0);
        Challenge ch1 = new Challenge(ChallengeType.FIGHT, "Huns", 3, 120, false);
        challengeList.add(ch1);
        Challenge ch2 = new Challenge(ChallengeType.MYSTERY, "Ferengi", 3, 150, false);
        challengeList.add(ch2);
        Challenge ch3 = new Challenge(ChallengeType.MAGIC, "Vandal", 9, 200, false);
        challengeList.add(ch3);
        Challenge ch4 = new Challenge(ChallengeType.MYSTERY, "Borg", 7, 90, false);
        challengeList.add(ch4);
        Challenge ch5 = new Challenge(ChallengeType.FIGHT, "Goth", 8, 45, false);
        challengeList.add(ch5);
        Challenge ch6 = new Challenge(ChallengeType.MAGIC, "Frank", 10, 200, false);
        challengeList.add(ch6);
        Challenge ch7 = new Challenge(ChallengeType.FIGHT, "Sith", 10, 170, false);
        challengeList.add(ch7);
        Challenge ch8 = new Challenge(ChallengeType.MYSTERY, "Cardashian", 9, 300, false);
        challengeList.add(ch8);
        Challenge ch9 = new Challenge(ChallengeType.FIGHT, "Jute", 2, 300, false);
        challengeList.add(ch9);
        Challenge ch10 = new Challenge(ChallengeType.MAGIC, "Celt", 2, 250, false);
        challengeList.add(ch10);
        Challenge ch11 = new Challenge(ChallengeType.MYSTERY, "Celt", 1, 250, false);
        challengeList.add(ch11);
    }

    /**
     * Alters the treasury by the specified amount of gulden determined by the int gulden from the treasury
     *
     * @param gulden is the amount of gulden to alter by, positive gulden increases the treasury and negative gulden decreases the treasury.
     *
     */
    public boolean alterTreasury(int gulden) {
        if (enoughGuldun(gulden)) {
        treasury = treasury + gulden;
        return true; }
        return false;
    }

    /**
     * Checks whether there is enough gulden in the treasury to deduct gulden from
     *
     * @param gulden is the amount of gulden to alter by, positive gulden increases the treasury and negative gulden decreases the treasury.
     * @return true if there is enough gulden left in the treasury to deduct the amount by, otherwise returns false
     */
    public boolean enoughGuldun(int gulden) {
        if (treasury + gulden >= 0) {
            return true;
        }
        return false;
    }



    /**
     * Returns whether the team is empty or not.
     *
     * @return true if the team contains no champions, otherwise returns false
     */
    public boolean teamEmpty() {
    for (Champion temp : championList)
    {
            if (temp.getState() == ChampionState.ACTIVE) {
                return false;
            }
        }

    return true;
}

}



