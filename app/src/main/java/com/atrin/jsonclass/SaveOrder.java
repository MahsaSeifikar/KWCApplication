package com.atrin.jsonclass;

/**
 * Created by SE7EN on 11/10/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveOrder {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("FactorNumber")
    @Expose
    private String factorNumber;

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
     * The factorNumber
     */
    public String getFactorNumber() {
        return factorNumber;
    }

    /**
     *
     * @param factorNumber
     * The FactorNumber
     */
    public void setFactorNumber(String factorNumber) {
        this.factorNumber = factorNumber;
    }

}