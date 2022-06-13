package com.atrin.jsonclass;

/**
 * Created by SE7EN on 06/08/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductLists {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("PicUrl")
    @Expose
    private String picUrl;
    @SerializedName("Number")
    @Expose
    private String number;

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     *
     * @param picUrl
     * The PicUrl
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     *
     * @return
     * The number
     */
    public String getNumber() {
        return number;
    }

    /**
     *
     * @param number
     * The Number
     */
    public void setNumber(String number) {
        this.number = number;
    }

}