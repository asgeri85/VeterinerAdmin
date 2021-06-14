package com.example.veterinerappadmin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.AsiPetSonuc;
import com.example.veterinerappadmin.Classess.KampanyaEkleSonuc;
import com.example.veterinerappadmin.R;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsiAdapter extends RecyclerView.Adapter<AsiAdapter.AsiTutucu> {
    private List<AsiPetSonuc>asiPetSonucList;
    private Context mContext;
    private AdminDao dao;
    public AsiAdapter(List<AsiPetSonuc> asiPetSonucList, Context mContext) {
        this.asiPetSonucList = asiPetSonucList;
        this.mContext = mContext;
        dao= ApiUtils.getDao();
    }

    @NonNull
    @Override
    public AsiTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_asi,parent,false);
        return new AsiTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsiTutucu holder, int position) {
        AsiPetSonuc asi=asiPetSonucList.get(position);
        holder.textView.setText(asi.getUsername()+" müşterinizin "+asi.getPetAd()+" isimli peti "+asi.getAsiIsmi()+" isimli aşısı yapılıcaktır.");
        Picasso.get().load(asi.getPetResim()).into(holder.imageView);

        holder.imageButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guncelleAsi(asi.getId(),"onayla",position);
            }
        });

        holder.imageButtonİptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guncelleAsi(asi.getId(),"iptal",position);
            }
        });

        holder.imageButtonAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ara(asi.getTelefon());
            }
        });
    }

    @Override
    public int getItemCount() {
        return asiPetSonucList.size();
    }

    public class AsiTutucu extends RecyclerView.ViewHolder{
        private ImageButton imageButtonAra,imageButtonOk,imageButtonİptal;
        private TextView textView;
        private ImageView imageView;
        public AsiTutucu(@NonNull View itemView) {
            super(itemView);
            imageButtonAra=itemView.findViewById(R.id.buttonAsiAra);
            imageButtonOk=itemView.findViewById(R.id.buttonAsiOk);
            imageButtonİptal=itemView.findViewById(R.id.buttonİptalAsi);
            imageView=itemView.findViewById(R.id.imageViewAsiCard);
            textView=itemView.findViewById(R.id.textViewAsiCard);
        }
    }

    public void guncelleAsi(String id,String islem,int ps){
        dao.asiGuncelle(id,islem).enqueue(new Callback<KampanyaEkleSonuc>() {
            @Override
            public void onResponse(Call<KampanyaEkleSonuc> call, Response<KampanyaEkleSonuc> response) {
                guncelle(ps);
                Toast.makeText(mContext,"İşlem onaylandı",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<KampanyaEkleSonuc> call, Throwable t) {

            }
        });
    }

    public void guncelle(int position){
        asiPetSonucList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void ara(String numara){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:"+numara));
        mContext.startActivity(intent);
    }
}
