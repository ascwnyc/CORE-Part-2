package core;

import java.io.Serializable;

/**
 * Enumeration class ChampionType - write a description of the enum class here
 *
 */
public enum ChampionType implements Serializable
{
    WIZARD("Wizard"), WARRIOR("Warrior"), DRAGON("Dragon");
    private String type;
    private boolean magic;
    private boolean fight;
    private boolean mystery;

    private ChampionType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type + magic + fight + mystery;
    }
}
