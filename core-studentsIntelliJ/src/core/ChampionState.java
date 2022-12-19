package core;

import java.io.*;
import java.util.Locale;

/**
 * Enumeration class ChampionState 
 * Specifies all possible states of a champion
 * 
 * @author A.Marczyk
 * @version 12/10/2022
 */
public enum ChampionState implements Serializable
{
    WAITING("In reserve"), ACTIVE("Active"),  DEAD ("Dead");
    private String state;
    
    private ChampionState(String st)
    {
        state = st;
    }
    
    public String toString()
    {
        return state;
    }


}
