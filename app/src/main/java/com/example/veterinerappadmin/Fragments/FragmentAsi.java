package com.example.veterinerappadmin.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.veterinerappadmin.Adapters.AsiAdapter;
import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.AsiPetSonuc;
import com.example.veterinerappadmin.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAsi extends Fragment {
    private View view;
    private Date date;
    private DateFormat dateFormat;
    private String today;
    private AdminDao dao;
    private List<AsiPetSonuc>asiPetSonucList;
    private RecyclerView recyclerView;
    private AsiAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_asi,container,false);
        tanimla();
        petler(today);
        return view;
    }

    public void tanimla(){
        recyclerView=view.findViewById(R.id.rvAsi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        dao= ApiUtils.getDao();
        dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        date= Calendar.getInstance().getTime();
        today=dateFormat.format(date);
        asiPetSonucList=new ArrayList<>();
    }

    public void petler(String tarih){
        dao.asiPetGetir(tarih).enqueue(new Callback<List<AsiPetSonuc>>() {
            @Override
            public void onResponse(Call<List<AsiPetSonuc>> call, Response<List<AsiPetSonuc>> response) {
                if (response.body().size()>0){
                    asiPetSonucList=response.body();
                    adapter=new AsiAdapter(asiPetSonucList,getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<AsiPetSonuc>> call, Throwable t) {
                Toast.makeText(getContext(),"Bugun bakılıcak pet bulunmamaktadır",Toast.LENGTH_LONG).show();
            }
        });
    }
}
