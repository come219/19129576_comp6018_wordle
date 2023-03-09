/****************   
 *  19129576
 *  COMP6018 Advanced Object Oriented Programming
 *  Wordle GUI View
 **************/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WordlePackage;  //Wordle Adv. OOP game Package

//Java Imported Libraries
import java.awt.Color;  //Java stanard colours (color) library
import java.awt.Component;  //Java standard component library
import java.awt.Container;  //Java container library
import java.awt.Dimension;  //Java Dimension library
import java.awt.FlowLayout; //Java FlowLayout library
import java.awt.Font;   //Java Font library
import java.awt.GridLayout; //Java GridLayout library
import java.awt.event.ActionEvent;  //Java Event ActionEvent library
import java.awt.event.ActionListener;   //Java Event ActionListenser library
import java.util.ArrayList; //Java ArrayList library
import java.util.Observable;    //Java Observable library
import java.util.Observer;  //Java Observer library
import java.util.regex.Matcher; //Java regex Matcher Util library
import java.util.regex.Pattern; //Java regex Pattern Util library
import javax.swing.Action;  //Java Swing Action library
import javax.swing.BoxLayout;   //Java Swing BoxLayout library
import javax.swing.JButton; //Java swing JButton library
import javax.swing.JFrame;  //Java swing JFrame library
import javax.swing.JLabel;  //Java swing JLabel library
import javax.swing.JOptionPane; //Java swing JOptionPane library
import javax.swing.JPanel;  //Java swing JPanel library
import javax.swing.JTextField;  //Java swing JTextField library

/**
 *  ViewGUI Class
 * 
 *  View of the model view controller architecture
 *  aims to display what is portrayed in the model
 * 
 * @author qqstj, 19129576
 */
public class ViewGUI implements Observer {
   
    private final ModelWordle model;    //model of the wordle game
    
    private final ControllerGUI controller; //controller that interacts with the model
    
    private JFrame frame;   //JFrame frame variable
    
    private JPanel Panel_Title; //JPanel of the Title section
    private JPanel guessPanel;  //JPanel of the Guesses section
    private JPanel controlPanel;    //JPanel of the controllers, buttons
    private JPanel keyboardPanel;   //JPanel of the keyboard usage
    
    
    private JButton enterButton;    //JButton of the enter button for the user to input their entry
    private JTextField TextField_user_input;    //JTextField to hold the user input entry
    //private ArrayList<JButton> albetButton[]; //alphabet button array
    
    private JLabel Label_randomword = new JLabel("null!");  //JLabel of the random word to display  
    private JLabel Label_title = new JLabel("Wordle Game, Adv. OOP");   //JLabel of the program title
    private JLabel Label_credit = new JLabel("  developed by 19129576");    //JLabel of the credits
    private JLabel user_prompt_Label = new JLabel("Enter your guess: ");    //JLabel to prompt user input
    private JLabel Label0;  //JLabel on row 0 of the guess, replaced after 5
    private JLabel Label1;  //JLabel on row 1 of the guess, replaced after 6
    private JLabel Label2;  //JLabel of row 2 of the guess, ''
    private JLabel Label3;  //JLabel of row 3 of the guess, ''
    private JLabel Label4;  //JLabel of row 4 of the guess, ''
    private JLabel Label5;  //JLabel of row 5 of the guess, '': the last guess
    //arraylist of type jlabels...
   
    private static final Dimension TITLE_SIZE = new Dimension(200,100); //Title panel Dimension definition
    private static final Dimension PANEL_SIZE = new Dimension(200,500); //general panel Dimension definition
    
    //private ColorPanel pane;  //old color panel code --> call colours in this class
    
    private static Boolean validInputCheck; //boolean holding local valid input check of (format and commons)
   
    /**
     * ViewGUI Constructor
     * 
     * calls the model and the controller in the context
     * adds the model to the observer
     * init the view call and update the model
     * 
     * @param model model of the Wordle Game
     * @param controller controller of the game
     */
    public ViewGUI(ModelWordle model, ControllerGUI controller) {
        
        this.model = model; //Wordle model in the context
        this.controller = controller;   //Wordle controller in the context
        model.addObserver(this);    //model added to the observer      
        initView(); //init view function call
        update(model, null);    //update the model 
   
    }
    
