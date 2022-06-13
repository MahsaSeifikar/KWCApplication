package com.atrin.jsonclass;

/**
 * Created by Mahsa on 15/07/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleNetworkList {

    @SerializedName("ShopName")
    @Expose
    private String shopName;
    @SerializedName("NameFamily")
    @Expose
    private String nameFamily;
    @SerializedName("Ostan")
    @Expose
    private String ostan;
    @SerializedName("Shahr")
    @Expose
    private String shahr;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Tel")
    @Expose
    private String tel;
    @SerializedName("PicUrl")
    @Expose
    private String picUrl;

    /**
     *
     * @return
     * The shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     *
     * @param shopName
     * The ShopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     *
     * @return
     * The nameFamily
     */
    public String getNameFamily() {
        return nameFamily;
    }

    /**
     *
     * @param nameFamily
     * The NameFamily
     */
    public void setNameFamily(String nameFamily) {
        this.nameFamily = nameFamily;
    }

    /**
     *
     * @return
     * The ostan
     */
    public String getOstan() {
        return ostan;
    }

    /**
     *
     * @param ostan
     * The Ostan
     */
    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    /**
     *
     * @return
     * The shahr
     */
    public String getShahr() {
        return shahr;
    }

    /**
     *
     * @param shahr
     * The Shahr
     */
    public void setShahr(String shahr) {
        this.shahr = shahr;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The tel
     */
    public String getTel() {
        return tel;
    }

    /**
     *
     * @param tel
     * The Tel
     */
    public void setTel(String tel) {
        this.tel = tel;
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