
package com.example.veterinerappadmin.Classess;
;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SoruSonuc {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("soran_id")
    @Expose
    private String soranId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("telefon")
    @Expose
    private String telefon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getSoranId() {
        return soranId;
    }

    public void setSoranId(String soranId) {
        this.soranId = soranId;
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

}
