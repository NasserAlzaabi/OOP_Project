package OOP_Project;

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
