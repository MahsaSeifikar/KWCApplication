package com.atrin.jsonclass;

/**
 * Created by SE7EN on 05/07/2016.
 */

//        import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Award {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("PicUrl_S")
    @Expose
    private String picUrlS;
    @SerializedName("PicUrl_B")
    @Expose
    private String picUrlB;

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

}