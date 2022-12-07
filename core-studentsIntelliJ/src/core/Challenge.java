package core;

public class Challenge {

    private int challengeNo;
    private String type;
    private String enemy;
    private int skillRequired;
    private int reward;

    public Challenge(int cn, String ty, String en, int sr, int rw) {
        challengeNo = cn;
        type = ty;
        enemy = en;
        skillRequired = sr;
        reward = rw;
    }

    public String toString() {
        return("\n Challenge Number: " + challengeNo + "\nType: " + type + "\nEnemy: " + enemy + "\nSkill Required: " + skillRequired + "\nReward: " + reward);
    }
}
