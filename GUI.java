package OOP_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

class GUI{
    private JFrame frame;
    private JPanel mainPanel,loginPanel,signupPanel;
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
        cardLayout = new CardLayout();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new loginHandler());
        submitLogin = new JButton("Submit");
        submitSignup = new JButton("Submit");
        submitSignup.addActionListener(new submitSignupHandler());
        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new signupHandler());
        returnButton1 = new JButton("Return");
        returnButton1.addActionListener(new return1Handler());
        returnButton2 = new JButton("Return");
        returnButton2.addActionListener(new return2Handler());
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new exitHandler());


        userText = new JTextField();
        passwordText = new JPasswordField();

        //adding components to different panels
        frame.setLayout(cardLayout);
        frame.add(mainPanel,"MainPanel");
        frame.add(loginPanel,"LoginPanel");
        frame.add(signupPanel, "SignupPanel");
        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

        frame.setVisible(true);
        mainP();
        logIn();
        signUp();
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

        signupPanel.setLayout(new GridLayout(5,4));

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
    
    class loginHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardLayout.show(frame.getContentPane(), "LoginPanel");
        }
    }

    class signupHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardLayout.show(frame.getContentPane(), "SignupPanel");
        }
    }

    class submitSignupHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            out.println(studentInfo[0].getText() + " " + studentInfo[1].getText()
            + " " + studentInfo[2].getText() + " " + studentInfo[3].getText());
            out.close();
        }
    }

    class return1Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardLayout.show(frame.getContentPane(), "MainPanel");
        }
    }

    class return2Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardLayout.show(frame.getContentPane(), "MainPanel");
        }
    }

    class exitHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}