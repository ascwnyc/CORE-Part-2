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

    public Champion (String nm, int sl, boolean nc, int ef, String ss, String wp, boolean tk, ChampionType tp) {
        name = nm;
        skillLevel = sl;
        necromancer = nc;
        entryFee = ef;
        spellSpeciality = ss;
        weapon = wp;
        talks = tk;
        type = tp;
        state = ChampionState.WAITING;
    }

    public String toString() {
        return("\n Name: " + name + "\nSkill Level: " + skillLevel + "\nNecromancer: " + necromancer + "\nEntry Fee: " + entryFee + "\nSpell Speciality: " + spellSpeciality + "\nWeapon: " + weapon + "\nTalks: " + talks);
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
        if (lowst == "in reserve") {
            state = ChampionState.WAITING;
            return true;
        }

        if (lowst == "active") {
            state = ChampionState.ACTIVE;
            return true;
        }

        if (lowst == "dead") {
            state = ChampionState.DEAD;
            return true;
        }

        return false;

    }
}
