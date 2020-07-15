package com.example.menuemdicel.ui.Carrito;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.menuemdicel.R;

public class CarritoFragment extends Fragment {

    private CarritoViewModel mViewModel;

    public static CarritoFragment newInstance() {
        return new CarritoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.carrito_fragment, container, false);
        //btngallery = root.findViewById(R.id.btngallery);
        // btngallery.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //  public void onClick(View v) {
        //     Toast.makeText(GalleryFragment.this.getActivity(),":D", Toast.LENGTH_LONG).show();
        //}
        //});
        return root;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CarritoViewModel.class);
        // TODO: Use the ViewModel
        int images[] = {R.drawable.descarga,R.drawable.descarga2,R.drawable.descarga3,R.drawable.descarga4};

    }

    }
