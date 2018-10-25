package com.example.janusdeb.loginapplication;

/**
 * Created by janusdeb on 24/10/18.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterUsuarios extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Usuarios> items;

    public AdapterUsuarios(Activity activity, ArrayList<Usuarios> items) {
        this.activity = activity;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Usuarios> category) {
        for (int i =0 ; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item, null);
        }

        Usuarios Item=items.get(position);

        ImageView imgFoto=(ImageView) convertView.findViewById(R.id.imgPhoto);
        TextView user = (TextView) convertView.findViewById(R.id.User);
        TextView nombre = (TextView) convertView.findViewById(R.id.nombre);
        TextView apellido = (TextView) convertView.findViewById(R.id.apellido);
        TextView email = (TextView) convertView.findViewById(R.id.Email);
        TextView celular = (TextView) convertView.findViewById(R.id.celular);
        TextView fecha = (TextView) convertView.findViewById(R.id.fechaN);
        TextView genero = (TextView) convertView.findViewById(R.id.Genero);
        TextView beca = (TextView) convertView.findViewById(R.id.Becado);

        return v;
    }
}