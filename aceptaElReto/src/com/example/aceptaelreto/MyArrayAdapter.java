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
	ArrayList<String> listaName;
	ArrayList<Integer> listaId;
	int opc;

    public MyArrayAdapter(Context context, ArrayList<String> obj1, ArrayList<Integer> obj2, int opc) {
        super(context, 0, obj1);
        listaName = obj1;
        listaId = obj2;
        this.opc = opc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        switch (opc) {
        
        case 0:
        	//Comprobando si el View no existe
        	if (convertView == null) {
        		//Si no existe, entonces inflarlo con two_line_list_item.xml
        		convertView = inflater.inflate(R.layout.list_item, parent, false);
        	}

        	//Obteniendo instancias de los text views
        	TextView titulo = (TextView)convertView.findViewById(R.id.text1);
        	ImageView img = (ImageView)convertView.findViewById(R.id.imglist);
        	//Obteniendo instancia de la Tarea en la posición actual

        	titulo.setText((CharSequence) listaName.get(position));
        	img.setImageResource(R.drawable.arrow);
        	break;
        case 1:	
        	//Comprobando si el View no existe
        	if (convertView == null) {
        		//Si no existe, entonces inflarlo con two_line_list_item.xml
        		convertView = inflater.inflate(R.layout.envios_box, parent, false);
        	}

        	//Obteniendo instancias de los text views
        	TextView probId = (TextView)convertView.findViewById(R.id.box_prob_id);
        	TextView probname = (TextView)convertView.findViewById(R.id.box_leng);
        	//Obteniendo instancia de la Tarea en la posición actual

        	probname.setText((CharSequence) listaName.get(position));
        	probId.setText((CharSequence) Integer.toString(listaId.get(position)));
        	break;
        case 2:
        	/*if (convertView == null) {
        		convertView = inflater.inflate(R.layout.envios_box, parent, false);
        	}

        	
        	TextView probId = (TextView)convertView.findViewById(R.id.box_prob_id);
        	TextView probname = (TextView)convertView.findViewById(R.id.box_leng);

        	probname.setText((CharSequence) listaName.get(position));
        	probId.setText((CharSequence) Integer.toString(listaId.get(position)));*/
        	break;
        }
        return convertView;

    }
}