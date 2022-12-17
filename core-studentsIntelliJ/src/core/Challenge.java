package core;

public class Challenge {

    private static int challengeNo = 0;
    private ChallengeType type;
    private String enemy;
    private int skillRequired;
    private int reward;

    public Challenge(ChallengeType ty, String en, int sr, int rw) {
        challengeNo++;
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
