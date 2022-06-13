package com.atrin.jsonclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Mahsa  on 6/10/2016.
 */

public class BranchCompany {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("PicUrl")
    @Expose
    private String picUrl;

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
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The Description
     */
    public void setDescription(String description) {
        this.description = description;
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

}