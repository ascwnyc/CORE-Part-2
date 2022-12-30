package core;

public class Challenge {

    private static int challNo = 1;
    private ChallengeType type;
    private String enemy;
    private int skillRequired;
    private int reward;

    private int challengeNo;

    public Challenge(String ty, String en, int sr, int rw, boolean reset) {
        if (reset) {
            challNo = 1;
        }
        challengeNo = challNo++;
        chooseType(ty);
        enemy = en;
        skillRequired = sr;
        reward = rw;
    }

    public String toString() {
        return("\n Challenge Number: " + challengeNo + "\nType: " + type + "\nEnemy: " + enemy + "\nSkill Required: " + skillRequired + "\nReward: " + reward);
    }

    public int getChallengeNo() {
        return challengeNo;
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
