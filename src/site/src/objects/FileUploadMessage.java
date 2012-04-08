package objects;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 03.04.2012
 * Time: 2:05:51
 * To change this template use File | Settings | File Templates.
 */
public class FileUploadMessage {
    private int code;
    private String message;
    private String details;


    public FileUploadMessage(int code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
