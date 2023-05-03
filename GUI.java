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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class GUI extends JFrame{
    private JFrame frame;
    private JPanel techOrStuPanel,mainPanel,loginPanel,signupPanel,successPanel,studentPanel;
    private JPanel techPanel, techLoginPanel, inventoryPanel, studentSearchPanel, itemSearch, itemApproveP, borrowedPanel; //itemApprove, borrowed new
    private JPanel studentDashboardPanel,studentDashboardBasketPanel, techDash, studentItemSearch;
    private CardLayout cardLayout;
    private JLabel mainLabel,userLabel,passwordLabel;
    private JButton loginButton,signupButton,submitLogin,TsubmitLogin,submitSignup,exitButton;
    private JButton studentButton,technicianButton,RSButton,RSButton2,techBackButton, iSearch, iSearchBack[], studentISearchBack;
    private JButton studentDashboardButton, studentDashboardBackButton, studentISearchButton;
    private JButton studentDashboardBasketAdd, studentDashboardBasketReview, studentDashboardBasketBack;
    private JButton studentDashboardSendRequest, studentDashboardEditAdd, studentDashboardEditReduce; //new JButtons
    private JButton techSearchStudentButton, apButton, approveButton, borrowedButton, studentSearchButton, studentSearchBackButton; //new JButton
    private JTextField userText,TechText, studentIDate, apName; //studentIDate, apName new
    private JPasswordField passwordText,techPassword;
    private JButton[] returnButton = new JButton[4];
    private JTextArea studentDashboardArea, studentDashboardBasket, dashPanel, requestList, borrowedStudents, studentSearchArea; //request list new
    private JScrollPane itemScroll, studentDashboardScroll, studentDashboardBasketScroll, requestScroll, studentSearchScroll; //request Scroll new

    //java.time for current time and date
    private LocalDate currentDateObj = LocalDate.now();
    private LocalTime currentTimeObj = LocalTime.now();
    private DateTimeFormatter currentTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss"); 
	private DateTimeFormatter currentDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private String currentDate = currentDateObj.format(currentDateFormat);
    private String currentTime = currentTimeObj.format(currentTimeFormat);

    PrintWriter pout;
    Technician tech = new Technician(); //technician
    private int studentCount = 0; //counts how many students signed up (for login)
    private FileOutputStream fout;
    private FileInputStream fin;
    ObjectInputStream in;
    ObjectOutputStream out;
    Student loggedStudent;
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Item> inventory = new ArrayList<Item>(); //for tech to add new items and edit existing ones
    ArrayList<Item> basket = new ArrayList<Item>(); //new, what the student takes
    ArrayList<Request> RQ = new ArrayList<Request>();

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
        studentDashboardPanel = new JPanel();
        studentDashboardBasketPanel = new JPanel();
        techPanel = new JPanel();
        inventoryPanel = new JPanel();
        itemSearch = new JPanel();
        techDash = new JPanel();
        studentItemSearch = new JPanel();
        itemApproveP = new JPanel();
        borrowedPanel = new JPanel();
        studentSearchPanel = new JPanel();
        cardLayout = new CardLayout();

        //text area convert to scroll pane
        studentDashboardArea = new JTextArea(5,4);
        studentDashboardBasket = new JTextArea(5,4);
        studentDashboardScroll = new JScrollPane(studentDashboardArea);
        studentDashboardBasketScroll = new JScrollPane(studentDashboardBasket);
        dashPanel = new JTextArea();
        itemScroll = new JScrollPane(dashPanel); 
        requestList = new JTextArea();
        requestScroll = new JScrollPane(requestList);
        borrowedStudents = new JTextArea();
        studentSearchArea = new JTextArea();
        studentSearchScroll = new JScrollPane(studentSearchArea);

        studentDashboardArea.setEditable(false);
        studentDashboardBasket.setEditable(false);
        dashPanel.setEditable(false);
        requestList.setEditable(false);
        borrowedStudents.setEditable(false);
        studentSearchArea.setEditable(false);

        ButtonHandler BH = new ButtonHandler(); //assigns variable BH as a handler for variables of type JButton
        techHandler TH = new techHandler();
        studentHandler SH = new studentHandler();

        iSearchBack = new JButton[3];

        RSButton = new JButton("Return to Selection");
        RSButton2 = new JButton("Return to Selection");
        studentButton = new JButton("Student");
        technicianButton = new JButton("Technician");
        loginButton = new JButton("Login");
        submitLogin = new JButton("Enter");
        submitSignup = new JButton("Submit");
        signupButton = new JButton("Sign Up");
        exitButton = new JButton("Exit");
        iEnter = new JButton("Enter Item");
        invDelete = new JButton("Delete Item");
        invEdit = new JButton("Edit Item");
        iSearch = new JButton("Search");
        iSearchBack[0] = new JButton("Go Back");
        iSearchBack[1] = new JButton("Go Back");
        iSearchBack[2] = new JButton("Go Back");
        apButton = new JButton("Approvals");
        approveButton = new JButton("Approve");
        borrowedButton = new JButton("Borrowed Student");
        techSearchStudentButton = new JButton("Search Student");
        studentSearchButton = new JButton("Search.");
        studentSearchBackButton = new JButton("Back");

        for (int i = 0; i < 4; i++){
            invReturn[i] = new JButton("Back");
            invReturn[i].addActionListener(TH);
        }

        studentISearchButton = new JButton("Search");
        studentISearchBack = new JButton("Go Back");

        TsubmitLogin = new JButton("Enter");
        techBackButton = new JButton("Return to Main Menu");

        //studentDashboard buttons
        studentDashboardButton = new JButton("Item Dashboard");
        studentDashboardBasketAdd = new JButton ("Add to Basket");
        studentDashboardBasketReview = new JButton("Review Basket");
        studentDashboardBackButton = new JButton("Back");
        studentDashboardBasketBack = new JButton("Return to Dashboard");
        studentDashboardSendRequest = new JButton("Send Request");
        studentDashboardEditAdd = new JButton("+1");
        studentDashboardEditReduce = new JButton("-1"); 
        

        RSButton.addActionListener(BH);
        RSButton2.addActionListener(BH);
        techBackButton.addActionListener(BH);
        studentButton.addActionListener(BH);
        technicianButton.addActionListener(BH);
        loginButton.addActionListener(BH);  //links handeler to buttons
        submitSignup.addActionListener(BH);
        signupButton.addActionListener(BH);
        exitButton.addActionListener(BH);
        submitLogin.addActionListener(BH);
        TsubmitLogin.addActionListener(BH);

        inventoryButton.addActionListener(TH);
        iEnter.addActionListener(TH);
        invDelete.addActionListener(TH);
        invEdit.addActionListener(TH);
        itemDashButton.addActionListener(TH);
        iSearch.addActionListener(TH);
        enterItem.addActionListener(TH);
        iSearchBack[0].addActionListener(TH);
        iSearchBack[1].addActionListener(TH);
        iSearchBack[2].addActionListener(TH);
        apButton.addActionListener(TH);
        approveButton.addActionListener(TH);
        borrowedButton.addActionListener(TH);
        techSearchStudentButton.addActionListener(TH);
        studentSearchButton.addActionListener(TH);
        studentSearchBackButton.addActionListener(TH);

        studentEnterItem.addActionListener(SH);
        studentISearchButton.addActionListener(SH);
        studentISearchBack.addActionListener(SH);
        studentDashboardButton.addActionListener(SH);
        studentDashboardBackButton.addActionListener(SH);
        studentDashboardBasketAdd.addActionListener(SH);
        studentDashboardBasketReview.addActionListener(SH);
        studentDashboardBasketBack.addActionListener(SH);
        studentDashboardSendRequest.addActionListener(SH);
        studentDashboardEditAdd.addActionListener(SH);
        studentDashboardEditReduce.addActionListener(SH);

        for (int i = 0; i < 4; i++){ //creates 4 of the same button, all of them return to main screen
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
        frame.add(studentDashboardPanel, "studentDashboardPanel");
        frame.add(studentDashboardBasketPanel, "studentDashboardBasketPanel");
        frame.add(techPanel, "techPanel");
        frame.add(inventoryPanel, "InventoryPanel"); 
        frame.add(itemSearch, "iSearch");
        frame.add(techDash, "DashboardPanel");
        frame.add(studentItemSearch, "studentItemSearch");
        frame.add(itemApproveP, "ApprovalPanel");
        frame.add(borrowedPanel, "borrowedPanel");
        frame.add(studentSearchPanel, "studentSearchP");
        frame.setSize(1000,650);
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        getInventoryFromFile();
        frame.setVisible(true);
        
        techOrStu();
        mainP();
        logIn();
        TlogIn();
        signUp();
        successP();
        techP();
        studentP();
        studentDashboardP();
        studentDashboardBasketP();
        invP();
        searchP();
        studentSearchP();
        techStudentSearchP();
        //approvalP();
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
        studentPanel.setLayout(new GridLayout(3,1));
        studentPanel.add(new JLabel("successful login"));
        studentPanel.add(studentDashboardButton);
        studentPanel.add(studentISearchButton);
        studentPanel.add(returnButton[3]);
    }

    private JTextField studentItemS= new JTextField();
    private JButton studentEnterItem = new JButton("Enter Item");

    public void studentSearchP(){
        studentItemSearch.setLayout(new GridLayout(3, 1));
        studentItemSearch.add(studentItemS);
        studentItemSearch.add(studentEnterItem);
        studentItemSearch.add(studentInfoLabel);
        studentItemSearch.add(studentISearchBack);
    }

    final JLabel studentInfoLabel = new JLabel("");
    public void studentSearchItem(){
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).getName().equals(studentItemS.getText())){
                studentInfoLabel.setText((inventory.get(i).getName() + "\t" + inventory.get(i).getModel() + "\t" + inventory.get(i).getQuantity()
                + "\t" +  inventory.get(i).getValue() + "\t" + inventory.get(i).getDate() + "\t" + inventory.get(i).getConsumable()));
                frame.setVisible(true);
                break;
            }
            else
                studentInfoLabel.setText("Item does not exist"); 
        }
    }

    JTextField studentIName, studentIQuantity, studentItemSelect; //studentItemSelect new attribute
    JLabel studentNotifLabel;

    public void studentDashboardP(){ 
        studentDashboardPanel.setLayout(new GridLayout(6,2));
        studentDashboardPanel.add(new JLabel("Item dashboard: "));
        studentDashboardPanel.add(studentDashboardScroll);
        studentDashboardPanel.add(new JLabel("Item Name: "));
        studentDashboardPanel.add(studentIName = new JTextField(""));
        studentDashboardPanel.add(new JLabel("Item Quantity: "));
        studentDashboardPanel.add(studentIQuantity = new JTextField());
        studentDashboardPanel.add(new JLabel("Date of return (dd-mm-yyyy): "));
        studentDashboardPanel.add(studentIDate = new JTextField());
        studentDashboardPanel.add(studentNotifLabel = new JLabel("")); //item not found or, item add and so on (idk how to do this)
        studentDashboardPanel.add(studentDashboardBasketAdd);
        studentDashboardPanel.add(studentDashboardBackButton);
        studentDashboardPanel.add(studentDashboardBasketReview);
    }

    public void studentDashboardBasketP(){
        studentDashboardBasketPanel.setLayout(new GridLayout(6,2));
        studentDashboardBasketPanel.add(new JLabel("Review Basket: "));
        studentDashboardBasketPanel.add(studentDashboardBasketScroll);
        studentDashboardBasketPanel.add(new JLabel("Enter Item To Edit: "));
        studentDashboardBasketPanel.add(studentItemSelect = new JTextField());
        studentDashboardBasketPanel.add(studentDashboardEditAdd);
        studentDashboardBasketPanel.add(studentDashboardEditReduce);
        studentDashboardBasketPanel.add(studentDashboardSendRequest);
        studentDashboardBasketPanel.add(studentDashboardBasketBack);
    }

    private JButton inventoryButton = new JButton("Inventory Panel");
    private JButton itemDashButton = new JButton("Dashboard");

    public void techP(){ //panel after logging into tech
        techPanel.setLayout(new GridLayout(7,1));
        techPanel.add(inventoryButton);
        techPanel.add(itemDashButton);
        techPanel.add(iSearch);
        techPanel.add(apButton);
        techPanel.add(borrowedButton);
        techPanel.add(techSearchStudentButton);
        techPanel.add(techBackButton);
    }

    public void borrowedP(){ //new
        borrowedPanel.setLayout(new GridLayout(2,2));
        borrowedStudents.setText("");
        borrowedStudents.append("Name \t ID \t Due Date");
        for (int i = 0; i < students.size(); i++){
            if (!students.get(i).getItems().isEmpty()){
                borrowedStudents.append(students.get(i).getName());
                borrowedStudents.append(students.get(i).getID());
            }
        }

        borrowedPanel.add(borrowedStudents);
        borrowedPanel.add(iSearchBack[2]);
    }

    private JTextField iName, iModel, iValue, iQuantity, iDate, iConsumable;
    private JButton iEnter, invEdit, invDelete;
    private JButton[] invReturn = new JButton[4];

    public void invP(){ //Panel for tech to add remove edit items

        inventoryPanel.setLayout(new GridLayout(9,2, 5, 5));
        inventoryPanel.add(new JLabel("  Please enter all requested info"));
        inventoryPanel.add(invReturn[0]);
        inventoryPanel.add(new JLabel("  Item Name: "));
        inventoryPanel.add(iName = new JTextField());
        inventoryPanel.add(new JLabel("  Item Model: "));
        inventoryPanel.add(iModel = new JTextField());
        inventoryPanel.add(new JLabel("  Item Value"));
        inventoryPanel.add(iValue = new JTextField());
        inventoryPanel.add(new JLabel("  Quantity of Item: "));
        inventoryPanel.add(iQuantity = new JTextField());
        inventoryPanel.add(new JLabel("  Item Consumable (y/n):"));
        inventoryPanel.add(iConsumable = new JTextField());
        inventoryPanel.add(new JLabel("  Item Date(dd-mm-yyyy): "));
        inventoryPanel.add(iDate = new JTextField());
        inventoryPanel.add(iEnter);
        inventoryPanel.add(invDelete);
        inventoryPanel.add(invEdit); //edit still work in progress
        inventoryPanel.add(iLabel);
    }

    private JTextField itemS = new JTextField();
    private JButton enterItem = new JButton("enterItem");

    public void searchP(){
        itemSearch.setLayout(new GridLayout(3,1, 5 ,5));
        itemSearch.add(itemS);
        itemSearch.add(enterItem);
        itemSearch.add(iInfo);
        itemSearch.add(iSearchBack[0]);
    }

    private JTextField searchStudentField;

    public void techStudentSearchP(){
        studentSearchPanel.setLayout(new GridLayout(3,2));
        studentSearchPanel.add(new JLabel("Enter student name: "));
        studentSearchPanel.add(searchStudentField = new JTextField());
        studentSearchPanel.add(studentSearchButton);
        studentSearchPanel.add(studentSearchScroll);
        studentSearchPanel.add(studentSearchBackButton);
    }

    public void techStudentSearcher(){
        boolean isRequestStudent = false;
        ArrayList<Item> studentItems = new ArrayList<Item>();
        int studentIndex = -1; 
        studentSearchArea.setText("Borrowed Items\tQuantity Borrowed\tDate Return\n");
        for (int i = 0; i < students.size(); i++){ 
            for (int j = 0; j < RQ.size(); j++){ 
                if (searchStudentField.getText().equals(RQ.get(j).getName())){ 
                    isRequestStudent = true;
                    studentIndex = j;
                    searchStudentField.setText("");
                }
            }
        }
        if (isRequestStudent){
            for (Item item : RQ.get(studentIndex).getItems()) {
                studentItems.add(item);
            }
            for (int i = 0; i < studentItems.size(); i++){ 
                studentSearchArea.append(studentItems.get(i).getName() + "\t" + studentItems.get(i).getQuantity()
                + "\t" + studentItems.get(i).getDate() + "\n");
            }
        }
        else{
            studentSearchArea.append("no items");
        }
    }
    

    final JLabel iInfo = new JLabel("");
    public void searchItem(){
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).getName().equals(itemS.getText())){
                iInfo.setText((inventory.get(i).getName() + "\t" + inventory.get(i).getModel() + "\t" + inventory.get(i).getQuantity()
                + "\t" +  inventory.get(i).getValue() + "\t" + inventory.get(i).getDate() + "\t" + inventory.get(i).getConsumable()));
                frame.setVisible(true);
                break;
            }
            else
                iInfo.setText("Item does not exist"); 
        }
    }

    public void dashP(){
        
        techDash.setLayout(new GridLayout(2,1,5,5));
        techDash.add(itemScroll);
        dashPanel.append("Name \t Model# \t Value \t Quantity\t Consumable\t Date \t Borrowed Quantity\n");
        techDash.add(invReturn[1]);

        for (int i = 0; i < inventory.size(); i++){
            dashPanel.append(inventory.get(i).getName() + "\t" + inventory.get(i).getModel() + "\t" +
            inventory.get(i).getValue() + "\t" + inventory.get(i).getQuantity() + "\t" + inventory.get(i).getConsumable()
             + "\t" + inventory.get(i).getDate() + "\t"
            + inventory.get(i).getBorrowedQuantity() +"\n");
        }
        
    }

    public void successP(){
        successPanel.setLayout(new GridLayout(3,1));
        successPanel.add(new JLabel("Sign up successful"));
        successPanel.add(new JLabel());
        successPanel.add(new JLabel("Press to return to login."));
        successPanel.add(returnButton[2]);
    }

    public void studentDashboardSetup(){ //sets up the look of the dashboard text area
        studentDashboardArea.append("Name \t Model# \t Value \t Quantity\t Consumable\t Date\n");
        for (int i = 0; i < inventory.size(); i++){
            studentDashboardArea.append(inventory.get(i).getName() + "\t" + inventory.get(i).getModel() + "\t" +
            inventory.get(i).getValue() + "\t" + inventory.get(i).getQuantity() + "\t" + inventory.get(i).getConsumable()
            + "\t" + inventory.get(i).getDate() + "\n");
        }
    }

    public void studentDashboardBasketSetup(){
        studentDashboardBasket.append("Name \t Quantity\n");
    }
    
    String dateStudent;

    public void studentBasketAddItem(){
        Item myItem;
        if (studentIName.getText().isEmpty() || studentIQuantity.getText().isEmpty()){
            studentNotifLabel.setText("Please provide Information");
        }
        else{
            int nameIndex = 0;
            for (int i = 0; i<inventory.size(); i++){
                if (studentIName.getText().equals(inventory.get(i).getName())){
                    nameIndex = i;
                }
            }
            if (Integer.parseInt(studentIQuantity.getText()) <= inventory.get(nameIndex).getQuantity()){
                myItem = new Item();
                myItem.setName(inventory.get(nameIndex).getName());
                myItem.setModel(inventory.get(nameIndex).getModel());
                myItem.setValue(inventory.get(nameIndex).getValue());
                myItem.setDate(inventory.get(nameIndex).getDate());
                myItem.setConsumable(inventory.get(nameIndex).getConsumable());
                myItem.setQuantity(Integer.parseInt(studentIQuantity.getText()));
                basket.add(myItem);
                dateStudent = studentIDate.getText();
                studentDashboardBasket.append(studentIName.getText() + "\t" + studentIQuantity.getText() + "\t" + studentIDate.getText() + "\n");
                // for (int n = 0; n < inventory.size();n++)
                // {
                //     if (inventory.get(n).getName().equals(studentIName.getText()))
                //         inventory.get(n).setDate(studentIDate.getText());
                // }
                studentIName.setText("");
                studentIQuantity.setText("");
                studentIDate.setText("");
            }
            else{
                studentNotifLabel.setText("That amount is not in stock");
            }
        }
    }

    public void studentItemQuantityAdd(){
        int newQuantity = 0;
        int maxQuantity = 0;
        int nameIndex = 0;
        if (studentItemSelect.getText().isEmpty()){
            //do studentilabel here nasser
        }
        else{
            for(int i = 0; i<inventory.size(); i++){
                if (studentItemSelect.getText().equals(inventory.get(i).getName())){
                    nameIndex = i;
                    maxQuantity = inventory.get(i).getQuantity();
                }
            }
            newQuantity = 1 + basket.get(nameIndex).getQuantity();
            if (newQuantity <= maxQuantity){
                basket.get(nameIndex).setQuantity(newQuantity);
                studentDashboardBasket.setText("");
                studentDashboardBasket.append("Name \t Quantity\n");
                for (int i = 0; i<basket.size(); i++){
                    if (i!=nameIndex){
                        studentDashboardBasket.append(basket.get(i).getName() + "\t" + basket.get(i).getQuantity() + "\t" + basket.get(i).getDate() + "\n"); //new nasser
                    }
                }
                studentDashboardBasket.append(basket.get(nameIndex).getName() + "\t" + basket.get(nameIndex).getQuantity() + "\t" + basket.get(nameIndex).getDate() + "\n"); //new nasser
            }
            
        }

    }

    public void studentItemQuantityReduce(){
        int newQuantity = 0;
        int nameIndex = 0;
        if (studentItemSelect.getText().isEmpty()){
            System.out.print("empty");
        }
        else{
            for(int i = 0; i < inventory.size(); i++){
                if (studentItemSelect.getText().equals(inventory.get(i).getName())){
                    nameIndex = i;
                }
            }
            newQuantity = basket.get(nameIndex).getQuantity() - 1;
            if (newQuantity>=0){
                basket.get(nameIndex).setQuantity(newQuantity);
                studentDashboardBasket.setText("");
                studentDashboardBasket.append("Name \t Quantity\n");
                for (int i = 0; i<basket.size(); i++){
                    if (i!=nameIndex){
                        studentDashboardBasket.append(basket.get(i).getName() + "\t" + basket.get(i).getQuantity() + "\t" + basket.get(i).getDate() + "\n");
                    }
                }
                studentDashboardBasket.append(basket.get(nameIndex).getName() + "\t" + basket.get(nameIndex).getQuantity() + "\t" + basket.get(nameIndex).getDate() + "\n");
            }
            
        }
    }
    //here
    public void sendItemRequest(){ //new method that appends to a file
        try{
            String fileName = loggedStudent.getID() + "Request.txt";
            String basketItems = studentDashboardBasket.getText();
            PrintWriter out = new PrintWriter(new FileOutputStream(fileName, false));
            out.append(loggedStudent.getName() + "\t" + "Phone Number: " + loggedStudent.getPhoneNumber() + "\n" 
            + basketItems + "\n" + currentDate + "\t" + currentTime + "\n");
            out.close();
            RQ.add(new Request(loggedStudent.getName(), loggedStudent.getID(), dateStudent, basket));
            for (int i = 0; i < students.size(); i++){ //sets boolean request to true if a students requests
                if (loggedStudent.getID() == students.get(i).getID()){
                    students.get(i).setRequest(true);
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        for (int i = 0; i < basket.size(); i++){
            for (int j = 0; j < inventory.size(); j++){
                if (basket.get(i).getName().equals(inventory.get(j).getName())){
                    inventory.get(j).setBorrowedQuantity(basket.get(i).getQuantity());
                }
            }
        }
        basket.clear();
    }

    //JComboBox pending;
    final JLabel isApprove = new JLabel(""); //new
    static int n = 0;

    public void approvalP(){ //new
        ArrayList<String> IDRequest = new ArrayList<String>();
        
        itemApproveP.setLayout(new GridLayout(4, 2));
        requestList.setText("");
        requestList.append("Name \t Date of Return\n");
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).isRequest())
                IDRequest.add(students.get(i).getID());
        }

        for (int j = 0; j < RQ.size(); j++){
            requestList.append(RQ.get(j).getName() + "\t" + RQ.get(j).getDate() + "\t");
            for (int a = 0; a < RQ.get(j).getItems().size(); a++) // changed n to a because idk what nasser did
                requestList.append(RQ.get(j).getItems().get(a).getName());
            requestList.append("\n");
        }
        
        if (n == 0){
            itemApproveP.add(new JLabel("Enter student name to Approve: "));
            itemApproveP.add(apName = new JTextField());
        }
        itemApproveP.add(requestScroll);
        itemApproveP.add(isApprove);
        itemApproveP.add(approveButton);
        itemApproveP.add(iSearchBack[1]);
        n++;
    }

    //brother this is O(n^4)
    public void approveItem(){ //new
        
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).isRequest()){
                for (int j = 0; j < RQ.size(); j++){
                    if (students.get(i).getName().equals(RQ.get(j).getName())){ //the inside of this code is not executed
                        isApprove.setText("Approved " + apName.getText() + "'s request");
                        students.get(i).setRequest(false);
                        students.get(i).setItems(RQ.get(j).getItems());
                        for (int q = 0; q < inventory.size(); q++){
                            for (int a = 0; a < RQ.get(j).getItems().size(); a++){
                                if (inventory.get(q).getName().equals(RQ.get(j).getItems().get(a).getName())){ 
                                    inventory.get(q).setQuantity(inventory.get(q).getQuantity() - inventory.get(q).getBorrowedQuantity());
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    public void studentResetTexts(){ //new method
        studentIName.setText("");
        studentIQuantity.setText("");
        studentItemSelect.setText("");
        studentDashboardBasket.setText("");
        studentDashboardArea.setText("");
    }
    



    public boolean checkLogin(String id, String password){//method that goes in file to check student data
        boolean isLogin = false;
        try {
            Scanner fin = new Scanner (new File("studentInfo.txt"));
            while(fin.hasNextLine()){
                String line = fin.nextLine();
                String[] data = line.split(" ");
                if(data[0].equals(id) && data[2].equals(password)){
                    loggedStudent = new Student(data[0],data[1],data[2],data[3]); //sets loggedstudent when logged in
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
            if(TechnicianCheck.equals(tech.getName()) && TPassCheck.equals(tech.getPassword())){
                TechText.setText(""); techPassword.setText("");
                cardLayout.show(frame.getContentPane(), "techPanel");
            }
            if(checkLogin(nameCheck,passwordCheck)){
                userText.setText(""); passwordText.setText("");
                cardLayout.show(frame.getContentPane(), "studentPanel");
            }
            else{
                loginPanel.add(incorrectLogin);
                frame.setVisible(true);
            }
    }


    //its in the name
    public void createNewStudent(String newID, String newName, String newPassword, String newPhoneNumber){
        Student newStudent = new Student(newID, newName, newPassword, newPhoneNumber);
        students.add(newStudent);
    }

    final JLabel enterInfo = new JLabel("Enter all required information.");
    final JLabel incorrectLogin = new JLabel("Incorrect Name or Password");
    final JLabel iLabel = new JLabel("");
    

    class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand().equals("Login"))
                cardLayout.show(frame.getContentPane(), "LoginPanel");
            if (e.getActionCommand().equals("Sign Up"))
                cardLayout.show(frame.getContentPane(), "SignupPanel");
            if (e.getActionCommand().equals("Return")){
                loggedStudent = new Student(); //resets logged student when loggin out
                basket.clear();
                cardLayout.show(frame.getContentPane(), "MainPanel");
            }      
            if (e.getActionCommand().equals("Return to Selection"))
                cardLayout.show(frame.getContentPane(), "TechOrStuPanel");
            if (e.getActionCommand().equals("Return to Main Menu"))
                cardLayout.show(frame.getContentPane(), "TechOrStuPanel");
            if (e.getActionCommand().equals("Submit")){ //submiting signup for students
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
                    createNewStudent(studentInfo[0].getText(), studentInfo[1].getText(),
                    studentInfo[2].getText(), studentInfo[3].getText());

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

    private boolean cons;
    //error where if not all info is added but name is added it executes delete item when the enter item button is pressed
    public void addInventory(){ //checks if entered item information, saves it to inventory 
        if (iName.getText().isEmpty() || iModel.getText().isEmpty() || iConsumable.getText().isEmpty()
            || iQuantity.getText().isEmpty() || iValue.getText().isEmpty() || iDate.getText().isEmpty()){
                iLabel.setText("Enter all required information.");
                
            }
        else{
            iLabel.setText("Item Added.");
            if (iConsumable.getText().equals("y"))
                cons = true;
            else // if another string is inputted it defaults to false
                cons = false;
            inventory.add(new Item(iName.getText(), iModel.getText(), Integer.parseInt(iValue.getText()),
            Integer.parseInt(iQuantity.getText()), cons, iDate.getText()));
            writeInventory();
            iName.setText(""); iModel.setText(""); iValue.setText("");
            iQuantity.setText(""); iConsumable.setText(""); iDate.setText(""); //resets textfields
        }
    }

    public void getInventoryFromFile(){ //new (I think)
        try{
            Scanner in = new Scanner(new FileReader("inventory.txt"));
            while(in.hasNext()){
                boolean c = false;
                String line = in.nextLine();
                String[] data = line.split("\t");
                if (data[4].equals("y")) c = true;
                inventory.add(new Item(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), c, data[5]));
            }
            in.close();
        }
        catch(FileNotFoundException fe){
            fe.printStackTrace();
        }
    }

    public void writeInventory(){
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream("inventory.txt", false));
            String c;
            for (int i = 0; i < inventory.size(); i++){
                c = "n";
                if (inventory.get(i).getConsumable()) c = "y";
                out.append(inventory.get(i).getName() + "\t" + inventory.get(i).getModel() + "\t" +
                inventory.get(i).getValue() + "\t" + inventory.get(i).getQuantity() + "\t" + c
                + "\t" + inventory.get(i).getDate() + "\n");
            }
            out.close();
        }
        catch(FileNotFoundException fe){
            fe.printStackTrace();
        }
    }

    public void deleteItem(){
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).getName().equals(iName.getText())){
                inventory.get(i).setQuantity(0);
                iLabel.setText("Item Deleted");
                writeInventory();
                break;
            }
        }

        iName.setText(""); iModel.setText(""); iValue.setText("");
        iQuantity.setText(""); iConsumable.setText(""); iDate.setText("");
        
    }

    public void editItem(){ //new
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).getName().equals(iName.getText())){
                boolean b = false;
                if (!iConsumable.getText().isEmpty()){
                if (iConsumable.getText().equals("y")) b = true;
                }
                iLabel.setText("Edited " + iName.getText());
                if (!iModel.getText().isEmpty()) inventory.get(i).setModel(iModel.getText());  
                if (!iValue.getText().isEmpty()) inventory.get(i).setValue(Integer.parseInt(iValue.getText()));
                if (!iDate.getText().isEmpty()) inventory.get(i).setDate(iDate.getText());
                if (!iConsumable.getText().isEmpty()) inventory.get(i).setConsumable(b);
                if (!iQuantity.getText().isEmpty()) inventory.get(i).setQuantity(Integer.parseInt(iQuantity.getText()));
                writeInventory();
                iName.setText(""); iModel.setText(""); iValue.setText("");
                iQuantity.setText(""); iConsumable.setText(""); iDate.setText("");
                break;
            }
            else 
                iLabel.setText("Item does not exist");
        }
    }

    class techHandler implements ActionListener{ //Seperate Handler for tech buttons and options, if item already exists it edits
        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand().equals("Inventory Panel"))
                cardLayout.show(frame.getContentPane(), "InventoryPanel");
            else if (e.getActionCommand().equals("Enter Item"))
                addInventory();
            else if (e.getActionCommand().equals("Back")){
                cardLayout.show(frame.getContentPane(), "techPanel"); dashPanel.setText(""); }
            else if (e.getActionCommand().equals("Delete Item"));
                deleteItem();
            if (e.getActionCommand().equals("Dashboard")){
                dashP(); cardLayout.show(frame.getContentPane(), "DashboardPanel"); }
            if (e.getActionCommand().equals("Search"))
                cardLayout.show(frame.getContentPane(), "iSearch");
            if (e.getActionCommand().equals("enterItem"))
                searchItem();
            if (e.getActionCommand().equals("Edit Item"))
                editItem();
            if (e.getActionCommand().equals("Go Back")){
                cardLayout.show(frame.getContentPane(), "techPanel");
            }
            if (e.getActionCommand().equals("Approvals")){
                approvalP();
                cardLayout.show(frame.getContentPane(), "ApprovalPanel");
            }
            if (e.getActionCommand().equals("Approve")){
                approveItem();
            }
            if (e.getActionCommand().equals("Borrowed Student")){
                borrowedP();
                cardLayout.show(frame.getContentPane(), "borrowedPanel");
            }
            if (e.getActionCommand().equals("Search.")){
                techStudentSearcher();
            }
            if (e.getActionCommand().equals("Back")){
                cardLayout.show(frame.getContentPane(), "techPanel");
                studentSearchArea.setText("");
            }
            if (e.getActionCommand().equals("Search Student")){
                cardLayout.show(frame.getContentPane(), "studentSearchP");
            }

        }
    }

    class studentHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand().equals("Item Dashboard")){
                cardLayout.show(frame.getContentPane(), "studentDashboardPanel");
                studentDashboardSetup(); //adds items to text area
                studentDashboardBasketSetup();
            }
            if (e.getActionCommand().equals("Back")){
                cardLayout.show(frame.getContentPane(), "studentPanel");
                studentDashboardArea.setText(""); //resets text area when you leave
                studentDashboardBasket.setText("");
            }
            if (e.getActionCommand().equals("Search")){
                cardLayout.show(frame.getContentPane(), "studentItemSearch");
            }
            if (e.getActionCommand().equals("Enter Item")){
                studentSearchItem();
            }
            if (e.getActionCommand().equals("Go Back")){
                cardLayout.show(frame.getContentPane(), "studentPanel");
            }
            if (e.getActionCommand().equals("Review Basket")){
                cardLayout.show(frame.getContentPane(), "studentDashboardBasketPanel");
            }
            if (e.getActionCommand().equals("Add to Basket")){
                studentBasketAddItem();
            } 
            if (e.getActionCommand().equals("Return to Dashboard")){
                cardLayout.show(frame.getContentPane(), "studentDashboardPanel");

            }
            if (e.getActionCommand().equals("+1")){
                studentItemQuantityAdd();
            }
            if (e.getActionCommand().equals("-1")){
                studentItemQuantityReduce();   
            }
            if (e.getActionCommand().equals("Send Request")){
                sendItemRequest();
                studentResetTexts();
                cardLayout.show(frame.getContentPane(), "studentPanel");
            }
        }
    }
}
