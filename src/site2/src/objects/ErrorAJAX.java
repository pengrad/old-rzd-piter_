package objects;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 23.09.11
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
public class ErrorAJAX {
    private String fieldName;
    private String errorMessage;

    public ErrorAJAX(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
