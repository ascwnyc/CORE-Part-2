package core;

import java.io.Serializable;

public class Item implements Serializable {

    // instance variables - replace the example below with your own
    private final String name;
    private final int skill;
    private final int gulden;

    /**
     * Constructor for objects of class ITEM
     */
    public Item(String nm, int sk, int gl)
    {
        // initialise instance variables
        name = nm;
        skill = sk;
        gulden = gl;
    }

    public int getSkill() {
        return skill;
    }

    public int getGulden() {
        return gulden;
    }

    public String getName() {
        return name;
    }

}
