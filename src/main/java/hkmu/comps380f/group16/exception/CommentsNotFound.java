public class CommentsNotFound extends Exception{

    public CommentsNotFound(String photo){

        super("Photo ID('" + photo + "') commnets not found");

    }

}