    /**
     * initView Function Call
     * 
     * initialises the view and call relevant corresponding operations
     * 
     * defines the frame and container that will contain the Panes
     * calls and adds the panels: title, guess, control, keyboard
     */
    private void initView() {
        
        frame = new JFrame("Wordle Game - 19129576");   //JFrame containing the program frame and content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //frame closes on close operation
        frame.setSize(600, 800);    //set the frame size
        
        Container contentPane = frame.getContentPane(); //define the container that the frame will display
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));    //define container layout
        
        initTitlePanel();   //initalise Title Panel
        contentPane.add(Panel_Title);   //add title panel
        initGuessPanel();   //initialise Guess Panel
        contentPane.add(guessPanel);    //add content panel
        initControlPanel(); //initialise Control Panel
        contentPane.add(controlPanel);  //add control panel
        initKeyboardPanel();    //Initialise Keyboard panel
        contentPane.add(keyboardPanel); //add keyboard panel
        
        //frame.pack(); //redundant comprising frame code
        
        frame.setResizable(false);  //set operation resizable to false
        frame.setVisible(true); //set the frame visible
         
    }
    
    /**
     * Init function for title panel 
     */
    private void initTitlePanel() {
        Panel_Title = new JPanel(); //Title Panel definition
        Panel_Title.setLayout(new FlowLayout());    //panel layout as a flow
        Label_title.setFont(new Font("Verdana",1,20));  //title label font set
        Panel_Title.add(Label_title);   //panel add title label
        Panel_Title.add(Label_credit);  //panel add credits label
        Label_randomword.setText(model.getRandomWord());    //label set the text of the label to the random word
        Label_randomword.setForeground(Color.RED);  //set the random word colour red
        Panel_Title.add(Label_randomword);  //title panel add the random word
        Panel_Title.setPreferredSize(TITLE_SIZE);   //set panel size to title dimension size
        
    }
    
    /**
     * init function for showing guesses
     * 
     * prints and assigns all the labels if they are valid
     * prints all the way to 4 from 0 and then replaces at 0
     * 
     * initially, at 0 will show: [?}[?][?][?][?]
     * and no other labels will show
     */
    private void initGuessPanel() {
        
         guessPanel = new JPanel(); //guess Panel definition
         guessPanel.setLayout(new BoxLayout(guessPanel, BoxLayout.Y_AXIS)); //guess panel layout set as box on the y axis
         Label1 = new JLabel("");   //init label1
         Label2 = new JLabel("");   //init label2
         Label3 = new JLabel("");   //init label3
         Label4 = new JLabel("");   //init label4
         Label5 = new JLabel("");   //init label5
         
         // Redundant Label Output formula: 
         //Label_Guess_Output = new JLabel("Level " + model.getGuessCounter() + ": " + controller.getUserInput());
         //Label_Guess_Output = new JLabel("Level " + 0 + ": " + array_Inputs.get(0));
         
         for(int i = 0; i < controller.array_Inputs.size(); i++) {  //loop to display all the labels if not null and what the user inputted
             
             Label0 = new JLabel("Level " + i + " : " + controller.array_Inputs.get(i));    //label 0 init
             guessPanel.add(Label0);    //guess panel label 0
             
              if(Label1 != null) {  //if label 1 is not null
                  guessPanel.add(Label1);   //add label 1 to guess panel
              }
              if(Label2 != null) {  //if label 2 is not null
                  guessPanel.add(Label2);   //add label 2 to guess panel
              }
              if(Label3 != null){   //if label 3 is not null                  
                  guessPanel.add(Label3);   //add label 3 to guess panel
              }
              if(Label4 != null){   //if label 4 is not null                 
                  guessPanel.add(Label4);   //add label 4 to guess panel
              }
              if(Label5 != null){   //if label 5 is not null                 
                  guessPanel.add(Label5);   //add label 4 to guess panel
              }
              
        }
         
        Label0.setFont(new Font("Verdana",1,30));   //label 0 font set
        Label1.setFont(new Font("Verdana",1,30));   //label 1 font set
        Label2.setFont(new Font("Verdana",1,30));   //label 2 font set
        Label3.setFont(new Font("Verdana",1,30));   //label 3 font set
        Label4.setFont(new Font("Verdana",1,30));   //label 4 font set
        Label5.setFont(new Font("Verdana",1,30));   //label 4 font set
        
        guessPanel.setPreferredSize(PANEL_SIZE);    //guess panel set general dimension size
        guessPanel.setAlignmentX(Component.CENTER_ALIGNMENT);   //panel aligned in the center
        guessPanel.setAlignmentY(Component.TOP_ALIGNMENT);  //panel aligned in the top
        
    }
    
    /**
     * Create Label Function
     * 
     * used to create the corresponding labels using the counter to target
     * and the text to set the label to change from null and show
     * 
     * @param counter holds the target variable for which label
     * @param text contains the text that the label will show
     */
    private void createLabel(int counter, String text) {
        
        
        if(counter >= 10) {  //if label loop execeeded error
                    counter = counter - 10;  //remove excess labels and replace
        }
        if(counter >= 6) {  //if label loop execeeded
            counter = counter - 6;  //remove excess labels and replace
        }
        
        char temp;  //character to compare the input and random word
       
        String output = ""; //output to be provided after the operation complete
       
        for(int i = 0; i < 5; i++) {    //loop to add all char outputs to final word
           
            if(model.indexChar.get(i).equals(true)) {   //first check for index, as priority
                
                temp = model.getRandomWord().charAt(i); //temp becomes the char at the random word: which is redundant
                output += (" " + text.charAt(i) + "  " );   //output becomes a clear char of the random word but of the user input
                
            }else if(model.Char2Char.get(i).equals(true)) { //if char 2 char check is true
               output += ("|" + text.charAt(i) + "| "); //output shows |x| that the char 2 char exists
               
            }else { //else out, all others false
               output += "[" + (text.charAt(i)) + "] "; //output show [x], no information
            }
        }
       
        text = output;  //text cleanly becomes output
        
        if(counter == 0) {  //if counter is 0
        Label0.setText("Level " + model.getGuessCounter() + " : " + text);  //set label 0 to text
        
        }
        if(counter == 1) {  //if counter is 1
        Label1.setText("Level " + model.getGuessCounter() + " : " + text);  //set label 1 to text
        }
        if(counter == 2) {  //if counter is 2
        Label2.setText("Level " + model.getGuessCounter() + " : " + text);  //set label 2 to text
        }
        if(counter == 3) {
        Label3.setText("Level " + model.getGuessCounter() + " : " + text); //set label 3 to text
        }
        if(counter == 4) {
        Label4.setText("Level " + model.getGuessCounter() + " : " + text); //set label 4 to text
        }
        if(counter == 5) {
        Label5.setText("Level " + model.getGuessCounter() + " : " + text); //set label 5 to text
        }
        
    }
    
    /**
     * call Alert Window function
     * 
     * when the user invalid input by format or not in common/random list of words
     */
    private void callAlertWindow() {
        JFrame alertFrame = new JFrame("Alert Window"); //alert frame definition
        alertFrame.setSize(500, 300);   //alert frame size
        
        JLabel alertLabel = new JLabel("Please enter 5 (FIVE) Characters only.");   //base alert Label, prompt 5 characters
       
        if(model.getInvalidWord() == true) {    //if word is not in random/commonlist of words
            alertLabel.setText("Please enter a valid word.");   //alert label for valid word prompt
        }
        alertLabel.setAlignmentY(Component.CENTER_ALIGNMENT);   //alertLabel aligned in the centre
        alertLabel.setAlignmentX(Component.CENTER_ALIGNMENT);   //alertLabel aligned in the centre
        alertFrame.add(alertLabel); //alert frame add the alert label
        alertFrame.setResizable(false); //resizable false
        alertFrame.setVisible(true);    //frame visible
        
        //TODO: if user inputs escape or timer
    }
    
    /**
     * call Winner Window function
     * 
     * when the user inputted the correct word compared to the random word
     */
    private void callWinnerWindow() {
        JFrame winnerFrame = new JFrame("WINNER WINNER!! CHICKEN DINNER!!"); //winner frame with proper title
        winnerFrame.setSize(500, 300);   //winner frame size
        JLabel winnerlabel = new JLabel("Congrats, you have guessed the word!"); //winner label 
        winnerlabel.setAlignmentX(Component.CENTER_ALIGNMENT);  //winner label center alignment
        winnerlabel.setAlignmentY(Component.CENTER_ALIGNMENT);  //winner label center alignment
        winnerFrame.add(winnerlabel);   //winner fram add winner label
        winnerFrame.setResizable(false);    //resizable false
        winnerFrame.setVisible(true);   //set winner frame visible true
    }
    
     /**
     * call Loser Window function
     * 
     * when the user inputted beyond 6 inputs
     */
    private void callLoserWindow() {
        JFrame loserFrame = new JFrame("Game Over"); //winner frame with proper title
        loserFrame.setSize(500, 300);   //winner frame size
        JLabel loserLabel = new JLabel("unforunately, you have run out of guesses."); //loser label 
        loserLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  //winner label center alignment
        loserLabel.setAlignmentY(Component.CENTER_ALIGNMENT);  //loser label center alignment
        loserFrame.add(loserLabel);   //loser fram add loser label
        loserFrame.setResizable(false);    //resizable false
        loserFrame.setVisible(true);   //set loser frame visible true
        loserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //frame closes on close operation
        
    }
    
    /**
     * valid Input checks function
     * 
     * to call the model valid input checks from the user input
     * 
     *  if the input is valid for sure
     *  check if the word is the input then call the winner window if true
     *  check if index check and assigns model arraylist
     *  check if char 2 char check and assigns model arraylist
     * 
     * @param _input containing the user input
     */
    private void validInputChecks(String _input) {
        
        if(validInputCheck == true) {   //if the input is valid and true
            
             if(this.model.test3_FullCheck(_input) == true){ //model check guess for word
                    callWinnerWindow(); //call the winner window
                }
                    
                model.test1_IndexCharCheck(_input); //model object use test 1 -- return bool 
                 
                model.test2_Char2CharCheck(_input); //model object use test 2
            
        }
        
    }
    
    /**
     * Instance Button Function
     * 
     * containing the action from when the user submits their input
     */
    private void instanceButton () {
        
        enterButton.addActionListener(new ActionListener() {    //enter button action listener
            
            @Override
            public void actionPerformed(ActionEvent e) {    //action performed by button
            
            controller.UserSubmitInput(TextField_user_input.getText()); //controller submit input
            
            if(model.inputCheck(TextField_user_input.getText()) == true){   //if input is valid after the check
                validInputCheck = true; //set valid input check to true
                System.out.println("entered " + TextField_user_input.getText()); //system print out input
                validInputChecks(TextField_user_input.getText());   //game checks the valid input
                createLabel(model.getGuessCounter(), TextField_user_input.getText());   //creates the corresponding valid input label
              
                for(int i = 0; i < TextField_user_input.getText().length(); i++ ){  //loop to parse used characters
                    model.usedCharacters += "" + (TextField_user_input.getText().charAt(i));    //add used characters to string from input
                }            
            model.setGuessCounter(model.getGuessCounter() + 1); //increment the model guess counter
            
            if(model.getGuessCounter() >= 6) { //if greater than 6.. call loser window
                callLoserWindow();  //call the loser window and potentially exit out
            }
            
            } else {    //else out
            validInputCheck = false;    //valid input is false
            callAlertWindow();  //call the alertwindow
            } 
            TextField_user_input.setText("");   //revert text field to empty
            }
        }); //end of button action listener
        
    }
    
    /**
     * init Control Panel Function
     * 
     * calls relevant control panel operations
     */
    private void initControlPanel() {

        controlPanel = new JPanel();    //control panel defined
        controlPanel.setLayout(new FlowLayout());   //panel flow layout set
        TextField_user_input = new JTextField(30);  //text field input size and definition
        enterButton = new JButton("Submit");   //enter guess button definition
        controlPanel.add(user_prompt_Label);    //panel add user prompt label
        controlPanel.add(TextField_user_input); //panel add text field input
        instanceButton();   //call the instance button to init
        controlPanel.add(enterButton);  //panel add enter button      
        controlPanel.setAlignmentX(Component.CENTER_ALIGNMENT); //panel alignment center
        controlPanel.setPreferredSize(PANEL_SIZE);  //panel set generl dimension size
        
    }
    
    /**
     * Create Keys Function
     * 
     * creates a button for each key on the keyboard with its functionalities
     * 
     * @param character target character of the button
     */
    private void createKeys(String character) {
        
        
       String temp_input = TextField_user_input.getText() + character;
       TextField_user_input.setText(temp_input);
        
    }
    
    /**
     * init Keyboard Panel Function
     * 
     * calls to display the used keys on the keyboard
     */
    private void initKeyboardPanel() {
        
        keyboardPanel = new JPanel();   //keyboard panel init
        keyboardPanel.setLayout(new BoxLayout(keyboardPanel, BoxLayout.Y_AXIS));    //keyboard panel layout
        
        
        String alphabetrow = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"; //string alphabet row
        JLabel row1 = new JLabel("Q W E R T Y U I O P");    //old: first row alphabet
        JLabel row2 = new JLabel(" A S D F G H J K L"); //old: second row alphabet
        JLabel row3 = new JLabel("  Z X C V B N M");    //old: third row alphabet
        
         String check1 = "";    //check 1 becomes char at the alphabet row
         String check2 = "";    //check 2 becomes char at model used charactees
         
         String wholepart = ""; //containing whole alphabet
         String part2 = ""; //containing the part after substring
         
         String tempused = "";  //add whats used to a string
         
         Pattern p = Pattern.compile(model.usedCharacters); //old: pattern created using model of used characters
         Matcher m = p.matcher(alphabetrow);    //old: check matcher of the pattern using the alphabet
         boolean matcher = m.find();    //asign a boolean if there is a find
        
         
         for(int j = 0; j <  model.usedCharacters.length(); j++){   //model used character loop
            check2 = "" + model.usedCharacters.charAt(j);   //check 2 becomes the char of used characters
                  
            for(int i = 0; i <  alphabetrow.length(); i++){ //whole alphabet loop
                 check1 = "" + alphabetrow.charAt(i);    //check 1 becomes the char at the alphabet
                
                if(check1.equalsIgnoreCase(check2) ){   //comparision if check1 and check 2 exist
                    //wholepart = alphabetrow.substring(0, j);
                    //part2 = alphabetrow.substring(j);
                    //wholepart += "" + check2 + part2;
                    tempused += check2;     //add whats used to temp
                    wholepart = alphabetrow.substring(0, i);    //whole part substring up to target
                    part2 = alphabetrow.substring(i, alphabetrow.length()); //part 2 contain after target
                    
                }
                wholepart += "" + tempused + part2; //add to whole part
            }
            
        }
        if(!wholepart.isEmpty()){   //if whole part is not empty
            System.out.println(wholepart);  //print new whole part
            alphabetrow = wholepart;    //set new alphabet row to whole part
        }
       
        JLabel fullrowLabel = new JLabel(alphabetrow);  //define full row label to be drawn using the alphabet row
        
        row1.setFont(new Font("Verdana",1,50)); //old: font for row1
        row2.setFont(new Font("Verdana",1,50)); //old: font for row2
        row3.setFont(new Font("Verdana",1,50)); //old: font for row3
        fullrowLabel.setFont(new Font("Verdana",1,20)); //set font for full row label
       
        //keyboardPanel.add(row1);  //add row 1 to keyboard panel
        //keyboardPanel.add(row2);  //add row 2 to keyboard panel 
        //keyboardPanel.add(row3);  //add row 3 to keyboard panel
        
        for(int k = 0; k < 26; k++) {   //loop all keyboard keys
          
        }
        
        JPanel keyInputsPanel1 = new JPanel(); //key input panel 1
        JPanel keyInputsPanel2 = new JPanel(); //key input panel 2
        JPanel keyInputsPanel3 = new JPanel(); //key input panel 3
        keyInputsPanel1.setLayout(new BoxLayout(keyInputsPanel1, BoxLayout.X_AXIS));   //layout of keys
        keyInputsPanel2.setLayout(new BoxLayout(keyInputsPanel2, BoxLayout.X_AXIS));   //layout of keys
        keyInputsPanel3.setLayout(new BoxLayout(keyInputsPanel3, BoxLayout.X_AXIS));   //layout of keys
        
        JButton qbutton = new JButton("Q"); //q button
        qbutton.addActionListener(new ActionListener() {    //add q button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("q");    //add q to textfield
            }
        }); 
        JButton wbutton = new JButton("W"); //w button
        wbutton.addActionListener(new ActionListener() {    //add w button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("w");    //add w to textfield
            }
        }); 
        JButton ebutton = new JButton("E"); //e button
        ebutton.addActionListener(new ActionListener() {    //add e button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define button
                createKeys("e");    //add e to textfield
            }
        }); 
        JButton rbutton = new JButton("R"); // r button
        rbutton.addActionListener(new ActionListener() {    //add r button action
            @Override
            public void actionPerformed(ActionEvent e) {    //defin button
                createKeys("r");    //add  r to textfield
            }
        }); 
        JButton tbutton = new JButton("T"); // t button
        tbutton.addActionListener(new ActionListener() {    //add t button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define button atcion
                createKeys("t");    //add t to textfield
            }
        }); 
        JButton ybutton = new JButton("Y"); //y button
        ybutton.addActionListener(new ActionListener() {    //add y button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define button
                createKeys("y");    //add y to text field
            }
        }); 
        JButton ubutton = new JButton("U"); //u button
        ubutton.addActionListener(new ActionListener() {    //add u button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define button
                createKeys("u");    //u button
            }
        }); 
        JButton ibutton = new JButton("I"); //i button
        ibutton.addActionListener(new ActionListener() {    //add i button action
            @Override
            public void actionPerformed(ActionEvent e) {    //defin button action
                createKeys("i");    //add i to text field
            }
        }); 
        JButton obutton = new JButton("O"); // o button
        obutton.addActionListener(new ActionListener() {    //add o button
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("o");    //add o to textfield
            }
        }); 
        JButton pbutton = new JButton("P"); //p button
        pbutton.addActionListener(new ActionListener() {    //add button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define button action
                createKeys("p");    //add p to textfield
            }
        }); 
        
        keyInputsPanel1.add(qbutton);   //add key to panel1
        keyInputsPanel1.add(wbutton);   //add key to panel1
        keyInputsPanel1.add(ebutton);   //add key to panel1
        keyInputsPanel1.add(rbutton);   //add key to panel1
        keyInputsPanel1.add(tbutton);   //add key to panel1
        keyInputsPanel1.add(ybutton);   //add key to panel1
        keyInputsPanel1.add(ubutton);   //add key to panel1
        keyInputsPanel1.add(ibutton);   //add key to panel1
        keyInputsPanel1.add(obutton);   //add key to panel1
        keyInputsPanel1.add(pbutton);   //add key to panel1
        
        JButton abutton = new JButton("A"); //a button
        abutton.addActionListener(new ActionListener() {    //add a button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("a");    //add a to textfield
            }
        }); 
        JButton sbutton = new JButton("S"); //s button
        sbutton.addActionListener(new ActionListener() {    //add s button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("s");    //add s to textfield
            }
        }); 
        JButton dbutton = new JButton("D"); //d button
        dbutton.addActionListener(new ActionListener() {    //add d button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("d");    //add d to textfield
            }
        }); 
        JButton fbutton = new JButton("F"); //f button
        fbutton.addActionListener(new ActionListener() {    //add f button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("f");    //add f to textfield
            }
        }); 
        JButton gbutton = new JButton("G"); //g button
        gbutton.addActionListener(new ActionListener() {    //add g button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("g");    //add g to textfield
            }
        }); 
        JButton hbutton = new JButton("H"); //h button
        hbutton.addActionListener(new ActionListener() {    //add h button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("h");    //add h to textfield
            }
        }); 
        JButton jbutton = new JButton("J"); //j button
        jbutton.addActionListener(new ActionListener() {    //add j button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("j");    //add j to textfield
            }
        }); 
        JButton kbutton = new JButton("K"); //k button
        kbutton.addActionListener(new ActionListener() {    //add k button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("k");    //add k to textfield
            }
        }); 
        JButton lbutton = new JButton("L"); //l button
        lbutton.addActionListener(new ActionListener() {    //add l button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("l");    //add l to textfield
            }
        }); 
        
        keyInputsPanel2.add(abutton);   //add key to panel 2
        keyInputsPanel2.add(sbutton);   //add key to panel 2
        keyInputsPanel2.add(dbutton);   //add key to panel 2
        keyInputsPanel2.add(fbutton);   //add key to panel 2
        keyInputsPanel2.add(gbutton);   //add key to panel 2
        keyInputsPanel2.add(hbutton);    //add key to panel 2
        keyInputsPanel2.add(jbutton);    //add key to panel 2
        keyInputsPanel2.add(kbutton);   //add key to panel 2
        keyInputsPanel2.add(lbutton);   //add key to panel 2
        
        JButton zbutton = new JButton("Z");     //zed button
        zbutton.addActionListener(new ActionListener() {    //add z button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("z");    //add z to textfield
            }
        }); 
        JButton xbutton = new JButton("X"); // x button
        xbutton.addActionListener(new ActionListener() {    //add x button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("x");    //add x to textfield
            }
        }); 
        JButton cbutton = new JButton("C"); //c button
        cbutton.addActionListener(new ActionListener() {    //add c button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("c");    //add c to textfield
            }
        }); 
        JButton vbutton = new JButton("V"); // v button
        vbutton.addActionListener(new ActionListener() {    //add v button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("v");    //add v to textfield
            }
        }); 
        JButton bbutton = new JButton("B"); // b button
        bbutton.addActionListener(new ActionListener() {    //add b button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("b");    //add b to textfield
            }
        }); 
        JButton nbutton = new JButton("N"); //n button
        nbutton.addActionListener(new ActionListener() {    //add n button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("n");    //add n to textfield
            }
        }); 
        JButton mbutton = new JButton("M"); //m button
        mbutton.addActionListener(new ActionListener() {    //add m button action
            @Override
            public void actionPerformed(ActionEvent e) {    //define action
                createKeys("m");    //add m to textfield
            }
        }); 

        keyInputsPanel3.add(zbutton);   //add key to panel 3
        keyInputsPanel3.add(xbutton);   //add key to panel 3
        keyInputsPanel3.add(cbutton);   //add key to panel 3
        keyInputsPanel3.add(vbutton);   //add key to panel 3
        keyInputsPanel3.add(bbutton);   //add key to panel 3
        keyInputsPanel3.add(nbutton);   //add key to panel 3 
        keyInputsPanel3.add(mbutton);   //add key to panel 3
        
        keyboardPanel.add(fullrowLabel);    //keyboard panel add full row label
        
        keyboardPanel.add(keyInputsPanel1); //add key panel 1
        keyboardPanel.add(keyInputsPanel2); //add key panel 2
        keyboardPanel.add(keyInputsPanel3); //add key panel 3
        

        fullrowLabel.setAlignmentX(Component.CENTER_ALIGNMENT);    //panel align center
        keyboardPanel.setPreferredSize(PANEL_SIZE); //panel align general dimension
        
    }

    /**
     * Update function operation
     * 
     * Override function of the observable to repaint the frame
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        frame.repaint();    //frame repaint
    }
    
}
