package com.example.veterinerappadmin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.veterinerappadmin.Adapters.KampanyaAdapter;
import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.KampanyaSonuc;
import com.example.veterinerappadmin.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKampanya extends Fragment {
    private View view;
    private RecyclerView rv;
    private List<KampanyaSonuc>kampanyaSonucList;
    private AdminDao dao;
    private KampanyaAdapter adapter;
    private ImageButton button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_kampanya,container,false);
        tanimla();
        kampanyaGetir();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,new FragmentKampanyaEkle()).commit();
            }
        });
        return view;
    }


    public void tanimla(){
        rv=view.findViewById(R.id.rvkampanya);
        button=view.findViewById(R.id.buttonKampanyaEkle);
        dao= ApiUtils.getDao();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        kampanyaSonucList=new ArrayList<>();

    }

    public void kampanyaGetir(){
        dao.tumkampanyalar().enqueue(new Callback<List<KampanyaSonuc>>() {
            @Override
            public void onResponse(Call<List<KampanyaSonuc>> call, Response<List<KampanyaSonuc>> response) {
                kampanyaSonucList=response.body();
                adapter=new KampanyaAdapter(kampanyaSonucList,getContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<KampanyaSonuc>> call, Throwable t) {

            }
        });
    }
}
