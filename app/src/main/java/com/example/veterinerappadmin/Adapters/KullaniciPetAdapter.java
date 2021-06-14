package com.example.veterinerappadmin.Adapters;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.KampanyaEkleSonuc;
import com.example.veterinerappadmin.Classess.PetlerSonuc;
import com.example.veterinerappadmin.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KullaniciPetAdapter extends RecyclerView.Adapter<KullaniciPetAdapter.PetAdapter>{
    private List<PetlerSonuc>petlerSonucList;
    private Context mContext;
    private AdminDao dao;
    private AlertDialog.Builder builder;
    private String secilenTarih,formatlitarih;
    public KullaniciPetAdapter(List<PetlerSonuc> petlerSonucList, Context mContext) {
        this.petlerSonucList = petlerSonucList;
        this.mContext = mContext;
        dao= ApiUtils.getDao();
    }

    @NonNull
    @Override
    public PetAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_kullanici_pet,parent,false);
        return new PetAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter holder, int position) {
        PetlerSonuc petlerSonuc=petlerSonucList.get(position);
        holder.textView.setText("Pet ismi: "+petlerSonuc.getPetAd()+"\nCinsi: "+petlerSonuc.getPetCins()+"\ntürü: "+petlerSonuc.getPetTur());
        Picasso.get().load(petlerSonuc.getPetResim()).resize(300,300).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertAc(petlerSonuc.getMusteriId(),petlerSonuc.getId());
            }
        });

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                silAlert(petlerSonuc.getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return petlerSonucList.size();
    }

    public class PetAdapter extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;
        private TextView textView;
        private ImageButton imageButton;
        public PetAdapter(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardKullaniciPet);
            imageView=itemView.findViewById(R.id.imageViewKullaniciPet);
            textView=itemView.findViewById(R.id.textViewkullaniciPet);
            imageButton=itemView.findViewById(R.id.imageButtonPetSil);
        }
    }

    public void alertAc(String musteri,String pet){
        View alert=LayoutInflater.from(mContext).inflate(R.layout.asi_alert,null);
        final EditText ad=alert.findViewById(R.id.editAsiAd);
        final EditText tarih=alert.findViewById(R.id.editAsiTarih);

        builder =new AlertDialog.Builder(mContext);
        builder.setView(alert);
        builder.setTitle("Asi bilgileri");

        Calendar zaman=Calendar.getInstance();
        int il=zaman.get(Calendar.YEAR);
        int ay=zaman.get(Calendar.MONTH);
        int gun=zaman.get(Calendar.DAY_OF_MONTH);



        builder.setPositiveButton("Aşı ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!ad.getText().toString().isEmpty() && !tarih.getText().toString().isEmpty()) {
                    asiYukle(musteri, ad.getText().toString(), tarih.getText().toString(), pet);
                }else{
                    Toast.makeText(mContext,"Aşı adını ve tarihi giriniz",Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setCancelable(false);
        builder.create().show();

        DatePickerDialog datePickerDialog;
        datePickerDialog=new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               DateFormat input=new SimpleDateFormat("dd/MM/yyyy");
               DateFormat output=new SimpleDateFormat("dd/MM/yyyy");
               secilenTarih=i2+"/"+(i1+1)+"/"+i;

                try {
                    Date date=input.parse(secilenTarih);
                    formatlitarih=output.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tarih.setText(formatlitarih);
            }
        },il,ay,gun);

        datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE,"Ayarla",datePickerDialog);
        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE,"İptal",datePickerDialog);


        tarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    public void asiYukle(String musteri,String ad,String tarih,String pet){
        dao.asiEkle(musteri, ad, tarih, pet).enqueue(new Callback<KampanyaEkleSonuc>() {
            @Override
            public void onResponse(Call<KampanyaEkleSonuc> call, Response<KampanyaEkleSonuc> response) {
                if (response.body().getTf()){
                    Toast.makeText(mContext,response.body().getYazi(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,response.body().getYazi(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KampanyaEkleSonuc> call, Throwable t) {

            }
        });
    }

    public void guncelle(int position){
        petlerSonucList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void silAlert(String id,int pos){
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("Pet silme");
        builder.setMessage("Peti silmek isitiyor musunuz?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                petSil(id,pos);
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

    public void petSil(String id,int pos){
        dao.petSil(id).enqueue(new Callback<KampanyaEkleSonuc>() {
            @Override
            public void onResponse(Call<KampanyaEkleSonuc> call, Response<KampanyaEkleSonuc> response) {
                if (response.body().getTf()){
                    guncelle(pos);
                    Toast.makeText(mContext,response.body().getYazi(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,response.body().getYazi(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KampanyaEkleSonuc> call, Throwable t) {

            }
        });
    }
}
