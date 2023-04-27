package OOP_Project;
import java.io.*;
import java.util.*;

class User{
    protected String user_id, name, pass;

    public User(){
        user_id = "";
        name = "";
        pass = "";
    }

    public User(String n, String id, String p){
        user_id = id;
        name = n;
        pass = p;
    }

}
