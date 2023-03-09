/****************   
 *  19129576
 *  COMP6018 Advanced Object Oriented Programming
 *  Wordle CLI Main
 **************/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WordlePackage; //Wordle Adv. OOP game Package

/**
 * MainCLI Main Class
 * 
 * Wordle Command Line Interface Program that encapsulates the CLI operation
 * 
 * calls the model and view-controller
 * old: calls runnable thread
 * 
 * @author qqstj, 19129576
 */
public class MainCLI {
    
    
    /**
     * Java Main Call Function
     * @param args input/output arguments of the program
     */
    public static void main(String[] args) {
        
        System.out.print("\n\n"
                + "________________\n"
                + " 19129576\n"
                + " Wordle Game CLI\n"
                + "________________\n");    //Program title & Credit
        
        createThread(); // creates thread invokes initWordleCLI(); 
            
    }
    
    /**
     * Function Threading function
     * to call to threading function
     * and invoke the progam
     */
    private static void createThread(){
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {    //create runnable
                public void run () {    //run
                    initWordleCLI();        //invoke wordle cli
                
                }
            }
        );
        
    }
    
    /**
     *  Main Init Function
     * 
     *  Invoke the Different Objects that run Wordle CLI
     */
    private static void initWordleCLI() {
        ModelWordle model = new ModelWordle();    //model of the wordle game
        ViewControllerCLI viewcontrol = new ViewControllerCLI(model);   //view-controller using model
    }
    
}
