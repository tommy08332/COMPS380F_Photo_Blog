package hkmu.comps380f.group16.exception;

public class UserNotFound extends Exception{

    public UserNotFound(String username){

        super("User '" + username + "' not found");

    }

}
