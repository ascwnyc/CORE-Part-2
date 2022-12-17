package core;

public class Challenge {

    private static int challengeNo = 0;
    private String type;
    private String enemy;
    private int skillRequired;
    private int reward;

    public Challenge(String ty, String en, int sr, int rw) {
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
}
