package core;

import java.io.Serializable;

/**
 * Enumeration class ChampionType - write a description of the enum class here
 *
 */
public enum ChampionType implements Serializable
{
    WIZARD("Wizard", false), WARRIOR("Warrior", false), DRAGON("Dragon", false);
    private String type;
    private boolean magic;
    private boolean fight;
    private boolean mystery;

    private ChampionType(String ty, boolean tk)
    {
        ty = ty.toLowerCase();

        if (ty == "wizard") {
            magic = true;
            fight = true;
            mystery = true;
        }

        if (ty == "warrior") {
            magic = false;
            fight = true;
            mystery = false;
        }

        if (ty == "dragon") {
            magic = false;
            fight = true;
            if (tk == true) {
                mystery = true;
            }
            else {
                mystery = false;
            }
            }
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
