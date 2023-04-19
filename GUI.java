package OOP_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GUI extends JFrame{
    private JFrame frame;
    private JPanel mainPanel,loginPanel,signupPanel,testPanel;
    private CardLayout cardLayout;
    private JLabel mainLabel,userLabel,passwordLabel;
    private JButton loginButton,signupButton,returnButton1,returnButton2,submitLogin,submitSignup,exitButton;
    private JTextField userText;
    private JPasswordField passwordText;

    public GUI() throws FileNotFoundException{
        //general JFrame structure setup

        frame = new JFrame("LIMA Project");
        mainPanel = new JPanel();
        loginPanel = new JPanel();
        signupPanel = new JPanel();
        testPanel = new JPanel();
        cardLayout = new CardLayout();

        ButtonHandler BH = new ButtonHandler(); //assigns variable BH as a handler for variables of type JButton

        loginButton = new JButton("Login"); // creates Buttons
        submitLogin = new JButton("Submit");
        submitSignup = new JButton("Submit");
        signupButton = new JButton("Sign Up");
        returnButton1 = new JButton("Return");
        returnButton2 = new JButton("Return");
        exitButton = new JButton("Exit");
        
        loginButton.addActionListener(BH);  //links handeler to buttons
        submitSignup.addActionListener(BH);
        signupButton.addActionListener(BH);
        returnButton1.addActionListener(BH);
        returnButton2.addActionListener(BH);
        exitButton.addActionListener(BH);

        userText = new JTextField();
        passwordText = new JPasswordField();

        //adding components to different panels
        frame.setLayout(cardLayout);
        frame.add(mainPanel,"MainPanel");
        frame.add(loginPanel,"LoginPanel");
        frame.add(signupPanel, "SignupPanel");
        frame.add(testPanel, "testPanel");
        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

        frame.setVisible(true);
        mainP();
        logIn();
        signUp();

        testPanel.setLayout(new GridLayout(3,4));
        testPanel.add(new JLabel("login success"));
    }

    
    private JTextField[] studentInfo;
    PrintWriter out = new PrintWriter("studentInfo.txt");

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
        loginPanel.setLayout(new GridLayout(3,2));
        loginPanel.add(new JLabel("Username: "));
        loginPanel.add(userText);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passwordText);
        loginPanel.add(returnButton1);
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
        signupPanel.add(returnButton2);
        signupPanel.add(submitSignup);
        }
    final JLabel label = new JLabel("Please enter all required information");
    
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
                    signupPanel.add(label); // have to figure how to set size to 2 columns and how to make sure it is only called once if submit is pressed repeatedly.
                    frame.setVisible(true);
                }
                else
                    out.println(studentInfo[0].getText() + " " + studentInfo[1].getText()
                    + " " + studentInfo[2].getText() + " " + studentInfo[3].getText());
            }
            if (e.getActionCommand().equals("Exit")){
                out.close();    
                System.exit(0);
            }

        }
    }

    class submitLoginHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (studentInfo[1].equals(userText.getText()) && studentInfo[2].equals(passwordText.getPassword())){
                cardLayout.show(frame.getContentPane(), "testPanel"); //not working
            }                                                              //what were u trying to do with it. cant fix it if idk whats wrong.
        }
    }

}