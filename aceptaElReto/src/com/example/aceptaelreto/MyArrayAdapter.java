package com.example.aceptaelreto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;





import java.util.ArrayList;
import java.util.List;

import com.example.aceptaelreto.R;

public class MyArrayAdapter<String> extends ArrayAdapter<String> {
	
	Context ct;
	ArrayList<String> lista;

    public MyArrayAdapter(Context context, ArrayList<String> objects) {
        super(context, 0, objects);
        lista = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Comprobando si el View no existe
        if (convertView == null) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        //Obteniendo instancias de los text views
        TextView titulo = (TextView)convertView.findViewById(R.id.text1);
        ImageView img = (ImageView)convertView.findViewById(R.id.imglist);
        //Obteniendo instancia de la Tarea en la posición actual

        titulo.setText((CharSequence) lista.get(position));
        img.setImageResource(R.drawable.arrow);
        //Devolver al ListView la fila creada
        return convertView;

    }
}