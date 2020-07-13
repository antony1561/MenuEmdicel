package com.example.menuemdicel.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.toolbox.ImageLoader;
import com.example.menuemdicel.R;
import com.example.menuemdicel.adapters.ProductoAdaptador;
import com.example.menuemdicel.helpers.QueueUtils;
import com.example.menuemdicel.models.Producto;
import com.example.menuemdicel.ui.Producto.ProductoFragment;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
     Button btngallery;
     ListView lista;
     ProductoAdaptador productoadaptador;
     ArrayList<Producto> datos;
     QueueUtils.QueueObject encolador;
     ImageLoader encoladorImagenes;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        /////////funcion de boton que te lleva a otra pantalla////////////////////////////////
       //btngallery = root.findViewById(R.id.btngallery);
       // btngallery.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View v) {
           //     Toast.makeText(GalleryFragment.this.getActivity(),":D", Toast.LENGTH_LONG).show();
            //}
        //});
        /////////////////////////////////////////////////////////////////////////////////////
        //para que jale los datos de la nube y muestre en pantalla en el layout itemproducto////
        ///// si se pone antes del view root no lo identificaria////////////////////////////////
        encolador = QueueUtils.getInstance(this.getContext());
        encoladorImagenes = encolador.getImageLoader();
        datos = new ArrayList<>();
        Producto.injectContactsFromCloud(encolador,datos,this);
        productoadaptador = new ProductoAdaptador(this.getContext(), datos, encoladorImagenes);
        lista = root.findViewById(R.id.lista);
        lista.setAdapter(productoadaptador);
        //////////////////////////al seleccionar cualquier item nos llevara a nosotros fragment/////////
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view,int position, long id) {
               ProductoFragment o = new ProductoFragment();
               o.productoobj = datos.get(position);
                getFragmentManager().beginTransaction()
                       .replace(R.id.nav_host_fragment, o)
                       .addToBackStack(null).commit();
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////
      //  galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //    @Override
         //   public void onChanged(@Nullable String s) {
          //      textView.setText(s);
           // }
       // });
        return root;
    }

    public void refreshList(){
        if (productoadaptador != null) {
            productoadaptador.notifyDataSetChanged();
        }
    }
}
