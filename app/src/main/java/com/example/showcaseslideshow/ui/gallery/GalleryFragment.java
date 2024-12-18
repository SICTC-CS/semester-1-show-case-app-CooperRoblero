package com.example.showcaseslideshow.ui.gallery;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.showcaseslideshow.R;

import java.util.Random;


class SciFiNameFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button generateButton = root.findViewById(R.id.generate_name_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView resultText = root.findViewById(R.id.result_text);


        generateButton.setOnClickListener(v -> {
            String[] prefixes = {"Zor", "Xan", "Kal", "Ven", "Mar", "Til"};
            String[] suffixes = {"tar", "lon", "vek", "rin", "dus", "zel"};


            Random random = new Random();
            String sciFiName = prefixes[random.nextInt(prefixes.length)] + suffixes[random.nextInt(suffixes.length)];


            resultText.setText("Your Sci-Fi Name: " + sciFiName);
        });


        return root;
    }
}
