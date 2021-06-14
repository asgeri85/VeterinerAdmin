package com.example.veterinerappadmin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.veterinerappadmin.Adapters.KullaniciPetAdapter;
import com.example.veterinerappadmin.ApiServices.AdminDao;
import com.example.veterinerappadmin.ApiServices.ApiUtils;
import com.example.veterinerappadmin.Classess.PetlerSonuc;
import com.example.veterinerappadmin.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKullaniciPet extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private TextView textView;
    private String id;
    private AdminDao dao;
    private List<PetlerSonuc>petlerSonucList;
    private KullaniciPetAdapter adapter;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_kullanici_pet,container,false);
        tanimla();
        petKullanici(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle =new Bundle();
                bundle.putString("musteri_id",id);
                Fragment fragment=new FragmentPetEkle();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });
        return view;
    }

    public void tanimla(){
        recyclerView=view.findViewById(R.id.rvKullaniciPet);
        dao= ApiUtils.getDao();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        textView=view.findViewById(R.id.textView7);
        imageView=view.findViewById(R.id.imageView6);
        button=view.findViewById(R.id.buttonKullaniciPetEkle);
        id=getArguments().getString("musid");
        imageView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        petlerSonucList=new ArrayList<>();
    }

    public void petKullanici(String id){
        dao.petKullanici(id).enqueue(new Callback<List<PetlerSonuc>>() {
            @Override
            public void onResponse(Call<List<PetlerSonuc>> call, Response<List<PetlerSonuc>> response) {
                if (response.body().size()>0){
                    petlerSonucList=response.body();
                    adapter=new KullaniciPetAdapter(petlerSonucList,getContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<PetlerSonuc>> call, Throwable t) {
                imageView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });
    }
}
