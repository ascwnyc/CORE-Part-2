package core;

public class Champion {

    private String name;
    private int skillLevel;
    private boolean necromancer;
    private int entryFee;
    private String spellSpeciality;
    private String weapon;
    private boolean talks;
    private ChampionType type;
    private ChampionState state;

    public Champion (String nm, int sl, boolean nc, int ef, String ss, String wp, String tp) {
        name = nm;
        skillLevel = sl;
        necromancer = nc;
        entryFee = ef;
        spellSpeciality = ss;
        weapon = wp;
        chooseType(tp);
        if (type == ChampionType.TALKINGDRAGON) {
            talks = true;
        } else {
            talks = false;
        }
        state = ChampionState.WAITING;
    }

    public String toString() {
        return("\n Name: " + name + "\nSkill Level: " + skillLevel + "\nNecromancer: " + necromancer + "\nEntry Fee: " + entryFee + "\nSpell Speciality: " + spellSpeciality + "\nWeapon: " + weapon + "\nTalks: " + talks + "\nType: " + type.toString() + "\nState: " + state.toString());
    }

    public String getName() {
        return name;
    }

    public ChampionState getState() {
        return state;
    }

    public int getEntryFee() {
        return entryFee;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public ChampionType getType() {
        return type;
    }

    /** Changes the champion's state to that of the string provided (if they are in the team)
     * @param st is the string name of the state to change the champion, the following strings change the states in these ways;
     *  "in reserve" changes champion's state to WAITING meaning the champion is not in the team
     *  "active" changes the champion's state to ACTIVE meaning the champion is in the team
     *  "dead" changes the champion's state to DEAD meaning the champion is now dead
     *
     *
     * @return boolean returns true if the state was altered and false if this fails
     **/
    public boolean alterState(String st) {
        String lowst = st.toLowerCase();
        if (lowst.equals ("in reserve")) {
            state = ChampionState.WAITING;
            return true;
        }

        if (lowst.equals("active")) {
            state = ChampionState.ACTIVE;
            return true;
        }

        if (lowst.equals("dead")) {
            state = ChampionState.DEAD;
            return true;
        }

        return false;

    }

    /** Makes a champion the provided type
     * @param st is the string name of the type to make a champion, the following strings types can be given;
     *  "dragon" makes the champion's type a dragon
     *  "talking dragon" makes the champion's type a dragon which can talk
     *  "wizard" makes the champion's type a wizard
     *  "warrior" makes the champion's type a warrior
     *
     *
     * @return boolean returns true if the state was altered and false if this fails
     **/

    public boolean chooseType(String st) {
        String lowst = st.toLowerCase();
        if (lowst == "dragon") {
            type = ChampionType.DRAGON;
            return true;
        }

        if (lowst == "talking dragon") {
            type = ChampionType.TALKINGDRAGON;
            return true;
        }

        if (lowst == "wizard") {
            type = ChampionType.WIZARD;
            return true;
        }

        if (lowst == "warrior") {
            type = ChampionType.WARRIOR;
            return true;
        }

        return false;

    }

    public String getStateString() {
        if (state == ChampionState.WAITING) {
            return "Waiting";
        }

        if (state == ChampionState.ACTIVE) {
            return "Active";
        }

        if (state == ChampionState.DEAD) {
            return "Dead";
        }

        return "incorrect";
    }

}
