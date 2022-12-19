package core;

public class Challenge {

    private static int challNo = 1;
    private ChallengeType type;
    private String enemy;
    private int skillRequired;
    private int reward;

    private int challengeNo;

    public Challenge(ChallengeType ty, String en, int sr, int rw, boolean reset) {
        if (reset) {
            challNo = 1;
        }
        challengeNo = challNo++;
        type = ty;
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

    public int getSkillRequired() {
        return skillRequired;
    }

    public int getReward() {
        return reward;
    }


}
