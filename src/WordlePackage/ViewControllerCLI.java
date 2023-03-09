/****************   
 *  19129576
 *  COMP6018 Advanced Object Oriented Programming
 *  Wordle CLI View-Controller
 **************/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WordlePackage; //Wordle Adv. OOP game Package

//Java Imported CLI Libraries
import java.util.ArrayList; //ArrayList Library
import java.util.List;  //List Library
import java.util.Scanner;   //Scanner library, user input

/**
 *  ViewControllerCLI Class
 * 
 *  The View-Controller of the Command Line Interface for the Wordle Model game.
 * 
 *  enables the user to interact with the wordle model game through the terminal.
 *  displays the functionalities of the game in its simplest form.
 * 
 * @author qqstj, 19129576
 */
public class ViewControllerCLI {
    
    private final ModelWordle model;    //final variable containing the Wordle Model
    
    private Scanner user_scan;  //user input scanner variable
    
    private static boolean isRunning = true;    //running system functional variable
    private static boolean bool_CorrectWord = false;    //correct word variable function --> obsolete?
    private static Boolean validInputCheck; //valid input check variable --> obsolete?
    
    //redundant: private String final_user_input = "";   //string variable to cut user input and hold the final input
    private static String user_input = "";  //string variable to hold the whole user input
   
    /**
     * ViewControllerCLI Constructor
     * 
     * constructor of the view-controller cli containing the model
     * 
     * calls the initialising function and runs the game.
     * 
     * @param model containg Wordle game model 
     */
    public ViewControllerCLI(ModelWordle model) {
        
        this.model = model; //model variable set
        init(); //initalise and init function call
       
        while(isRunning) {      //game while funtional loop
            
            showCurrentGuess(); //Perform Guess View Operation
            userInput();        //Perform User Input Operation
            System.out.println("You inputted: " + user_input); //feedback to the user what they had inputted
          
            if(validInputCheck == true) {  //User Input Valid Check, perform game logic below
                             
                if(this.model.test3_FullCheck(user_input) == true){ //model check guess for word
                    bool_CorrectWord = true;    //user has inputted the correct word
                }
                   
                model.test1_IndexCharCheck(user_input); //model object use test 1 -- return bool 
                 
                model.test2_Char2CharCheck(user_input); //model object use test 2
                              
                if(bool_CorrectWord) {  //Checks if the user has inputted the correct word
                    runWinState(); // run winner state.
                   
                }
                
                //if greater than 6, call lose state
            }                           
            model.setGuessCounter(model.getGuessCounter() + 1); //model increment the guess counter after all the valid operations
            
            if(model.getGuessCounter() >= 6){
                runLoseState();
            }
        }   // !! end bracket for functional while loop 
       
    }
    
     /**
     * Function to enter the program win state
     */
    private void runWinState() {
        showCurrentGuess(); //Perform Guess View Operation, to show the guess
        System.out.println("\n");   //extra line, user has won at this point
        setRunning(false);  //set running to false.
    }
    
    /**
     * Function to enter the program lost state
     */
    private void runLoseState() {
        System.out.println("\nUnfortunately, you have run out of guesses.");
        setRunning(false);  //set running to false.
    }
        
  
    /**
     * Function to show the Current Guess
     * Essentially, this is the CLI View Class
     * 
     * Will try to show if [?] unknown
     *                  if |x| if c2c
     *                  if  x  known
     */
    private void showCurrentGuess() {
        
        //Intention:
        // print for each square
        // print accourding to yellow or green square
 
        //System.out.println("Level X: [?] [?] [?] [?] [?]");    // model print that was used to create this output
        
        System.out.print("Level " + model.getGuessCounter() + ": ");    //prints the level and the current guess

        char temp;  //temp containing the char at index check
         
        int looplength = 5; //loop length if the variable is less than 5
        
        if(!user_input.isEmpty()) { //if the input is not empty, probably less than 5
            looplength = user_input.length();   //loop length is same as length of input to draw properly
        }
        
        for(int i = 0; i < looplength; i++) {   //loop to draw all the user input in the format that shows the guesses
           
            String output = ""; //string containing the building output.
           
            if(model.indexChar.get(i).equals(true)) {   //first check for index checks, as it is priority
                
                temp = model.getRandomWord().charAt(i); //temp becomes the char at random word --> redundant as the user input is alread the same
                output = (" " + user_input.charAt(i)+ "  ");    //ouputput becomes char of the whole user input
                
            }  else if(model.Char2Char.get(i).equals(true)) {   //checks for char 2 char --> model output = ("|" + "X" + "| ");
               
               output = ("|" + user_input.charAt(i) + "| ");    //output of the char2char with |x|
            
            } else {    //if no checks are made, the output is given as the user input with the brackets to indicate that they have found no info
                output = "[" + "?"+ "] ";   //default no input
                
                if(!user_input.isEmpty()) { //if there is user input
                    output = "[" + user_input.charAt(i)+ "] ";  //output of the no info input 
                }
            }
            // OLD ELSE IF CODE THAT WAS USED BUT  CHANGED TO LEGACY, used for reference
            //            else  if ((model.indexChar.get(i) == false) && (model.Char2Char.get(i) == false) == true) {
            //                output = "[?] ";
            //            }

            System.out.print(output);   //print the output
        }
        
    }
    
    /**
     * ViewControllerCLI Init Function
     * 
     * calls the test code to show the random word
     * prints the tile of the game
     * and defines the user input variable scanner with the system in to be ready
     */
    private void init() {
        
        System.out.println("Random word: " + model.getRandomWord()); //test print randomWord
        System.out.println("\n\n    Command Line Interface: Wordle");   //print program title
        user_scan = new Scanner(System.in); //initalise the user scan to get system input
        
    }

    /**
     *  User Input Function
     * 
     *  Essentially, ( CLI Controller )
     * 
     *   allows the user to input their word into the game
     *  recursive to itself to call if the user input the incorrect words/format
     */
    private void userInput() {
        
        System.out.println("\nEnter your guess: "); //prints the prompt the input the word
        user_input = user_scan.next(); //user input string varible becomes what the user inputs using the scanner
     
        if(model.inputCheck(user_input).equals(true)){    //if the valid input check from the model is true
            validInputCheck = true; //assign the valid input check in this state
        } else {
            validInputCheck = false;    //assign the valid input check false
            userInput(); //recurse itself as the input was not in format
           
        }
       
    }
    
    /**
     *  Function to change the run state of the game... 
     *
     * @param value value that changes the state of isRunning
     * @return isRunning is false probably.
     */
    private boolean setRunning( boolean value) {
        
        return isRunning = value;   //operation to close the System
    }
    
    
    
    
    

    

    
    
    
    
    
}
