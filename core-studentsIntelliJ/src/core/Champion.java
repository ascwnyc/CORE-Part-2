package core;

public class Champion {

    private String name;
    private int skillLevel;
    private boolean necromancer;
    private int entryFee;
    private String spellSpeciality;
    private String weapon;
    private boolean talks;

    public Champion (String nm, int sl, boolean nc, int ef, String ss, String wp, boolean tk) {
        name = nm;
        skillLevel = sl;
        necromancer = nc;
        entryFee = ef;
        spellSpeciality = ss;
        weapon = wp;
        talks = tk;
    }

    public String toString() {
        return("\n Name: " + name + "\nSkill Level: " + skillLevel + "\nNecromancer: " + necromancer + "\nEntry Fee: " + entryFee + "\nSpell Speciality: " + spellSpeciality + "\nWeapon: " + weapon + "\nTalks: " + talks);
    }

    public String getName() {
        return name;
    }
}
