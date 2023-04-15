package OOP_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUI{
    private JFrame frame;
    private JPanel mainPanel,loginPanel,signupPanel;
    private CardLayout cardLayout;
    private JLabel mainLabel,userLabel,passwordLabel;
    private JButton loginButton,signupButton,returnButton,submitLogin,submitSignup;
    private JTextField userText;
    private JPasswordField passwordText;
    //private Jlabel user, name, pass, number;
    public GUI(){
        //general JFrame structure setup

        frame = new JFrame("LIMA Project");
        mainPanel = new JPanel();
        loginPanel = new JPanel();
        signupPanel = new JPanel();
        cardLayout = new CardLayout();

        mainLabel = new JLabel("Login or Sign Up");
        userLabel = new JLabel("Usesxdfrname: ");
        passwordLabel = new JLabel("Password:");

        loginButton = new JButton("Login");
        loginButton.addActionListener(new loginHandler());
        submitLogin = new JButton("Submit Login");
        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new signupHandler());
        returnButton = new JButton("Return");
        returnButton.addActionListener(new returnHandler());


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

        mainPanel.setLayout(new GridLayout(3,3));
        mainPanel.add(new JLabel());
        mainPanel.add(mainLabel);
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(loginButton);
        mainPanel.add(new JLabel());
        mainPanel.add(signupButton);

        loginPanel.setLayout(new GridLayout(4,4));
        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);
        loginPanel.add(submitLogin);
        loginPanel.add(returnButton);

        frame.setVisible(true);
        signUp();
    }

    
    private JTextField[] studentInfo;
    public void signUp(){
        studentInfo = new JTextField[4];
		for (int i = 0; i < 4; i++) studentInfo[i] = new JTextField();

        signupPanel.setLayout(new GridLayout(4,4));
		


        signupPanel.add(new JLabel("ID: "));
        signupPanel.add(studentInfo[0]);
        signupPanel.add(new JLabel("Name: "));
        signupPanel.add(studentInfo[1]);
        signupPanel.add(new JLabel("Password: "));
        signupPanel.add(studentInfo[2]);
        signupPanel.add(new JLabel("Phone number: "));
        signupPanel.add(studentInfo[3]);

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

    class returnHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardLayout.show(frame.getContentPane(), "MainPanel");
        }

    }

}
