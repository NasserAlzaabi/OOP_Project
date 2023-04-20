package OOP_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GUI extends JFrame{
    private JFrame frame;
    private JPanel mainPanel,loginPanel,signupPanel,successPanel,studentPanel,techPanel;
    private CardLayout cardLayout;
    private JLabel mainLabel,userLabel,passwordLabel;
    private JButton loginButton,signupButton,submitLogin,submitSignup,exitButton;
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton[] returnButton = new JButton[3];
    Technician tech = new Technician();

    public GUI() throws FileNotFoundException{
        //general JFrame structure setup

        frame = new JFrame("LIMA Project"); 
        mainPanel = new JPanel();
        loginPanel = new JPanel();
        signupPanel = new JPanel();
        successPanel = new JPanel();
        studentPanel = new JPanel();
        techPanel = new JPanel();
        cardLayout = new CardLayout();

        ButtonHandler BH = new ButtonHandler(); //assigns variable BH as a handler for variables of type JButton

        loginButton = new JButton("Login"); // creates Buttons
        submitLogin = new JButton("Enter");
        submitSignup = new JButton("Submit");
        signupButton = new JButton("Sign Up");
        
        exitButton = new JButton("Exit");
        
        loginButton.addActionListener(BH);  //links handeler to buttons
        submitSignup.addActionListener(BH);
        signupButton.addActionListener(BH);
        exitButton.addActionListener(BH);
        submitLogin.addActionListener(BH);

        for (int i = 0; i < 3; i++){ //creates 3 of the same button, all of them return to main screen
            returnButton[i] = new JButton("Return");
            returnButton[i].addActionListener(BH);
        }

        userText = new JTextField();
        passwordText = new JPasswordField();

        //adding components to different panels
        frame.setLayout(cardLayout);
        frame.add(mainPanel,"MainPanel");
        frame.add(loginPanel,"LoginPanel");
        frame.add(signupPanel, "SignupPanel");
        frame.add(successPanel, "successPanel");
        frame.add(studentPanel, "studentPanel");
        frame.add(techPanel, "techPanel");
        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        mainP();
        logIn();
        signUp();
        successP();
        techP();
    }

    
    private JTextField[] studentInfo;
    PrintWriter out = new PrintWriter(new FileOutputStream("studentInfo.txt", true));

    public void mainP(){
        mainPanel.setLayout(new GridLayout(3,3));
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel("Login or Sign Up"));
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(loginButton);
        mainPanel.add(signupButton);
        mainPanel.add(exitButton);
    }

    public void logIn(){
        loginPanel.setLayout(new GridLayout(4,2));
        loginPanel.add(new JLabel("Username: "));
        loginPanel.add(userText);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passwordText);
        loginPanel.add(returnButton[0]);
        loginPanel.add(submitLogin);
    }

    public void signUp(){
        studentInfo = new JTextField[4];
		for (int i = 0; i < 4; i++) studentInfo[i] = new JTextField();

        signupPanel.setLayout(new GridLayout(6,4));

        signupPanel.add(new JLabel("ID: "));
        signupPanel.add(studentInfo[0]);
        signupPanel.add(new JLabel("Name: "));
        signupPanel.add(studentInfo[1]);
        signupPanel.add(new JLabel("Password: "));
        signupPanel.add(studentInfo[2]);
        signupPanel.add(new JLabel("Phone number: "));
        signupPanel.add(studentInfo[3]);
        signupPanel.add(returnButton[1]);
        signupPanel.add(submitSignup);
    }

    public void studentP(){


    }

    public void techP(){
        techPanel.add(new JLabel("U are in tech panel"));
    }

    public void successP(){
        successPanel.setLayout(new GridLayout(3,1));
        successPanel.add(new JLabel("Sign up successful"));
        successPanel.add(new JLabel());
        successPanel.add(new JLabel("Press to return to login."));
        successPanel.add(returnButton[2]);
    }

    final JLabel enterInfo = new JLabel("Enter all required information.");
    final JLabel incorrectLogin = new JLabel("Incorrect Username or Password");

    class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand().equals("Login"))
                cardLayout.show(frame.getContentPane(), "LoginPanel");
            if (e.getActionCommand().equals("Sign Up"))
                cardLayout.show(frame.getContentPane(), "SignupPanel");
            if (e.getActionCommand().equals("Return"))
                cardLayout.show(frame.getContentPane(), "MainPanel");
            if (e.getActionCommand().equals("Submit")){
                if ((studentInfo[0].getText().isEmpty() || studentInfo[1].getText().isEmpty()
                || studentInfo[2].getText().isEmpty() || studentInfo[3].getText().isEmpty())){
                    signupPanel.add(enterInfo); 
                    frame.setVisible(true);
                }
                else{
                    out.append(studentInfo[0].getText() + " " + studentInfo[1].getText()
                    + " " + studentInfo[2].getText() + " " + studentInfo[3].getText() + "\n");
                    cardLayout.show(frame.getContentPane(), "successPanel");
                    out.close();   //error where we can only create one acc once every time we call main !!! need to fix
                }
            }
            if (e.getActionCommand().equals("Enter")){ //need to add login for student too here !!!
                if(userText.getText().equals(tech.getName()) && passwordText.getText().equals(tech.getPassowrd()))
                    cardLayout.show(frame.getContentPane(), "techPanel");
                else{
                    loginPanel.add(incorrectLogin);
                    frame.setVisible(true);
                }
            }
            if (e.getActionCommand().equals("Exit")){
                System.exit(0);
            }
        }
    }

    class submitLoginHandler implements ActionListener{ //zayed if u dont need this delete
        public void actionPerformed(ActionEvent e){
            if (studentInfo[1].equals(userText.getText()) && studentInfo[2].equals(passwordText.getText())){
                cardLayout.show(frame.getContentPane(), "testPanel"); //not working
            }                                                              //what were u trying to do with it. cant fix it if idk whats wrong.
        }
    }

}