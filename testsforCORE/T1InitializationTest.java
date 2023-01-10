/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import core.*;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author comqaam
 */
public class T1InitializationTest {
    CORE game;
    
    public T1InitializationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Tournament("Olek");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    //just a local method to check a String for contents
    private boolean containsText(String text, String[] s) {
        boolean check = true;
        for(int i=0; i < s.length; i++)
        check = check && text.contains(s[i]);
        return check;
    }
    
    @Test
    public void gameCorrectlyInitialised() {
        String result = game.toString();
        String[] xx = {"Olek","1000", "Is OK", "No champions"};
        boolean actual = containsText(result,xx );
        System.out.println(actual);
        assertTrue(actual);
    }
    
    @Test
    public void treasuryTest() {
        int expected = 1000;
        int actual = game.getMoney();
        assertEquals(expected,actual);
    }
    
    @Test
    public void defeatedTest() {
        boolean actual = game.isDefeated();
        assertFalse(actual);
    }
    
    @Test
    public void checkTeamEmptyAtStart() {
        boolean result = true;
        List<String> champs = new ArrayList<String>(Arrays.asList("Ganfrank","Rudolf",
                 "Elblond","Flimsi, *Drabina", "Golum","Argon", "Neon", "Xenon"));

        for (String chmp : champs) {
            result = result && !game.isInPlayersTeam(chmp);
        }
        assertTrue(result);
    }

}
