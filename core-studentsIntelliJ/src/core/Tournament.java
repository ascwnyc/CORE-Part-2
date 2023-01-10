package core;
import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from the CORE
* as required for 6COM1037 Cwk Assignment - Nov 2022
 * 
 * @author Terrence Corner & Alex Williams
 * @version 28/12/22
 */

public class Tournament implements CORE {
    // Fields
    // Fields
    private final String playerName;
    private final ArrayList<Item> championChallengeList;
    private int treasury;

    //**************** CORE **************************

    /**
     * Constructor requires the name of the player
     *
     * @param pl the name of the player
     */
    public Tournament(String pl) {
        playerName = pl;
        championChallengeList = new ArrayList<>();
        treasury = 1000;
        setupChallenges();
        setupChampions();
    }

    /**
     * Constructor requires the name of the player and the filename to load challenges from (Task 4.4).
     *
     * @param pl the name of the player
     * @param fn the filename to load challenges from.
     */
    public Tournament(String pl, String fn) {
        playerName = pl;
        championChallengeList = new ArrayList<>();
        treasury = 1000;
        // Reads challenges from comma-separated file (Task 4.4).
        readChallenges(fn);
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
        if (!isDefeated()) {
            defeated = "Is OK";
        } else {
            defeated = "Defeated";
        }
        String team = getTeam();
        if (Objects.equals(team, "")) {
            team = "No champions";
        }
        return "\n Player name: " + playerName + "\n Treasury: " + treasury + "\n Defeated?: " + defeated + "\n Team: " + team;
    }

