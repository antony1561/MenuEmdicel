package com.example.menuemdicel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.menuemdicel.R;
import com.example.menuemdicel.models.Producto;

import java.util.List;

public class ProductoAdaptador extends ArrayAdapter<Producto> {
    Context context;
    ImageLoader queue;
    private class ViewHolder {
        TextView phone;
        TextView nickname;
        TextView precio;
        NetworkImageView image;


        private ViewHolder() {
        }
    }
    public ProductoAdaptador(Context context, List<Producto> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue = _queue;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Producto rowItem = (Producto) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_producto, null);
            holder = new ViewHolder();
            holder.phone = (TextView) convertView.findViewById(R.id.phone);
            holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
            holder.precio = (TextView) convertView.findViewById(R.id.precio);
            holder.image = (NetworkImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.precio.setText(rowItem.precio);
        holder.phone.setText(rowItem.phone);
        holder.nickname.setText(rowItem.nickname);
        holder.image.setImageUrl(rowItem.urlImage, this.queue);
        return convertView;
    }
}
