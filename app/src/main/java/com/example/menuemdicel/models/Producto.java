package com.example.menuemdicel.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.menuemdicel.helpers.QueueUtils;
import com.example.menuemdicel.ui.gallery.GalleryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Producto {
    public int id;
    public String phone;
    public String nickname;
    public String urlImage;
    public String precio;


    public Producto(int _id, String _phone, String _nickname ,String _precio,String _urlImage) {
        this.id = _id;
        this.phone = _phone;
        this.nickname = _nickname;
        this.precio = _precio;
        this.urlImage = _urlImage;
    }
    public static ArrayList getCollection() {
        ArrayList<Producto> collection = new ArrayList<>();
        collection.add(new Producto(0, "981999923", "Bichito","$ ###,###,###.00", ""));
        collection.add(new Producto(0, "9859913923", "Plaga","$ ###,###,###.00", ""));
        collection.add(new Producto(0, "981914213", "Libelula","$ ###,###,###.00", ""));
        return collection;
    }
    /////////////////////////////////////////////////////////////////////////////////
    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Producto> contactos,
                                               final GalleryFragment _interface) {
        String url = "https://cbf679da2167.ngrok.io/api/auth/productos";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("objects")) {

                            try {
                                JSONArray list = response.getJSONArray("objects");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    contactos.add(new Producto(o.getInt("id"),o.getString("nombre"),
                                            o.getString("descripcion"), o.getString("precio"),o.getString("imagen")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList(); // Esta función debemos implementarla
                            // en nuestro GalleryFragment
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }
}
