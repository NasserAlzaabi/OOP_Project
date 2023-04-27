package OOP_Project;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class GUI extends JFrame{
    private JFrame frame;
    private JPanel techOrStuPanel,mainPanel,loginPanel,signupPanel,successPanel,studentPanel,techPanel, techLoginPanel;
    private CardLayout cardLayout;
    private JLabel mainLabel,userLabel,passwordLabel;
    private JButton loginButton,signupButton,submitLogin,TsubmitLogin,submitSignup,exitButton,studentButton,technicianButton,RSButton,RSButton2;
    private JTextField userText,TechText;
    private JPasswordField passwordText,techPassword;
    private JButton[] returnButton = new JButton[3];
    
    PrintWriter pout;
    Technician tech = new Technician(); //technician
    private int studentCount = 0; //counts how many students signed up (for login)
    private FileOutputStream fout;
    private FileInputStream fin;
    ObjectInputStream in;
    ObjectOutputStream out;
    ArrayList<Student> students = new ArrayList<Student>();

    public GUI() throws FileNotFoundException{
        //general JFrame structure setup

        frame = new JFrame("LIMA Project");
         
        techOrStuPanel = new JPanel();
        mainPanel = new JPanel();
        loginPanel = new JPanel();
        techLoginPanel = new JPanel();
        signupPanel = new JPanel();
        successPanel = new JPanel();
        studentPanel = new JPanel();
        techPanel = new JPanel();
        cardLayout = new CardLayout();

        ButtonHandler BH = new ButtonHandler(); //assigns variable BH as a handler for variables of type JButton
        RSButton = new JButton("Return to Selection");
        RSButton2 = new JButton("Return to Selection");
        studentButton = new JButton("Student");
        technicianButton = new JButton("Technician");
        loginButton = new JButton("Login"); // creates Buttons
        submitLogin = new JButton("Enter");
        TsubmitLogin = new JButton("Enter");
        submitSignup = new JButton("Submit");
        signupButton = new JButton("Sign Up");
        
        exitButton = new JButton("Exit");
        
        RSButton.addActionListener(BH);
        RSButton2.addActionListener(BH);
        studentButton.addActionListener(BH);
        technicianButton.addActionListener(BH);
        loginButton.addActionListener(BH);  //links handeler to buttons
        submitSignup.addActionListener(BH);
        signupButton.addActionListener(BH);
        exitButton.addActionListener(BH);
        submitLogin.addActionListener(BH);
        TsubmitLogin.addActionListener(BH);

        for (int i = 0; i < 3; i++){ //creates 3 of the same button, all of them return to main screen
            returnButton[i] = new JButton("Return");
            returnButton[i].addActionListener(BH);
        }

        userText = new JTextField();
        TechText = new JTextField();
        passwordText = new JPasswordField();
        techPassword = new JPasswordField();

        //adding components to different panels
        frame.setLayout(cardLayout);
        frame.add(techOrStuPanel,"TechOrStuPanel");
        frame.add(mainPanel,"MainPanel");
        frame.add(loginPanel,"LoginPanel");
        frame.add(techLoginPanel,"techLoginPanel");
        frame.add(signupPanel, "SignupPanel");
        frame.add(successPanel, "successPanel");
        frame.add(studentPanel, "studentPanel");
        frame.add(techPanel, "techPanel");
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        techOrStu();
        mainP();
        logIn();
        TlogIn();
        signUp();
        successP();
        techP();
        studentP();
    }

    public void techOrStu(){

        techOrStuPanel.setLayout(new GridLayout(3,3));
        techOrStuPanel.add(new JLabel()); 
        techOrStuPanel.add(new JLabel("Student or Technician"));
        techOrStuPanel.add(new JLabel());
        techOrStuPanel.add(new JLabel());
        techOrStuPanel.add(new JLabel());
        techOrStuPanel.add(new JLabel());
        techOrStuPanel.add(studentButton);
        techOrStuPanel.add(new JLabel());
        techOrStuPanel.add(technicianButton);
    }
    
    private JTextField[] studentInfo;

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
        mainPanel.add(RSButton);
    }

    public void logIn(){
        loginPanel.setLayout(new GridLayout(4,2));
        loginPanel.add(new JLabel("ID: "));
        loginPanel.add(userText);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passwordText);
        loginPanel.add(returnButton[0]);
        loginPanel.add(submitLogin);
    }
    public void TlogIn(){
        techLoginPanel.setLayout(new GridLayout(4,2));
        techLoginPanel.add(new JLabel("Name: "));
        techLoginPanel.add(TechText);
        techLoginPanel.add(new JLabel("Password: "));
        techLoginPanel.add(techPassword);
        techLoginPanel.add(RSButton2);
        techLoginPanel.add(TsubmitLogin);
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

    public void studentP(){ //panel after logging into student
        studentPanel.add(new JLabel("successful login"));

    }

    public void techP(){ //panel after logging into tech
        techPanel.add(new JLabel("U are in tech panel"));
    }

    public void successP(){
        successPanel.setLayout(new GridLayout(3,1));
        successPanel.add(new JLabel("Sign up successful"));
        successPanel.add(new JLabel());
        successPanel.add(new JLabel("Press to return to login."));
        successPanel.add(returnButton[2]);
    }

    public boolean checkLogin(String id, String password){//method that goes in file to check student data
        boolean isLogin = false;
        try {
            Scanner fin = new Scanner (new File("studentInfo.txt"));
            while(fin.hasNextLine()){
                String line = fin.nextLine();
                String[] data = line.split(" ");
                if(data[0].equals(id) && data[2].equals(password)){
                    isLogin = true;
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return isLogin;
    }

    public void checkUser(){ //function that checks if the user can login and returns boolean
        String nameCheck = userText.getText();
        String passwordCheck = new String(passwordText.getPassword());
        String TechnicianCheck = TechText.getText();
        String TPassCheck = new String(techPassword.getPassword());
            if(TechnicianCheck.equals(tech.getName()) && TPassCheck.equals(tech.getPassword()))
                cardLayout.show(frame.getContentPane(), "techPanel");

            if(checkLogin(nameCheck,passwordCheck)){
                cardLayout.show(frame.getContentPane(), "studentPanel");
            }
            else{
                loginPanel.add(incorrectLogin);
                frame.setVisible(true);
            }
    }


    //its in the name
    public void createNewStudent(String newID, String newName, String newPassword, String newPhoneNumber){
        Student newStudent = new Student(newID, newName, newPassword, newPhoneNumber,"");
        students.add(newStudent);
    }

    final JLabel enterInfo = new JLabel("Enter all required information.");
    final JLabel incorrectLogin = new JLabel("Incorrect Name or Password");

    class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand().equals("Login"))
                cardLayout.show(frame.getContentPane(), "LoginPanel");
            if (e.getActionCommand().equals("Sign Up"))
                cardLayout.show(frame.getContentPane(), "SignupPanel");
            if (e.getActionCommand().equals("Return"))
                cardLayout.show(frame.getContentPane(), "MainPanel");
            if (e.getActionCommand().equals("Return to Selection")){
                cardLayout.show(frame.getContentPane(), "TechOrStuPanel");
            }
            if (e.getActionCommand().equals("Submit")){
                if ((studentInfo[0].getText().isEmpty() || studentInfo[1].getText().isEmpty()
                || studentInfo[2].getText().isEmpty() || studentInfo[3].getText().isEmpty())){
                    signupPanel.add(enterInfo); 
                    frame.setVisible(true);
                }
                else{
                    try {
                    pout = new PrintWriter(new FileOutputStream("studentInfo.txt", true));
                    pout.append(studentInfo[0].getText() + " " + studentInfo[1].getText()
                    + " " + studentInfo[2].getText() + " " + studentInfo[3].getText() + "\n");
                    cardLayout.show(frame.getContentPane(), "successPanel");
                    pout.close();
                    //storeStudent();
                    } catch (FileNotFoundException a) {a.printStackTrace();}

                    //Adds student to arraylist students
                    createNewStudent(studentInfo[0].getText(), studentInfo[1].getText(), studentInfo[2].getText(), studentInfo[3].getText());
                    studentInfo[0].setText(""); //fixes a bug so when you click signup again,
                    studentInfo[1].setText(""); // it doesnt have the data of the previous user
                    studentInfo[2].setText(""); // in the textfield
                    studentInfo[3].setText(""); 
                    
                } 
            }
            if (e.getActionCommand().equals("Student")){
                cardLayout.show(frame.getContentPane(), "MainPanel");
            }
            if (e.getActionCommand().equals("Technician")){
                cardLayout.show(frame.getContentPane(), "techLoginPanel");
            }
            if (e.getActionCommand().equals("Enter")){
                checkUser();
            }
            if (e.getActionCommand().equals("Exit")){
                System.exit(0);
            }
        }
    }
}
