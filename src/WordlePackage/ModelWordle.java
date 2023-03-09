/****************   
 *  19129576
 *  COMP6018 Advanced Object Oriented Programming
 *  Wordle Model
 **************/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package WordlePackage;  //Wordle Adv. OOP game Package

//Java Libraries
import java.io.File;    //File library
import java.io.FileNotFoundException;   //File Exception Library
import java.util.ArrayList; //ArrayList Library
import java.util.Observable;    //Observable Library
import java.util.Observer;  //Observer Library
import java.util.Random;    //Random Library
import java.util.Scanner;   //Scanner (User Input) Library

/**
 * Wordle Modle class
 * 
 *ss encapsulates the abstract functions of the Wordle game.
 * 
 * contains concrete applications to the abstraction operations of the interface that it implements. 
 * defines the proper application to compare if index or char2char or the word exists.
 * 
 * @author qqstj, 19129576
 */
public class ModelWordle extends Observable implements ModelInterface {
   
    //private String userInput = "";
    
    private static boolean invalidWord = false; //invalid word variable for the user input

    private String chosenWord = "null!"; //chosen word of the random list of 5 letter words, defaulting to null!
    
    private ArrayList<Boolean> return_temp = new ArrayList<>();  //arraylist containing the temp returns if there are no checks passed
    private ArrayList<String> randomWords = new ArrayList<>();   //arraylist containing the list of all the random words 
    private ArrayList<String> acceptedWords = new ArrayList<>(); //arraylist containing the list of all the common/accepted words
    public ArrayList<Boolean> indexChar = new ArrayList<>();    //arraylist containing the index checks
    public ArrayList<Boolean> Char2Char = new ArrayList<>();    //arraylist contating the char2char checks
    
    public static String usedCharacters = "";   //static string containing the combination of used characters
    
    public int i_GuessCounter =0;   //integer value containing the current guess counter of the game and used to identify labels
    
    /**
     * Function to return if the input is an invalid word
     * @return invalidWord
     */
    @Override
    public boolean getInvalidWord() {    
        return invalidWord;   
    }
    
    /**
     * get Guess Counter Function
     * 
     * @return the integer value of the current guess of the game model
     */
    @Override
    public int getGuessCounter() {
        return i_GuessCounter;  //return the integer of the counter
    }
    
    /**
     * set the Guess Counter Function
     *
     * @param val containing the value of the counter
     */
    @Override
    public void setGuessCounter(int val) {
        assert(val<0): "value is less than 0";  //assert val less than 0
        assert(val>=6): "value is greater than 6";  //assert val larger than 6
        i_GuessCounter = val;   //guess becomes val
    }
    
    /**
     * get Random Word of the word
     * @return the chosen word from the list of random words.
     */
    @Override
    public String getRandomWord() {
        return chosenWord;  //return the chosen random word
    }
    
    /**
     * Initialise the Chosen Word Function
     * 
     * assigns the chosen random word from the list of random words
     */
    @Override
    public void initChosenWord() {
        
        Random r = new Random();    //random operation used and defined
        int i_randomWords_Size = randomWords.size();    //size of the list of the random list
        int lowest = 0; //lowest value of random
        int highest = i_randomWords_Size;   //highest random of list size
        int random_integer = r.nextInt(highest - lowest) + lowest;  //create a value from the two ranges
        
        chosenWord = randomWords.get(random_integer);   //find the word using the random value found
        
    }
  
    /**
     * model init Function
     * 
     * Wordle Model Init function that calls the relevant operations to initialise
     * the context and game
     */
    @Override
    public void init( ) {
       
        for(int i = 0; i < 5; i++) {    //loop to add inital false to the arraylist checks
            indexChar.add(false);   //add inital false
            Char2Char.add(false);   //add inital false
        }
        
        readFile_wordstxt();    //read the file of the random words of the text file and store
        readFile_commontxt();   //read and store the list of common words
        
        initChosenWord();   //init chosen word call to assign the random words from the list
        
    }
    
