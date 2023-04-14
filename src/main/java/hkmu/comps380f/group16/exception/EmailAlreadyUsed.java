package hkmu.comps380f.group16.exception;

public class EmailAlreadyUsed extends Exception {

    public EmailAlreadyUsed (String email) {

        super("This '"+ email +"' already registered");

    }
}
