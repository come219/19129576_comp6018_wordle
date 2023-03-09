/****************   
 *  19129576
 *  COMP6018 Advanced Object Oriented Programming
 *  Wordle GUI Main
 **************/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package WordlePackage;  //Wordle Adv. OOP game Package

/**
 * MainGUI Main Class
 * 
 * Main Graphic User Interface Class that encapsulates the whole operation
 * for the Wordle game in the GUI state.
 * 
 * calls Model, Controller and View
 * old: calls runnable thread
 * 
 * @author qqstj, 19129576
 */
public class MainGUI {

    /**
     * Java Main Call Function
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
             
        System.out.print("\n\n"
                + "________________\n"
                + " 19129576\n"
                + " Wordle Game GUI\n"
                + "________________\n");    //Program title & Credit
           
        createThread(); // creates thread and invokes initWordleGUI();    
              
    }

    /**
     * Function Threading function?
     * invoke call to threading function
     * and invoke the gui program
     */
    private static void createThread(){
        
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {    //create runnable
                public void run () {    //run
                    initWordleGUI();       //invoke wordle gui
                
                }
            }
        );
        
    }
    
    
    /**
     *  Main Init Function
     * 
     *  Invoke the different Objects that run the Wordle GUI
     */
    public static void initWordleGUI() {
        
        ModelWordle model = new ModelWordle();  //model for the game model
        ControllerGUI control = new ControllerGUI(model);   //controller to interact with the model
        ViewGUI view = new ViewGUI(model, control); //view to display what the model portrays
                
    } 
   
}
