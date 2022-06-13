package com.atrin.jsonclass;

/**
 * Created by SE7EN on 22/07/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AfterSaleList {


    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Ostan")
    @Expose
    private String ostan;
    @SerializedName("Shahr")
    @Expose
    private String shahr;
    @SerializedName("Tel")
    @Expose
    private String tel;
    @SerializedName("Mobile")
    @Expose
    private String mobile;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The Name
     */
    public void setName(String name) {
        this.name = name;
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
     * The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     * The Mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
