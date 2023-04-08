package hkmu.comps380f.group16.exception;

public class InvalidFileFormat extends Exception{
    public InvalidFileFormat (String filename) {

        super("This '"+ filename +"' not valid");

    }
}
