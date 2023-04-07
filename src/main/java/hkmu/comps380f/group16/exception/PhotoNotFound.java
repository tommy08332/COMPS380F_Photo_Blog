package hkmu.comps380f.group16.exception;

public class PhotoNotFound extends Exception{

    public PhotoNotFound(String photo){

        super("Photo '" + photo + "' not found");

    }

}
