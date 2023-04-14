import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LIMA extends JFrame{
    JFrame frame;
    JPanel mainPanel,loginPanel,signupPanel;
    CardLayout cardLayout;
    JLabel mainLabel,userLabel,passwordLabel;
    JButton loginButton,signupButton,submitLogin,submitSignup;
    JTextField userText;
    JPasswordField passwordText;

    public LIMA(){
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


        userText = new JTextField();
        passwordText = new JPasswordField();

        //adding components to different panels
        frame.setLayout(cardLayout);
        frame.add(mainPanel,"MainPanel");
        frame.add(loginPanel,"LoginPanel");
        frame.add(signupPanel, "SignupPanel");
        frame.setSize(600,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel.setLayout(new GridLayout(3,2));
        mainPanel.add(mainLabel);
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(loginButton);
        mainPanel.add(signupButton);

        loginPanel.setLayout(new GridLayout(4,4));
        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);
        loginPanel.add(submitLogin);

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
    }    public static void main(String[] args){
        LIMA A = new LIMA();
    }
}

class User{
    protected String name,user_id,password;

    public User(){
        name = "";
        user_id = "";
        password = "";
    }

    public User(String n, String u, String p){
        name = n;
        user_id = u;
        password = p;
    }

}

class Technician extends User{
    private String name = "tech", passowrd = "t2023";

    


}