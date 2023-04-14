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

    public GUI(){
        //general JFrame structure setup

        frame = new JFrame("LIMA Project");
        mainPanel = new JPanel();
        loginPanel = new JPanel();
        signupPanel = new JPanel();
        cardLayout = new CardLayout();

        mainLabel = new JLabel("Login or Sign Up");
        userLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password");

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
        frame.setVisible(true);
        
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
