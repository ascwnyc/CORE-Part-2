package core;

import java.io.*;
/**
 * Enumeration class ChallengeType - write a description of the enum class here
 * 
 * @author A.A.Marczyk
 * @version 12/10/2022
 */
public enum ChallengeType implements Serializable
{
    MAGIC(" Magic"), FIGHT("Fight"), MYSTERY ("Mystery");
    private String type;
    
    private ChallengeType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type;
    }
}
