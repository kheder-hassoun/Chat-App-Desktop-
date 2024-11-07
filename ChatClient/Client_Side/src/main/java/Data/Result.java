package Data;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean isSuccess;
    private String description;

    public Result(boolean isSuccess, String description) {
        this.isSuccess = isSuccess;
        this.description = description;
    }

    public Result() {
        this.description="";
        this.isSuccess = false;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getDescription() {
        return description;
    }
}
