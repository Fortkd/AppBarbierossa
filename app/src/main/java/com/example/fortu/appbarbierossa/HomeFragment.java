package com.example.fortu.appbarbierossa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements View.OnClickListener{

    View v;
    Button prenota;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, null);
        prenota = (Button) v.findViewById(R.id.button_prenota);
        prenota.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v){
        Intent openPrenota = new Intent(getActivity(),PrenotaActivity.class);
        startActivity(openPrenota);
    }
}