    /// IMPORTANT FILE PATH TARGET
    /// requiring: 'words.txt' and 'common.txt'
    /// C:\Users\qqstj\Documents\!!brookes year 3 folder\brookes yr 3 sem 2\advancedd oop module\!COURSEWORK FOLDER
    ///
    
    /**
     * read the file for the random words Function
     * 
     * assigns the random words to the arraylist and performs proper error checks and feedback
     */
    @Override
    public void readFile_wordstxt(){
        try {   //try-catch statement if file does not exist
            File myObj = new File("C:\\Users\\qqstj\\Documents\\!!brookes year 3 folder\\brookes yr 3 sem 2\\advancedd oop module\\!COURSEWORK FOLDER\\Files needed for CW-20220202\\words.txt");   //file words.txt path defined
            Scanner myReader = new Scanner(myObj);  //scanner called to read the file
            
            while (myReader.hasNextLine()) {    //while loop to go through the words file
                String data = myReader.nextLine();  //data becomes the current line of the file
                //System.out.println(data); //print outputs
                randomWords.add(data);  //add the data to the corresponding arraylist
            } myReader.close();  //close the file
            
        } catch (FileNotFoundException e) { //catch statement if the file was not found
            System.out.println("An error occurred.");   //output error occured
            e.printStackTrace();    //print stack trace
        
        }   System.out.println("Read words.txt");   //print word text read if successful
        
    }
    
    /**
     * read the file for the commons words Function
     * 
     * assigns the common words to the arraylist and performs proper error checks and feedback
     */
    @Override
    public void readFile_commontxt(){
        try {
            File myObj = new File("C:\\Users\\qqstj\\Documents\\!!brookes year 3 folder\\brookes yr 3 sem 2\\advancedd oop module\\!COURSEWORK FOLDER\\Files needed for CW-20220202\\common.txt"); //file common.txt path defined            
            Scanner myReader = new Scanner(myObj);  //scanner called to read the file
            
            while (myReader.hasNextLine()) {    //while loop to go through the common file  
                String data = myReader.nextLine();  //data assigned by the next line of the file
                //System.out.println(data); //display output
                acceptedWords.add(data);    //add data to arraylist of accepted words
            } myReader.close();  //close file
            
        } catch (FileNotFoundException e) { //catch statement if file not found
                System.out.println("An error occurred.");   //output error occured  
                e.printStackTrace();    //print stack trace
        
        } System.out.println("Read common.txt"); //print read commons text if successfull
        
    }
    
    /**
     * Model Wordle Constructor
     * 
     * calls the init operation to initialise the model
     */
    public ModelWordle() {
        init(); //init operation call
    }
    
    
    /**
     *  Game Function to Check User Input
     * 
     *  Manages if the user input is valid against the common words
     *  Manages if the user input is valid lengths and feedback to the user.
     * @param input containing the input of the user
     */
    @Override
    public Boolean inputCheck(String input){
        assert(input==null): "input is null"; //assert input check
        
        if(validInputChecker(input) == true) { //if the valid input checker of input returns true
                  if(commonWordsCheck(input) == true) { //if the commonwords of the input returns true
                return true;    //then return proper word. and continue state
                
            } else{ //else out if not valid or not in common
                return false;   //return use valid words
            }
        }
        return false; //re input
    }
   
    /**
     * Boolean Return Function
     * TO check if the inputted word is valid
     * 
     * Checks for if the input is == 5, not less, not more
     * Acts and responds appropriately
     * 
     * @param input containing the user input
     * @return true if not returned false
     */
    @Override
    public boolean validInputChecker(String input) {
         assert(input==null): "input is null"; //assert input check
         assert(input.length()<=4 && input.length()>=6): "invalid input is: " + input;   //assert input length not 5
         assert(input.length()==5): "nice input is: " + input;   //input is proper
        
        char emptychar = '¬';   //empty char character
        
        if(input.length() <= 4) {   //If the input length is smaller than 5
            System.out.println("\nPlease enter 5 (FIVE) Characters only.");
            return false;   //return false if invalid format input, less than 5
        }
        
        if(input.length() > 5) { //If the input length is greater than 5                            
            System.out.println("\nPlease enter 5 (FIVE) Characters only.");
            input = input.substring(0,5);   //cut the characters -->> need to remove 
            return false;   //return false if invalid format input, greater than 5
        }
        return true;    //All checks are passed and responded to, return true valid input
        
    } 
    
