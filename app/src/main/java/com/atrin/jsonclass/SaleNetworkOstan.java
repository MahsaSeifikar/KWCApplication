package com.atrin.jsonclass;

/**
 * Created by Mahsa Seifikar on 15/07/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleNetworkOstan {

    @SerializedName("OstanName")
    @Expose
    private String ostanName;

    /**
     *
     * @return
     * The ostanName
     */
    public String getOstanName() {
        return ostanName;
    }

    /**
     *
     * @param ostanName
     * The OstanName
     */
    public void setOstanName(String ostanName) {
        this.ostanName = ostanName;
    }

}