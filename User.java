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

    public User(String id, String n, String p){
        user_id = id;
        name = n;
        pass = p;
    }

    public User(User u){
        this.user_id = u.user_id;
        this.name = u.name;
        this.pass = u.pass;
    }

}
