package core;

public class Item {

    // instance variables - replace the example below with your own
    private String name;
    private int skill;
    private int gulden;

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
