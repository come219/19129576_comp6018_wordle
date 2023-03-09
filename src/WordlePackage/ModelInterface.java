/****************   
 *  19129576
 *  COMP6018 Advanced Object Oriented Programming
 *  Wordle Model Interface
 **************/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package WordlePackage;

/**
 *  Model Wordle Interface
 * 
 *  encapsulating the headers of the functions of the model
 *  model of the m.v.c. architecture
 * 
 * @author qqstj, 19192576
 */
public interface ModelInterface {

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
    boolean commonWordsCheck(String input);

    /**
     * get Guess Counter Function
     *
     * @return the integer value of the current guess of the game model
     */
    int getGuessCounter();

    /**
     * Function to return if the input is an invalid word
     * @return invalidWord
     */
    boolean getInvalidWord();

    /**
     * get Random Word of the word
     * @return the chosen word from the list of random words.
     */
    String getRandomWord();

    /**
     * model init Function
     *
     * Wordle Model Init function that calls the relevant operations to initialise
     * the context and game
     */
    void init();

    /**
     * Initialise the Chosen Word Function
     *
     * assigns the chosen random word from the list of random words
     */
    void initChosenWord();

    /**
     *  Game Function to Check User Input
     *
     *  Manages if the user input is valid against the common words
     *  Manages if the user input is valid lengths and feedback to the user.
     * @param input containing the input of the user
     */
    Boolean inputCheck(String input);

    /**
     * read the file for the commons words Function
     *
     * assigns the common words to the arraylist and performs proper error checks and feedback
     */
    void readFile_commontxt();

    /// IMPORTANT FILE PATH TARGET
    /// requiring: 'words.txt' and 'common.txt'
    /// C:\Users\qqstj\Documents\!!brookes year 3 folder\brookes yr 3 sem 2\advancedd oop module\!COURSEWORK FOLDER
    ///
    /**
     * read the file for the random words Function
     *
     * assigns the random words to the arraylist and performs proper error checks and feedback
     */
    void readFile_wordstxt();

    /**
     * set the Guess Counter Function
     *
     * @param val containing the value of the counter
     */
    void setGuessCounter(int val);

    /**
     * Function to check whether any char is in the same index
     */
    void test1_IndexCharCheck(String _input);

    /**
     * Function to check if char matches any char
     */
    void test2_Char2CharCheck(String _input);

    /**
     *  Function check to see if the user input equals the guessed word
     *
     * @param _input containing the user input
     * @return false if not equal but true of the same
     */
    boolean test3_FullCheck(String _input);

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
    boolean validInputChecker(String input);
    
}