    /**
     * returns true if Treasury <=0 and the player's team has no
     * champions which can be withdrawn.
     *
     * @return true if Treasury <=0 and the player's team has no
     * champions which can be decommissioned.
     */
    public boolean isDefeated() {
        return getMoney() <= 0 && teamEmpty();
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
     * returns the state of a champion
     *
     * @param champ champion object to return the state of
     * @return string regarding the state of a champion
     */
    private String getChampionState(Champion champ) {
            return champ.getStateString();
        }

    /**
     * Returns true if the state of the champion is equal to the provided state
     *
     * @param champ item object to return whether is equal to the provided state
     * @return true if the item is a champion and has that state, otherwise returns false
     */
    private boolean stateEquals(Item champ, String state) {
        String lowst = state.toLowerCase().trim();
        //Error handling to check whether item provided is champion
        return champ instanceof Champion && getChampionState((Champion) champ).toLowerCase().equals(lowst);
    }

    /**
     * Returns the type of challenge a challenge is
     *
     * @param challenge is the challenge to check the type of
     * @return the type of challenge a challenge is, otherwise an empty string
     */
    private String getChallengeType(Item challenge) {
        //Error handling to check whether item provided is champion
        if (challenge instanceof Challenge) {
            return ((Challenge)challenge).getTypeString();
        }
        return "";
    }

    /**
     * returns true if the type of challenge is equal to the provided type
     *
     * @param challenge is the challenge to check
     * @param type is the type of challenge to be checked against the challenge
     * @return true if the item provided is a challenge is the provided type, otherwise returns false
     */
    private boolean typeEquals(Item challenge, String type) {
        String lowst = type.toLowerCase().trim();
        //Error handling to check whether item provided is champion
        return challenge instanceof Challenge && getChallengeType(challenge).toLowerCase().equals(lowst);
    }

    /**
     * Returns a String representation of all champions in reserve
     *
     * @return a String representation of all champions in reserve
     **/
    public String getReserve() {
        String ss = "";
        for (Item temp : championChallengeList) {
        
            if (stateEquals(temp, "In reserve")) {
                ss += temp + "\n";
            }
        }
        return ss;
    }


    /**
     * Returns the champion object of a champion with the given name
     *
     * @param nme is the name of the champion
     * @return champion with the given name
     * @throws NullPointerException on 'throw new'
     **/
    private Champion getChampionObject(String nme) throws NullPointerException {
        for (Item temp : championChallengeList) {
            if (temp.getName().equals(nme) && temp instanceof Champion) {
                return (Champion) temp;
            }
        }
        throw new NullPointerException();
    }


    /**
     * Returns details of any champion with the given name
     *
     * @param nme is the name of the champion
     * @return details of any champion with the given name
     **/
    public String getChampionDetails(String nme) {
        return getChampionObject(nme).toString();
    }

    /**
     * returns whether champion is in reserve
     *
     * @param nme is the name of the champion
     * @return true if champion in reserve, false otherwise
     */
    public boolean isInReserve(String nme) {
        try {
            getChampionObject(nme);
        }
        catch (NullPointerException e) {
            return false;
        }
        return stateEquals(getChampionObject(nme), "In reserve");
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
        try
        {
            getChampionObject(nme);
        }
        catch (NullPointerException e){
            return -1;
        }

        Champion champ = getChampionObject(nme);

        if (!stateEquals(getChampionObject(nme), "In reserve")) {
            return 1;
                }

        int gulden = champ.getGulden();
        if (enoughGulden(-gulden)) {
            alterTreasury(-gulden);
            champ.alterState("active");
            return 0;
        }
        return 2;
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
        try
        {
            getChampionObject(nme);
        }
        catch (NullPointerException e){
            return false;
        }

        return stateEquals(getChampionObject(nme), "Active");
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
        try
        {
            getChampionObject(nme);
        }
        catch (NullPointerException e){
            return -1;
        }
        Champion champ = getChampionObject(nme);

        if (stateEquals(champ, "Dead")) {
            return 1;
        }

        if (stateEquals(champ, "In reserve")) {
            return 2;
        }

        champ.alterState("In reserve");
        alterTreasury(champ.getGulden()/2);
        return 0;
    }

    /**
     * Returns a String representation of the champions in the player's team
     * or the message "No champions entered"
     *
     * @return a String representation of the champions in the player's team
     **/
    public String getTeam() {
        String ss = "";
        for (Item temp : championChallengeList) // get each item in turn
        {
            if (stateEquals(temp, "Active")) {
                ss = ss + temp + "\n";
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
        return num > 0 && num <= getNoOfChallenges();
    }

    /**
     * Returns the number of challenges available
     *
     * @return the number of challenges
     **/
    private int getNoOfChallenges() {
        int challenges = 0;
        for (Item temp : championChallengeList) {
            if (temp instanceof Challenge) {
                challenges++;
            }
        }
        return challenges;
    }

    /**
     * Provides a String representation of a challenge given by
     * the challenge number
     *
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by
     * the challenge number
     **/
    public String getChallenge(int num) {
        if (isChallenge(num)) {
            return getChallengeObject(num).toString();
                }
        return "";
    }

    /**
     * Checks whether a challenge is the same as the number challenge chosen by a user
     *
     * @param num the number of the challenge
     * @param challenge the current challenge to check the given number by
     * @return returns true if the provided challenge matches the given challenge number, otherwise false
     **/
    private boolean equalsChallenge(int num, Item challenge) {
        return challenge instanceof Challenge && ((Challenge) challenge).getChallengeNo() == num;
    }

    /**
     * Provides the challenge object given by
     * its challenge number
     *
     * @precondition The isChallenge() method should be called before this to prevent NullPointerException otherwise try catch must be used
     * @param num the number of the challenge
     * @return returns the challenge object given by the challenge number
     * @throws NullPointerException at 'throw new'
     **/
    private Challenge getChallengeObject(int num) throws NullPointerException {
        if (isChallenge(num)) {
            for (Item temp : championChallengeList) {
                if (equalsChallenge(num, temp)) {
                    return (Challenge)temp;
                }
            }
        }
        throw new NullPointerException();
    }

    /**
     * Provides a String representation of all challenges
     *
     * @return returns a String representation of all challenges
     **/
    public String getAllChallenges() {
        String ss = "";
        for (Item temp : championChallengeList) {
            if (temp instanceof Challenge) {
                ss = ss + temp;
            }
        }
        return ss;
    }

    /**
     * Determines whether a champion provided is suitable to fight a challenge
     * @param challenge is the challenge the champion is checked for suitability against
     * @param champ is the champion to be checked for suitability against the given challenge
     * @return returns true if the champion is suited for the fight if their type can fight against the challenge type, otherwise false
     **/
    private boolean determineSuitability(Challenge challenge, Champion champ) {

        boolean matchesType = false;
        if (typeEquals(challenge, "Magic")) {
            matchesType = champ.isMagic();
        }
        if (typeEquals(challenge, "Fight")) {
            matchesType = champ.isFight();
        }
        if (typeEquals(challenge, "Mystery")) {
            matchesType = champ.isMystery();
        }

        return matchesType;
    }

    /**
     * Determines the outcome of a champion fighting the given challenge
     * The results of fighting a challenge will be
     *      one of the following:
     *      0 - challenge won, add reward to the treasury,
     *      1 - challenge lost on battle skills  - deduct reward from
     *      treasury and record champion as "dead"
     *      3 - If a challenge is lost and player completely defeated (no money and
     *      no champions to withdraw)
     *
     * @param challenge is the challenge the champion will fight in
     * @param champ is the champion to fight the challenge
     * @return an int showing the result(as above) of fighting the challenge
     **/
    private int determineBattleOutcome(Challenge challenge, Champion champ) {

            if (champ.getSkill() >= challenge.getSkill()) {
                alterTreasury(challenge.getGulden());
                return 0;
            } else {
                alterTreasury(-challenge.getGulden());
                champ.alterState("dead");
                if (isDefeated()) {
                    return 3;
                }
                return 1;
            }

        }

    /**
     * Retrieves the challenge represented by the challenge
     * number.Finds a champion from the team which can challenge the
     * challenge. The results of fighting a challenge will be
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
            Challenge challenge = getChallengeObject(chalNo);
            for (Item temp : championChallengeList) {

                if (stateEquals(temp, "Active")) {
                    Champion champ = (Champion) temp;
                    
                    if (determineSuitability(challenge, champ)) {
                        return determineBattleOutcome(challenge, champ);
                        }
                }
            }
            alterTreasury(-challenge.getGulden());

            if (isDefeated()) {
                return 3;
            }
            return 2;
        }
        return -1;
    }

//// These methods are not needed until Task 4.4
// ***************   file write/read  *********************

    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname){
        try {
            // Create new ObjectOutputStream under FileOutputStream,
            // and write game object to file.
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this);
        }
        catch (IOException e) {
            e.printStackTrace(); // Catch thrown IOException.
        }
    }

    /** reads all information about the game from the specified file
     * and returns a CORE reference to a Tournament object
     * @param fname name of file storing the game
     * @return the game (as a Tournament object)
     */
    public CORE loadGame(String fname){
        try {
            // Create new ObjectInputStream under FileInputStream.
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream in = new ObjectInputStream(fis);

            // Setup Tournament object to handle the reference,
            // then return it as an object.
            return (Tournament)in.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * reads challenges from a comma-separated textfile and stores
     * @param filename of the comma-separated textfile storing information about challenges
     */
    public void readChallenges(String filename){
        try {
            // Reset's challengeNo. Provided tests don't reset incrementation so must be reset here.
            if (championChallengeList.size() == 0) {
                Challenge.resetChallengeNo();
            }

            // Initialize line reader.
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;

            // While reading a valid line, split string into individual arguments.
            // Then, populate challengeList with Challenge objects where the fields are the arguments from split up string.
            while((str = in.readLine()) != null) {
                String[] arg = str.split(",");

                championChallengeList.add(new Challenge(arg[0], arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3])));
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace(); // Catch if invalid filename supplied.
        }
        catch (IOException e) {
            e.printStackTrace(); // Catch IO exception.
        }
    }

    /**
     * Adds the champions to the game that may be added to the player's team and fought in challenges
     *
     */
    private void setupChampions() {
        Champion cp0 = new Champion("Ganfrank", 7, true, 400, "transmutation", null, "wizard");
        championChallengeList.add(cp0);
        Champion cp1 = new Champion("Rudolf", 6, true, 400, "invisibility", null, "wizard");
        championChallengeList.add(cp1);
        Champion cp2 = new Champion("Elblond", 1, false, 150, "", "sword", "warrior");
        championChallengeList.add(cp2);
        Champion cp3 = new Champion("Flimsi", 2, false, 200, "", "bow", "warrior");
        championChallengeList.add(cp3);
        Champion cp4 = new Champion("Drabina", 7, false, 500, "", "", "dragon");
        championChallengeList.add(cp4);
        Champion cp5 = new Champion("Golum", 7, false, 500, "", "sword", "talking dragon");
        championChallengeList.add(cp5);
        Champion cp6 = new Champion("Argon", 9, false, 900, "", "mace", "warrior");
        championChallengeList.add(cp6);
        Champion cp7 = new Champion("Neon", 2, false, 300, "translocation", "", "wizard");
        championChallengeList.add(cp7);
        Champion cp8 = new Champion("Xenon", 7, false, 500, "", "", "talking dragon");
        championChallengeList.add(cp8);
        Champion cp9 = new Champion("Atlanta", 5, false, 500, "", "bow", "warrior");
        championChallengeList.add(cp9);
        Champion cp10 = new Champion("Krypton", 8, false, 300, "fireballs", "", "wizard");
        championChallengeList.add(cp10);
        Champion cp11 = new Champion("Hedwig", 1, true, 400, "flying", "", "warrior");
        championChallengeList.add(cp11);
    }

    /**
     * Adds the challenges to the game that champions will fight against
     *
     */
    private void setupChallenges() {
        // Reset's challengeNo. Provided tests don't reset incrementation so must be reset here.
        if (championChallengeList.size() == 0) {
            Challenge.resetChallengeNo();
        }

        Challenge ch0 = new Challenge("Magic", "Borg", 3, 100);
        championChallengeList.add(ch0);
        Challenge ch1 = new Challenge("Fight", "Huns", 3, 120);
        championChallengeList.add(ch1);
        Challenge ch2 = new Challenge("Mystery", "Ferengi", 3, 150);
        championChallengeList.add(ch2);
        Challenge ch3 = new Challenge("Magic", "Vandal", 9, 200);
        championChallengeList.add(ch3);
        Challenge ch4 = new Challenge("Mystery", "Borg", 7, 90);
        championChallengeList.add(ch4);
        Challenge ch5 = new Challenge("Fight", "Goth", 8, 45);
        championChallengeList.add(ch5);
        Challenge ch6 = new Challenge("Magic", "Frank", 10, 200);
        championChallengeList.add(ch6);
        Challenge ch7 = new Challenge("Fight", "Sith", 10, 170);
        championChallengeList.add(ch7);
        Challenge ch8 = new Challenge("Mystery", "Cardashian", 9, 300);
        championChallengeList.add(ch8);
        Challenge ch9 = new Challenge("Fight", "Jute", 2, 300);
        championChallengeList.add(ch9);
        Challenge ch10 = new Challenge("Magic", "Celt", 2, 250);
        championChallengeList.add(ch10);
        Challenge ch11 = new Challenge("Mystery", "Celt", 1, 250);
        championChallengeList.add(ch11);
    }

    /**
     * Alters the treasury by the specified amount of gulden determined by the int gulden from the treasury
     *
     * @param gulden is the amount of gulden to alter by, positive gulden increases the treasury and negative gulden decreases the treasury.
     * @precondition May require the calling of enough gulden beforehand if the transaction cannot become less than zero in that case
     */
    private void alterTreasury(int gulden) {
        treasury = treasury + gulden;
    }

    /**
     * Checks whether there is enough gulden in the treasury to deduct gulden from
     *
     * @param gulden is the amount of gulden to alter by, positive gulden increases the treasury and negative gulden decreases the treasury.
     * @return true if there is enough gulden left in the treasury to deduct the amount by, otherwise returns false
     */
    private boolean enoughGulden(int gulden) {
        return treasury + gulden >= 0;
    }

    /**
     * Returns whether the team is empty or not.
     *
     * @return true if the team contains no champions, otherwise returns false
     */
    private boolean teamEmpty() {
        for (Item temp : championChallengeList) {
                
                if (stateEquals(temp, "Active")) {
                    return false;
                }
        }
        return true;
    }

    /**
     * Returns true if the champion with the name is in
     * the player's team, false otherwise.
     *
     * @param nme is the name of the champion
     * @return true if the champion with the name
     * is in the player's team, false otherwise.
     **/
    public boolean isDead(String nme) {
        for (Item temp : championChallengeList)
        {
            if (temp.getName().equals(nme) && temp instanceof Champion champ) {
                if (champ.getStateString().equals("dead")) {
                    return true;
                }
            }
        }
        return false;
    }
}



