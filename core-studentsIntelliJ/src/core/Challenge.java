package core;

public class Challenge extends Item {
    private static int challNo = 1;
    private ChallengeType type;
    private String enemy;
    private int skillRequired;
    private int reward;
    private int challengeNo;

    public Challenge(String ty, String en, int sr, int rw) {
        super(en,sr,rw);

        challengeNo = challNo++;
        chooseType(ty);
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

    public ChallengeType getType() {
        return type;
    }

    public String getTypeString() {
        return type.toString().trim();
    }

    public int getSkillRequired() {
        return skillRequired;
    }

    public int getReward() {
        return reward;
    }

    public int getStaticChallNo() {
        return challNo;
    }

    public boolean chooseType(String st) {
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
