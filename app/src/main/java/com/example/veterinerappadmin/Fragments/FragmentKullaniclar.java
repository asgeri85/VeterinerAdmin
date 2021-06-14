package com.example.veterinerappadmin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.veterinerappadmin.Adapters.KullanicilarAdapter;
import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.UserSonuc;
import com.example.veterinerappadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKullaniclar extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private AdminDao dao;
    private List<UserSonuc>userSonucList;
    private KullanicilarAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_kullanicilar,container,false);
        tanimla();
        kullaniclar();
        return view;
    }

    public void tanimla(){
        recyclerView=view.findViewById(R.id.rvKulanicilar);
        dao= ApiUtils.getDao();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        userSonucList=new ArrayList<>();
    }

    public void kullaniclar(){
        dao.tumKullancilar().enqueue(new Callback<List<UserSonuc>>() {
            @Override
            public void onResponse(Call<List<UserSonuc>> call, Response<List<UserSonuc>> response) {
                userSonucList=response.body();
                adapter=new KullanicilarAdapter(userSonucList,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<UserSonuc>> call, Throwable t) {

            }
        });
    }
}
