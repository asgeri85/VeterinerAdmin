package com.example.veterinerappadmin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinerappadmin.Adapters.SorularAdapter;
import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.SoruSonuc;
import com.example.veterinerappadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSoru extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<SoruSonuc>soruSonucList;
    private AdminDao dao;
    private SorularAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_soru,container,false);
        tanimla();
        sorular();
        return view;
    }

    public void tanimla(){
        recyclerView=view.findViewById(R.id.rvSoru);
        dao= ApiUtils.getDao();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        soruSonucList=new ArrayList<>();
    }

    public void sorular(){
        dao.sorularGetir().enqueue(new Callback<List<SoruSonuc>>() {
            @Override
            public void onResponse(Call<List<SoruSonuc>> call, Response<List<SoruSonuc>> response) {
                soruSonucList=response.body();
                adapter=new SorularAdapter(soruSonucList,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SoruSonuc>> call, Throwable t) {

            }
        });
    }
}
