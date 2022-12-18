package core;

import java.io.Serializable;

/**
 * Enumeration class ChampionType - write a description of the enum class here
 *
 */
public enum ChampionType implements Serializable
{
    WIZARD("Wizard", true, true, true), WARRIOR("Warrior", false, true, false), DRAGON("Dragon", false, true, false), TALKINGDRAGON("Talking dragon", false, true, true);
    private String type;
    private boolean magic;
    private boolean fight;
    private boolean mystery;

    private ChampionType(String ty, boolean mc, boolean fg, boolean ms) {

        magic = mc;
        fight = fg;
        mystery = ms;
        type = ty;
    }

    public String toString()
    {
        return type;
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
