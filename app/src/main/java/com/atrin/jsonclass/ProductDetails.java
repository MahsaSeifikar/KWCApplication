package com.atrin.jsonclass;

/**
 * Created by SE7EN on 06/08/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetails {

    @SerializedName("Cat")
    @Expose
    private String cat;
    @SerializedName("Group")
    @Expose
    private String group;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("Power")
    @Expose
    private String power;
    @SerializedName("Color")
    @Expose
    private String color;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Price")
    @Expose
    private String price;
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
     * The cat
     */
    public String getCat() {
        return cat;
    }

    /**
     *
     * @param cat
     * The Cat
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     *
     * @return
     * The group
     */
    public String getGroup() {
        return group;
    }

    /**
     *
     * @param group
     * The Group
     */
    public void setGroup(String group) {
        this.group = group;
    }

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
     * The power
     */
    public String getPower() {
        return power;
    }

    /**
     *
     * @param power
     * The Power
     */
    public void setPower(String power) {
        this.power = power;
    }

    /**
     *
     * @return
     * The color
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color
     * The Color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The Code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The Price
     */
    public void setPrice(String price) {
        this.price = price;
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