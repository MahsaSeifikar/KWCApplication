package com.atrin.jsonclass;

/**
 * Created by SE7EN on 06/08/2016.
 */

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class ProductGroups {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("PicUrl")
    @Expose
    private String picUrl;
    @SerializedName("NumberMain")
    @Expose
    private String numberMain;

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
     * The numberMain
     */
    public String getNumberMain() {
        return numberMain;
    }

    /**
     *
     * @param numberMain
     * The NumberMain
     */
    public void setNumberMain(String numberMain) {
        this.numberMain = numberMain;
    }

}