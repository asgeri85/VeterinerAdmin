
package com.example.veterinerappadmin.Classess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AsiPetSonuc {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("asi_ismi")
    @Expose
    private String asiIsmi;
    @SerializedName("pet_ad")
    @Expose
    private String petAd;
    @SerializedName("pet_cins")
    @Expose
    private String petCins;
    @SerializedName("pet_resim")
    @Expose
    private String petResim;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("telefon")
    @Expose
    private String telefon;
    @SerializedName("pet_tur")
    @Expose
    private String petTur;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsiIsmi() {
        return asiIsmi;
    }

    public void setAsiIsmi(String asiIsmi) {
        this.asiIsmi = asiIsmi;
    }

    public String getPetAd() {
        return petAd;
    }

    public void setPetAd(String petAd) {
        this.petAd = petAd;
    }

    public String getPetCins() {
        return petCins;
    }

    public void setPetCins(String petCins) {
        this.petCins = petCins;
    }

    public String getPetResim() {
        return petResim;
    }

    public void setPetResim(String petResim) {
        this.petResim = petResim;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPetTur() {
        return petTur;
    }

    public void setPetTur(String petTur) {
        this.petTur = petTur;
    }

    @Override
    public String toString() {
        return "AsiPetSonuc{" +
                "id='" + id + '\'' +
                ", asiIsmi='" + asiIsmi + '\'' +
                ", petAd='" + petAd + '\'' +
                ", petCins='" + petCins + '\'' +
                ", petResim='" + petResim + '\'' +
                ", username='" + username + '\'' +
                ", telefon='" + telefon + '\'' +
                ", petTur='" + petTur + '\'' +
                '}';
    }
}
