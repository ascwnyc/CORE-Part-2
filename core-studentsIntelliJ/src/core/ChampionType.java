package core;

import java.io.Serializable;

/**
 * Enumeration class ChampionType - Enumeration on types of Champion.
 *
 */
public enum ChampionType implements Serializable
{
    WIZARD("Wizard", true, true, true), WARRIOR("Warrior", false, true, false), DRAGON("Dragon", false, true, false), TALKINGDRAGON("Talking dragon", false, true, true);
    private final String type;
    private final boolean magic;
    private final boolean fight;
    private final boolean mystery;

    ChampionType(String ty, boolean mc, boolean fg, boolean ms) {
        magic = mc;
        fight = fg;
        mystery = ms;
        type = ty;
    }

    public String toString()
    {
        return "\nType: " + type + "\nMagic?: " + magic + "\nFight?: " + fight + "\nMystery?: " + mystery;
    }
    public boolean isMagic(){
        return magic;
    }
    public boolean isFight(){
        return fight;
    }
    public boolean isMystery(){
        return mystery;
    }

}
