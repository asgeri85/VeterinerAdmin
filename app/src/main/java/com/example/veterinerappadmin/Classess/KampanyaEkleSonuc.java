
package com.example.veterinerappadmin.Classess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KampanyaEkleSonuc {

    @SerializedName("yazi")
    @Expose
    private String yazi;
    @SerializedName("tf")
    @Expose
    private Boolean tf;

    public String getYazi() {
        return yazi;
    }

    public void setYazi(String yazi) {
        this.yazi = yazi;
    }

    public Boolean getTf() {
        return tf;
    }

    public void setTf(Boolean tf) {
        this.tf = tf;
    }

}
