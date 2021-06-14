
package com.example.veterinerappadmin.Classess;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class KampanyaSonuc {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("baslik")
    @Expose
    private String baslik;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("resim")
    @Expose
    private String resim;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

}
