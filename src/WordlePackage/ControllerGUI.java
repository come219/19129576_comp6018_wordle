/****************   
 *  19129576
 *  COMP6018 Advanced Object Oriented Programming
 *  Wordle GUI Controller
 **************/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package WordlePackage;  //Wordle Adv. OOP game Package

import java.util.ArrayList; //Java Arraylist libray

/**
 *  Controller GUI  Class
 * 
 *  controller graphical user interface
 *  encapsulates the controller of the m.v.c. architecture
 * 
 * @author qqstj, 19129576
 */
public class ControllerGUI {
    
    private final ModelWordle model;    //wordle model in context
       
    private String user_input = "";     //String containing the user input
    
    public ArrayList<String> array_Inputs;  //ArrayList containing the list of user inputs

    /**
     * Controller GUI constructor
     * 
     * encapsulates the object of the controller of the m.v.c.
     * @param model containing the model of the Wordle game
     */
    public ControllerGUI(ModelWordle model) {
        this.model = model; //this model in context
        initController();   //init call the controller operations
        
    }
    
    /**
     * init Controller Functionalities
     */
    private void initController() {
                
         array_Inputs = new ArrayList<String>();    //array of inputs defined
         array_Inputs.add("[?] [?] [?] [?] [?]");   //preliminary input defined and added
       //array_Inputs.add("2222");
    }
    
    /**
     * set User String Function
     * 
     * sets the user input variable to the input defined by the function
     * @param _input containing the user input from which it was called
     */
    public void setUserString(String _input) {
        user_input = _input;    //set user input to the input
    }
    
    /**
     * get User String Input Function
     * 
     * returns the string of the user input as a reference
     * @return user_input as defined
     */
    public String getUserInput() {
        return user_input;  //return current input of the user
    }
    
    /**
     * user Submit input function
     * 
     * submits the user input and calls the relevant function to set the string
     * also add the input to the array of inputs
     * 
     * @param _input containing the user input defined
     */
    public void UserSubmitInput(String _input) {
        setUserString(_input);  //call string that sets user input to input
        array_Inputs.add(_input);   //adds user input to arraylist
        
    } 
    
}
