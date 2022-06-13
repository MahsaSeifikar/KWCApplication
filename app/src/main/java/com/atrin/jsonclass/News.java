package com.atrin.jsonclass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Mahsa on 6/10/2016.
 */

public class News {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("PicUrl_S")
    @Expose
    private String picUrlS;
    @SerializedName("PicUrl_B")
    @Expose
    private String picUrlB;
    @SerializedName("Date")
    @Expose
    private String date;

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
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The Text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The picUrlS
     */
    public String getPicUrlS() {
        return picUrlS;
    }

    /**
     *
     * @param picUrlS
     * The PicUrl_S
     */
    public void setPicUrlS(String picUrlS) {
        this.picUrlS = picUrlS;
    }

    /**
     *
     * @return
     * The picUrlB
     */
    public String getPicUrlB() {
        return picUrlB;
    }

    /**
     *
     * @param picUrlB
     * The PicUrl_B
     */
    public void setPicUrlB(String picUrlB) {
        this.picUrlB = picUrlB;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The Date
     */
    public void setDate(String date) {
        this.date = date;
    }

}