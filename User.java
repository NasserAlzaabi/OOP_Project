package OOP_Project;
import java.io.*;
import java.util.*;

class User{
    protected String name, user_id, pass;

    public User(){
        name = "";
        user_id = "";
        pass = "";
    }

    public User(String n, String u, String p){
        name = n;
        user_id = u;
        pass = p;
    }

}
