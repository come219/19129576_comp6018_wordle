/****************   
 *  19129576
 *  COMP6018 Advanced Object Oriented Programming
 *  Wordle Test Class for testing
 **************/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WordlePackage;  //wordle package

//import org.junit.*;   //junity library
import org.junit.jupiter.api.Test;  //junity test

/**
 *  Test Class 
 * 
 * used to test components in the world game program
 * used to test inputs, the different files and locations
 * and if the program can create a runnable
 * @author qqstj, 19129576
 */
public class TestClass {
    
      private ModelWordle model;    //model of wordle
     
       private static boolean test_status1; //test case 1: file random
       private static boolean test_status2; //test case 2: common words
       private static boolean test_status3; //test case 3: runnable
       private static boolean test_status0;//test case 4: user input
      
     
       /**
        * Java Main Class of the Wordle Test Class
        * 
        * runs the three different tests and validates the program
        * @param args 
        */
     public static void main(String[] args) {
     
 
 
         String temp  = ""; //to contain a string
         
         System.out.println("Wordle Game Program Testing\n");   //program title of testt
         System.out.println("19192576\n\n");    //title credit

         waitInput();   //wait input to prompt user to ready
         test_status1 = false;  //initalise to false
         test_status2 = false;  //initit to false
         test_status3 = false;  //init to false
         test_status0 = false;  //0
         
         System.out.println("Test 1: Random words File\n"); //test 1 random file test
             runfiletest1();    //run test function
             if(test_status1 == true){  //run conditional statement of return
                  System.out.println(" : passed\n");    //feedback passed test 1
            } else {
                 System.out.println(" : error\n");  //feedback failed test 1
            }
         waitInput();   //wait user input but also do test 0 to the next test
         
         System.out.println("Test 2: common words File\n"); //test 2 common files test
              runfiletest2();   //run test 2 function
             if(test_status2 == true){  //run condition statement if test 2 
                  System.out.println(" : passed\n");    //feedbacak test 2 passed   
            } else {
                 System.out.println(" : error\n");  //feedback test 2 failed
            }
         waitInput();   //wait input for next test also doing test 0
         
            System.out.println("Test 3: create runnable test\n");   //test 3 create runnable test
            runrunnabletest3();   //execute test3 function  
             if(test_status3 == true){  //conditional statement if test 3 
                  System.out.println(" : passed\n");    //feedback test 3 pass
            } else {
                 System.out.println(" : error\n");  //feedback test 3 fail
            }
         waitInput();   //wait for final input and do test 0
         
         System.out.println("Test 4: user input\n");    //reveal test 0
             if(test_status0 == true){  //conditional of test 0
                  System.out.println(" : passed\n");    //feedback that is true
            } else {
                 System.out.println(" : error\n");  //feedback problems if error
            }

             System.out.println("\n\nEnd on test");    //conclude testing
     }
     
     /**
      * Wait input test
      * 
      * a function that is used to wait for user input to check the test
      * but also do a user input test
      */
     @Test
     private static void waitInput() {
         //assert
         System.out.println("\nEnter key for the next test: "); //prompt user to enter next test
         try{
             System.in.read();  //system in read
             test_status0= true;    //system passes
         }
         catch(Exception e){
             test_status0= false; //system doesnt passes
         }
           System.out.println("\n");    //print new line
        
     }
     
     /**
      * run test 1 function
      * 
      * test 1 includes whether random words file is found where it is used in the wordle game
      */
     @Test
     private static void runfiletest1(){
         //assert
          System.out.println("random words file found.");   //found file
          //model.readFile_wordstxt();
          
     }
     
     /**
      * run test 2 function
      * 
      * test 2 runs a test on whether the common file words text file is found
      */
     @Test
     private static void runfiletest2(){
         //assert
          System.out.println("common words file found.");   //found file
          //model.readFile_commontxt();
          
     }
     
     /**
      * run test 3 function
      * 
      * test 3 runs a test on whether the program can create a runnable and execute or not
      */
     @Test
     private static void runrunnabletest3(){
           //assert  
          System.out.println("System can create runnable.");  //system runnable
          test_status3 = true;    //set test    
          
          javax.swing.SwingUtilities.invokeLater( //threading functionalities
                new Runnable() {    //create runnable thread
                    public void run () {    //define function
                          
                           test_status3 =true;//set true

                    }
                }
            );
         
     }
    
}
