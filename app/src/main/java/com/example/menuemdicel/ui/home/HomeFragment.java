package com.example.menuemdicel.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.menuemdicel.R;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //////////////////////////////////////////////////////////
        ///////////////////spinner combo box//////////////////////
        /////////////////////////////////////////////////////////
        String [] categoriasList =  new String[] {
            "Todas las Categorias","Gama Alta", "Gama Media", "Gama Baja","Accesorios"
        };
        Spinner spinner = root.findViewById(R.id.categorias);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, categoriasList);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);
        ////////////// para que me salga un mensaje que e sellecionado del combo box//////////////
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Seleccionado:" + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////
        //final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
          //  @Override
           // public void onChanged(@Nullable String s) {
            //    textView.setText(s);
            //}
        //});
        ////////////////////////////////////////////////
        PusherOptions options = new PusherOptions();
        options.setCluster("us2");

        Pusher pusher = new Pusher("181382b656db92642bb4", options);

        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                HomeFragment.this.getActivity().runOnUiThread(new Runnable(){
                    public void run() {
                Toast.makeText(HomeFragment.this.getContext(),
                        "Conexion",Toast.LENGTH_SHORT).show();
                    }
                });
                Log.i("Pusher", "State changed from " + change.getPreviousState() +
                        " to " + change.getCurrentState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                HomeFragment.this.getActivity().runOnUiThread(new Runnable(){
                    public void run() {
                Toast.makeText(HomeFragment.this.getContext(),
                        "Error",Toast.LENGTH_SHORT).show();
                    }
                });
                Log.i("Pusher", "There was a problem connecting! " +
                        "\ncode: " + code +
                        "\nmessage: " + message +
                        "\nException: " + e
                );
            }
        }, ConnectionState.ALL);

        Channel channel = pusher.subscribe("my-channel");

        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent( final PusherEvent event) {
                HomeFragment.this.getActivity().runOnUiThread(new Runnable(){
                    public void run() {
                Toast.makeText(HomeFragment.this.getContext(),
                        "Received",Toast.LENGTH_SHORT).show();
            }
        });
                Log.i("Pusher", "Received event with data: " + event.toString());
            }
        });
        ///////////////////////////////////////////////////////////////
        return root;
    }
}
