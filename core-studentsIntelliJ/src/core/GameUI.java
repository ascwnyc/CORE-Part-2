package core;
import java.io.*;
import java.util.*;
/**
 * Command line interface
 * 
 * @author A.A.Marczyk
 * @version 20/10/22
 */
public class GameUI
{
    
    private static Scanner myIn = new Scanner(System.in);

    public static void main(String[] args)
    {
        Tournament tr ;
        int choice;
        String playerName;
        String output = "";
        int result = -1; 
        try
        {
            System.out.println("Enter player's name");
            String s = myIn.nextLine();
            //myIn.nextLine();
            tr = new Tournament(s); // create
            choice = 100;
            while (choice != 0 )
            {
                choice = getMenuItem();
                if (choice == 1)
                {
                    System.out.println(tr.getReserve());
                }
                else if (choice == 2)
                {
                    System.out.println(tr.getTeam());
                }
                else if (choice == 3)
                {
                    String nm = "f";
                    while (nm == "f") {
                        System.out.println("Enter champion's name");
                        nm = myIn.nextLine();
                        nm = tr.getChampionDetails(nm);
                        if (nm == "") {
                            System.out.println("Invalid champion name, please try again");
                            nm = "f";
                        }

                    }
                    System.out.println(nm);
                } 
                else if (choice == 4)
                {
                    System.out.println("Enter Champion name");
                    String nme = (myIn.nextLine()).trim();
                    result = tr.enterChampion(nme);
                    // code omitted
                    // display message appropriate to result

                    output = processAddingResult(result);
                    System.out.println("\n" + output + "\nTreasury = " + tr.getMoney());

                }
                else if (choice == 5)
                {
                    System.out.println("Enter number of the challenge");
                    String chal = myIn.nextLine();
                    int number = Integer.parseInt(chal);
                    System.out.println(number);
                    if (tr.isChallenge(number))
                    {
                        result = tr.fightChallenge(number);
                    }
                    System.out.println(result);
                    output = processChallengeResult(result);
                    System.out.println("\n" + output + "\nTreasury = " + tr.getMoney());
                }
                else if (choice==6)
                {
                    String nm = "f";
                    while (nm == "f") {
                        System.out.println("Enter champion's name to retire");
                         nm = (myIn.nextLine()).trim();
                        if (nm == "") {
                            System.out.println("Invalid champion name, please try again");
                            nm = "f";
                        }

                    }

                    output = processRetiringResult(result);
                    System.out.println("\n" + output + "\nTreasury = " + tr.getMoney());
                }  
                else if (choice==7)
                {
                    System.out.println(tr.toString());
                }
//                 else if (choice == 8) // Task 4.4 only
//                 {
//                     System.out.println("Write to file");
//                     tr.saveGame("olenka.txt");
//                 }
//                 else if (choice == 9) // Task 4.4 only
//                 {
//                     System.out.println("Restore from file");
//                     CORE tr2= tr.loadGame("olenka.txt");
//                     System.out.println(tr2.toString());               
//                 }
            }     
        }
        catch (IOException e) {System.out.println (e);}   
        System.out.println("Thank-you");
    }
    
    private static int getMenuItem()throws IOException
    {   int choice = 100;  
        System.out.println("\nMain Menu");
        System.out.println("0. Quit");
        System.out.println("1. List champions in reserve");
        System.out.println("2. List champions in players team"); 
        System.out.println("3. View a champion");
        System.out.println("4. Enter champion into player's team");
        System.out.println("5. Fight a challenge");
        System.out.println("6. Retire a champion");
        System.out.println("7. View game state");
        System.out.println("8. Save this game");
        System.out.println("9. Load this game");
       
        
        while (choice < 0 || choice  > 9)
        {
            System.out.println("Enter the number of your choice");
            choice =  myIn.nextInt();
        }
        myIn.nextLine();
        return choice;        
    }  
    
    private static String processChallengeResult(int res) {
    
        String out;
        if (res ==0)
        {
            out = "Challenge won";
        }
        else if (res ==1)
        {
            out = "Challenge lost on battle skill";
        }
        else if (res ==2)
        {
            out = "Challenge lost as no champion available";
        }
        else if (res ==3)
        {
            out = "Challenge lost. You lose the game ";
        }
        else if (res == -1)
        {
            out = "No such challenge";
        }
        else
        {
            out = "No such result";
        }
        return out; 
    }

    private static String processAddingResult(int res) {

        String out;

        if (res == 0) {
            out = "Champion added to your team and is now ready for battle";
        }

        else if (res == 1) {
            out = "Champion is not in reserves, it cannot be added to your team";
        }

        else if (res == 2) {
            out = "There is not enough gulden in the treasury to add this champion to your team";
        }

        else if (res == -1) {
            out = "The specified champion does not exist and cannot be added to the team";
        }

        else
        {
            out = "No such result";
        }

        return out;
    }

    private static String processRetiringResult(int res) {

        String out;

        if (res == 0) {
            out = "Champion has been retired from your team and is in reserves";
        }

        else if (res == 1) {
            out = "This champion may not be retired as they are dead";
        }

        else if (res == 2) {
            out = "This champion cannot be retired because they are not in the team";
        }

        else if (res == -1) {
            out = "The specified champion does not exist and cannot be retired from the team";
        }

        else
        {
            out = "No such result";
        }

        return out;
    }
}