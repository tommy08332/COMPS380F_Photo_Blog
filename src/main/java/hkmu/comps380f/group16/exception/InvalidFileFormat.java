package hkmu.comps380f.group16.exception;

public class InvalidFileFormat extends Exception{
    public InvalidFileFormat (String filename) {

        super("This file '"+ filename +"' not valid");

    }
}
