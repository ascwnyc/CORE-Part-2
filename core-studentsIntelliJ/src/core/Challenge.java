package core;

public class Challenge extends Item {
    private static int challNo = 1;
    private ChallengeType type;

    private int challengeNo;

    public Challenge(String ty, String en, int sr, int rw) {
        super(en,sr,rw);
        challengeNo = challNo++;

        try {
            chooseType(ty);
        }
        catch (InterruptedException e) {

        }
    }

    public String toString() {
        return("\n Challenge Number: " + challengeNo + "\nType: " + type + "\nEnemy: " + super.getName() + "\nSkill Required: " + super.getSkill() + "\nReward: " + super.getGulden());
    }

    public int getChallengeNo() {
        return challengeNo;
    }

    public static void resetChallengeNo() {
        challNo = 1;
    }

    public String getTypeString() {
        return type.toString().trim();
    }

    /** Changes the challenge's type to that of the string provided
     * @param st is the string name of the type to change the challenge, the following strings change the type in these ways;
     *  "magic" changes challenge type to MAGIC
     *  "fight" changes the challenge type to FIGHT
     *  "mystery" changes the challenge type to MYSTERY
     *
     *
     * @return boolean returns true if the type was altered and false if this fails
     **/
    public boolean chooseType(String st) throws InterruptedException {
        String lowst = st.toLowerCase().trim();
        if (lowst.equals("magic")) {
            type = ChallengeType.MAGIC;
            return true;
        }

        if (lowst.equals("fight")) {
            type = ChallengeType.FIGHT;
            return true;
        }

        if (lowst.equals("mystery")) {
            type = ChallengeType.MYSTERY;
            return true;
        }
        return false;
    }
}
