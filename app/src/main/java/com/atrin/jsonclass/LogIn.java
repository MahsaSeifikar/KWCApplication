package com.atrin.jsonclass;

/**
 * Created by SE7EN on 20/09/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogIn {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("UserNumber")
    @Expose
    private String userNumber;
    @SerializedName("VName")
    @Expose
    private String vName;

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The Message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param userType
     * The UserType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     *
     * @return
     * The userNumber
     */
    public String getUserNumber() {
        return userNumber;
    }

    /**
     *
     * @param userNumber
     * The UserNumber
     */
    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    /**
     *
     * @return
     * The vName
     */
    public String getVName() {
        return vName;
    }

    /**
     *
     * @param vName
     * The VName
     */
    public void setVName(String vName) {
        this.vName = vName;
    }

}
