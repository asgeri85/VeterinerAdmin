package com.example.veterinerappadmin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.veterinerappadmin.R;

public class FragmentHome extends Fragment {
    private CardView cardViewusers,cardViewPetler,cardViewSorulat,cardViewkampanyalar;
    private View view;
    private Fragment fragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        tanimla();

        cardViewkampanyalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentKampanya();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });

        cardViewPetler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentAsi();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });

        cardViewSorulat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentSoru();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });

        cardViewusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentKullaniclar();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });
        return view;
    }

    public void tanimla(){
        cardViewusers=view.findViewById(R.id.cardKullanicilar);
        cardViewPetler=view.findViewById(R.id.cardPetler);
        cardViewSorulat=view.findViewById(R.id.cardSorular);
        cardViewkampanyalar=view.findViewById(R.id.cardkampanya);

    }
}
