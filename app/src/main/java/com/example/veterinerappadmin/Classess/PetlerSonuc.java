
package com.example.veterinerappadmin.Classess;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PetlerSonuc {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("musteri_id")
    @Expose
    private String musteriId;
    @SerializedName("pet_ad")
    @Expose
    private String petAd;
    @SerializedName("pet_tur")
    @Expose
    private String petTur;
    @SerializedName("pet_cins")
    @Expose
    private String petCins;
    @SerializedName("pet_resim")
    @Expose
    private String petResim;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusteriId() {
        return musteriId;
    }

    public void setMusteriId(String musteriId) {
        this.musteriId = musteriId;
    }

    public String getPetAd() {
        return petAd;
    }

    public void setPetAd(String petAd) {
        this.petAd = petAd;
    }

    public String getPetTur() {
        return petTur;
    }

    public void setPetTur(String petTur) {
        this.petTur = petTur;
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

    @Override
    public String toString() {
        return "PetlerSonuc{" +
                "id='" + id + '\'' +
                ", musteriId='" + musteriId + '\'' +
                ", petAd='" + petAd + '\'' +
                ", petTur='" + petTur + '\'' +
                ", petCins='" + petCins + '\'' +
                ", petResim='" + petResim + '\'' +
                '}';
    }
}
