package hkmu.comps380f.group16.exception;

public class PhoneNumberAlreadyUsed extends Exception{
    public PhoneNumberAlreadyUsed (String phoneNum) {

        super("This '"+ phoneNum +"' already registered");

    }
}
