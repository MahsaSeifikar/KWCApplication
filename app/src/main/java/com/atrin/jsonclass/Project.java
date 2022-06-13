package com.atrin.jsonclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Project {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("PicUrl")
    @Expose
    private String picUrl;
    @SerializedName("GalleryCount")
    @Expose
    private String galleryCount;
    @SerializedName("GalleryPix")
    @Expose
    private String galleryPix;

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
     * The galleryCount
     */
    public String getGalleryCount() {
        return galleryCount;
    }

    /**
     *
     * @param galleryCount
     * The GalleryCount
     */
    public void setGalleryCount(String galleryCount) {
        this.galleryCount = galleryCount;
    }

    /**
     *
     * @return
     * The galleryPix
     */
    public String getGalleryPix() {
        return galleryPix;
    }

    /**
     *
     * @param galleryPix
     * The GalleryPix
     */
    public void setGalleryPix(String galleryPix) {
        this.galleryPix = galleryPix;
    }

}