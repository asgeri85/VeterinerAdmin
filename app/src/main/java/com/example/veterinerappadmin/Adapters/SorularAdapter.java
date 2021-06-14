package com.example.veterinerappadmin.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.KampanyaEkleSonuc;
import com.example.veterinerappadmin.Classess.SoruSonuc;
import com.example.veterinerappadmin.R;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SorularAdapter extends RecyclerView.Adapter<SorularAdapter.SoruTutucu>{
    private List<SoruSonuc>soruSonucList;
    private Context mContext;
    private AdminDao dao;

    public SorularAdapter(List<SoruSonuc> soruSonucList, Context mContext) {
        this.soruSonucList = soruSonucList;
        this.mContext = mContext;
        dao= ApiUtils.getDao();
    }

    @NonNull
    @Override
    public SoruTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_sorular,parent,false);
        return new SoruTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoruTutucu holder, int position) {
        SoruSonuc soruSonuc = soruSonucList.get(position);
        holder.textViewAd.setText(soruSonuc.getUsername());
        holder.textViewSoru.setText(soruSonuc.getSoru());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertAc(soruSonuc.getId(),soruSonuc.getSoranId(),position,soruSonuc.getSoru());
            }
        });
    }

    @Override
    public int getItemCount() {
        return soruSonucList.size();
    }

    public class SoruTutucu extends RecyclerView.ViewHolder{
        private TextView textViewAd,textViewSoru;
        private ImageButton button;
        public SoruTutucu(@NonNull View itemView) {
            super(itemView);
            textViewAd=itemView.findViewById(R.id.textViewCardSoruAd);
            textViewSoru=itemView.findViewById(R.id.textViewCardSoru);
            button=itemView.findViewById(R.id.buttonSoruCard);
        }
    }

    public void cavabGonder(String id,String musteri,String cevap,int pos){
        dao.cavapVer(id,musteri,cevap).enqueue(new Callback<KampanyaEkleSonuc>() {
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

    public void alertAc(String id,String musteri,int pos,String soru){
        View alert=LayoutInflater.from(mContext).inflate(R.layout.cevapver_alert,null);
        final EditText editText=alert.findViewById(R.id.editAlertCevap);
        final TextView textView=alert.findViewById(R.id.textViewAlertCevapSoru);
        textView.setText(soru);
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setView(alert);

        builder.setPositiveButton("Cevapla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cavabGonder(id,musteri,editText.getText().toString(),pos);
            }
        });

        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

    public void guncelle(int position){
        soruSonucList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
