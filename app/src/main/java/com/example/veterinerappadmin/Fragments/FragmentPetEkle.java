package com.example.veterinerappadmin.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.KampanyaEkleSonuc;
import com.example.veterinerappadmin.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPetEkle extends Fragment {
    private View view;
    private EditText editTextAd,editTextTur,editTextCins;
    private Button button;
    private ImageView imageView;
    private AdminDao dao;
    private Bitmap bitmap;
    private String imageString="";
    private String id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_petekle,container,false);
        tanimla();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galeriAc();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ad=editTextAd.getText().toString();
                String tur=editTextTur.getText().toString();
                String cins=editTextCins.getText().toString();

                if (!ad.isEmpty() && !tur.isEmpty() && !cins.isEmpty() && !imagetoString().equals("")){
                    petEkle(id,ad,tur,cins,imageString);
                }else{
                    Toast.makeText(getContext(),"Lütfen tüm alanları doldurun ve fotoğraf ekleyin",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    public void tanimla(){
        editTextAd=view.findViewById(R.id.editPetAd);
        editTextCins=view.findViewById(R.id.editPetCins);
        editTextTur=view.findViewById(R.id.editPetTur);
        button=view.findViewById(R.id.buttonPetSistemeEkle);
        imageView=view.findViewById(R.id.imageViewPetSistem);
        dao= ApiUtils.getDao();
        id=getArguments().getString("musteri_id");
    }

    public void galeriAc(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,777);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==777  && data != null){
            Uri uri=data.getData();

            try {
                bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String imagetoString(){
        if (bitmap!=null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            imageString = Base64.encodeToString(bytes, Base64.DEFAULT);
            return imageString;
        }else{
            return imageString;
        }
    }

    public void petEkle(String id,String ad,String tur,String cins,String resim){
        dao.petSistemeGonder(id,ad,tur,cins,resim).enqueue(new Callback<KampanyaEkleSonuc>() {
            @Override
            public void onResponse(Call<KampanyaEkleSonuc> call, Response<KampanyaEkleSonuc> response) {
                if (response.body().getTf()){
                    editTextAd.setText("");
                    editTextCins.setText("");
                    editTextTur.setText("");
                    Fragment fragment=new FragmentKullaniciPet();
                    Bundle bundle=new Bundle();
                    bundle.putString("musid",id);
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
                    Toast.makeText(getContext(),response.body().getYazi(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),response.body().getYazi(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KampanyaEkleSonuc> call, Throwable t) {

            }
        });
    }
}
