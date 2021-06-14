package com.example.veterinerappadmin.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.KampanyaEkleSonuc;
import com.example.veterinerappadmin.Classess.KampanyaSonuc;
import com.example.veterinerappadmin.R;
import com.squareup.picasso.Picasso;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KampanyaAdapter extends RecyclerView.Adapter<KampanyaAdapter.KampanyaTutuxu> {
    private List<KampanyaSonuc>kampanyaSonucList;
    private Context mContext;
    private AdminDao dao;
    public KampanyaAdapter(List<KampanyaSonuc> kampanyaSonucList, Context mContext) {
        this.kampanyaSonucList = kampanyaSonucList;
        this.mContext = mContext;
        dao= ApiUtils.getDao();
    }

    @NonNull
    @Override
    public KampanyaTutuxu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_kampanya,parent,false);
        return new KampanyaTutuxu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KampanyaTutuxu holder, int position) {
        KampanyaSonuc sonuc=kampanyaSonucList.get(position);
        holder.textViewAd.setText(sonuc.getBaslik());
        holder.textViewAciklama.setText(sonuc.getAciklama());
        Picasso.get().load(sonuc.getResim()).resize(250,250).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertAc(sonuc.getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kampanyaSonucList.size();
    }

    public class KampanyaTutuxu extends RecyclerView.ViewHolder{
        private TextView textViewAd,textViewAciklama;
        private ImageView imageView;
        private CardView cardView;
        public KampanyaTutuxu(@NonNull View itemView) {
            super(itemView);
            textViewAd=itemView.findViewById(R.id.textViewKampanya);
            textViewAciklama=itemView.findViewById(R.id.textViewKampanyaAciklama);
            imageView=itemView.findViewById(R.id.imageViewKAmpanya);
            cardView=itemView.findViewById(R.id.cardkampanyaSil);
        }
    }

    public void sil(String id){
        dao.kampanyaSil(id).enqueue(new Callback<KampanyaEkleSonuc>() {
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

    public void alertAc(String id,int pos){
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("Kampanya silme");
        builder.setMessage("Kampanya silinsin mi?");

        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sil(id);
                guncelle(pos);
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

    public void guncelle(int position){
        kampanyaSonucList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
