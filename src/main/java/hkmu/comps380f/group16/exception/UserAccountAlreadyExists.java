package hkmu.comps380f.group16.exception;

public class UserAccountAlreadyExists extends Exception{
    public UserAccountAlreadyExists(String username){

        super("Username '" + username + "' already in used");

    }
}
