package com.atrin.jsonclass;

/**
 * Created by SE7EN on 02/08/2016.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newsletter {
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("PicUrl")
    @Expose
    private String picUrl;
    @SerializedName("File")
    @Expose
    private String file;
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
     * The file
     */
    public String getFile() {
        return file;
    }

    /**
     *
     * @param file
     * The File
     */
    public void setFile(String file) {
        this.file = file;
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