    /**
     * Common Words  Function checker
     * 
     * for loop to search through array list of common
     * check if true or false
     * also checks through the randomwords too
     * 
     * if any input true returns true
     * 
     * checks through arraylist if no true then return false
     * @param input containing the user input defined
     * @return holder, false if none of the checks were passed returned true
     */
    @Override
    public boolean commonWordsCheck(String input) {
        assert(input==null): "input is null"; //assert input check
        
        return_temp.clear();    //return temp clear to begin checks so that it is not using previous checks
        
        boolean holder = false; //if input is equal to any common return true
        
        for(int i = 0; i < acceptedWords.size(); i++) { //loop to go through the accepted words/common list
          
            if(input.equals(acceptedWords.get(i))){ //if input equals a word in the accepted list
                                
                System.out.println("Using (common) accepted word");  //print using common words output
                return_temp.add(Boolean.TRUE);  //add to return temp that it is true
                return true;    //return true that it is a valid input       
            }    
        }            
        for(int j = 0; j < randomWords.size(); j++) {   //loop to go through the list of random words list too
   
            if(input.equals(randomWords.get(j))){   //if input equals a word from the randoms word list
                                
                System.out.println("Using (random) accepted word"); //print out put using random accepted word output
                return_temp.add(Boolean.TRUE);  //add to return temp that it is true
                return true;    //return true that it is a valid input
                
            }
        }
        if(!return_temp.contains(true)) {   //if the return temps do not contain trues
                System.out.println("Please enter an accepted word");    //print output to use accepted word
                invalidWord = true; //set invalid word to true for the model
                return false;   //return false as the input is invalid
        }
        return holder;  //return holder
    }
   
    /**
     * Function to check whether any char is in the same index
     */
    @Override
    public void test1_IndexCharCheck(String _input) {
      assert(_input==null): "input is null"; //assert input check
        for(int i = 0; i < getRandomWord().length(); i++ ) {    //loop through the chosen random word
          
            if(_input.charAt(i) == getRandomWord().charAt(i)) { //compare if the index char is true
                //System.out.println("\nCongrats, you have found an index char!");  //print output that index char found
                indexChar.set(i, true); //set arraylist that index char at i is true          
            } else {    //else out  
                indexChar.set(i, false);    //set arraylist that index char at i is false
            }   
       }

    }
    
    /**
     * Function to check if char matches any char
     */
    @Override
    public void test2_Char2CharCheck(String _input) {
      assert(_input==null): "input is null"; //assert input check
        for(int i = 0; i < getRandomWord().length(); i++) { //loop through the chosen random word
            for(int j = 0; j < getRandomWord().length(); j++) { //loop through chosen random word to get other dimension of word
                
                if(_input.charAt(i) == getRandomWord().charAt(j)) { //if input char 2 char matches at j
                     //System.out.println("\nCongrats, you have found a char 2 char!"); //print output that char 2 char has been found
                     Char2Char.set(i, true);    //set arraylist that char2char at i is true
                } else {    //else out
                    Char2Char.set(i, false);    //set arraylist that char2char at i is false
                }
            }
        }
       
    }
    
    
    /**
     *  Function check to see if the user input equals the guessed word
     * 
     * @param _input containing the user input
     * @return false if not equal but true of the same
     */
    @Override
    public boolean test3_FullCheck(String _input) {
        assert(_input==null): "input is null"; //assert input check
            if(_input.equals(getRandomWord())) {    //if the input matches the random word
                System.out.println("\nCongrats, you have guessed it!! : " + getRandomWord());   //print output that the user has guess it
                return true;    //return true
            }
        
        return false;   //return false
    }
    
}
