package com.example.menuemdicel.ui.perfil;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.menuemdicel.R;

public class perfilFragment extends Fragment implements View.OnClickListener {
    EditText usuario,contraseña;
    Button actualizar;


    private PerfilViewModel mViewModel;

    public static perfilFragment newInstance() {
        return new perfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.perfil_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {

    }
}
