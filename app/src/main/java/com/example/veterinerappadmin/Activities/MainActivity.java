package com.example.veterinerappadmin.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.veterinerappadmin.Fragments.FragmentHome;
import com.example.veterinerappadmin.R;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment;
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.imageButton);
        fragment=new FragmentHome();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_home,fragment).commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new FragmentHome();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,fragment).commit();
            }
        });

    }
}