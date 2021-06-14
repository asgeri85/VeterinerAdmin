package com.example.veterinerappadmin.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.KampanyaEkleSonuc;
import com.example.veterinerappadmin.Classess.UserSonuc;
import com.example.veterinerappadmin.Fragments.FragmentKullaniciPet;
import com.example.veterinerappadmin.R;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KullanicilarAdapter extends RecyclerView.Adapter<KullanicilarAdapter.KullaiciTutucu>{
    private List<UserSonuc>userSonucList;
    private Context mContext;
    private AdminDao dao;

    public KullanicilarAdapter(List<UserSonuc> userSonucList, Context mContext) {
        this.userSonucList = userSonucList;
        this.mContext = mContext;
        dao= ApiUtils.getDao();
    }

    @NonNull
    @Override
    public KullaiciTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_kullanici,parent,false);
        return new KullaiciTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KullaiciTutucu holder, int position) {
        UserSonuc user=userSonucList.get(position);
        holder.textView.setText(user.getUsername());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ara(user.getTelefon());
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertAc(user.getId(),position);
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("musid",user.getId());
                Fragment fragment=new FragmentKullaniciPet();
                fragment.setArguments(bundle);
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userSonucList.size();
    }

    public class KullaiciTutucu extends RecyclerView.ViewHolder{
        private TextView textView;
        private Button button;
        private ImageButton imageButton;
        private CardView cardView;
        public KullaiciTutucu(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textViewKullaniciAd);
            imageButton=itemView.findViewById(R.id.buttonCardUserArama);
            button=itemView.findViewById(R.id.buttonCardKullaniciPet);
            cardView=itemView.findViewById(R.id.cardKullaniciSil);
        }
    }

    public void ara(String numara){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:"+numara));
        mContext.startActivity(intent);
    }

    public void guncelle(int position){
        userSonucList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void alertAc(String id,int pos){
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("Kullanici  silme");
        builder.setMessage("Kullaniciyi silmek isitiyor musunuz?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                kullaniciSil(id,pos);
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

    public void kullaniciSil(String id,int pos){
        dao.kullanisiSil(id).enqueue(new Callback<KampanyaEkleSonuc>() {
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
