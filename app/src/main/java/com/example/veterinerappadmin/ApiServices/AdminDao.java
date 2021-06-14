package com.example.veterinerappadmin.ApiServices;

import com.example.veterinerappadmin.Classess.AsiPetSonuc;
import com.example.veterinerappadmin.Classess.KampanyaEkleSonuc;
import com.example.veterinerappadmin.Classess.KampanyaSonuc;
import com.example.veterinerappadmin.Classess.PetlerSonuc;
import com.example.veterinerappadmin.Classess.SoruSonuc;
import com.example.veterinerappadmin.Classess.UserSonuc;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AdminDao {
    @GET("kampanya.php")
    Call<List<KampanyaSonuc>>tumkampanyalar();

    @POST("kampanyaekle.php")
    @FormUrlEncoded
    Call<KampanyaEkleSonuc>kampanyaGonder(@Field("ad") String ad, @Field("aciklama") String aciklama, @Field("resim") String resim);

    @POST("kampanyasil.php")
    @FormUrlEncoded
    Call<KampanyaEkleSonuc>kampanyaSil(@Field("id") String id);

    @POST("asiAdmin.php")
    @FormUrlEncoded
    Call<List<AsiPetSonuc>>asiPetGetir(@Field("tarih") String tarih);

    @POST("asidurum.php")
    @FormUrlEncoded
    Call<KampanyaEkleSonuc>asiGuncelle(@Field("id") String id,@Field("islem") String islem);

    @GET("soruadmin.php")
    Call<List<SoruSonuc>>sorularGetir();

    @POST("cevapveradmin.php")
    @FormUrlEncoded
    Call<KampanyaEkleSonuc>cavapVer(@Field("id") String id,@Field("musteri") String musteri,@Field("ceevap") String cevap);

    @GET("kullanicilar.php")
    Call<List<UserSonuc>>tumKullancilar();

    @POST("petler.php")
    @FormUrlEncoded
    Call<List<PetlerSonuc>>petKullanici(@Field("id") String id);

    @POST("petEkle.php")
    @FormUrlEncoded
    Call<KampanyaEkleSonuc>petSistemeGonder(@Field("id") String id,@Field("ad") String ad,@Field("tur") String tur,
                                            @Field("cins") String cins,@Field("resim") String resim);

    @POST("asiekle.php")
    @FormUrlEncoded
    Call<KampanyaEkleSonuc>asiEkle(@Field("musteri") String musteri,@Field("ad") String ad,@Field("tarih") String tarih,@Field("pet") String pet);

    @POST("kullanicisil.php")
    @FormUrlEncoded
    Call<KampanyaEkleSonuc>kullanisiSil(@Field("id") String id);

    @POST("petsil.php")
    @FormUrlEncoded
    Call<KampanyaEkleSonuc>petSil(@Field("id") String id);

}
