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

public class ProfiloFragment extends Fragment implements View.OnClickListener {

    View v;
    Button logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profilo, null);
        logout = (Button) v.findViewById(R.id.button_logout);
        logout.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Intent openMain = new Intent(getActivity(),LoginActivity.class); //Definisce l'intenzione di aprire il mainActivity
        startActivity(openMain); //Apre il main
    }
}
