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

public class EnvArrayAdapter<String> extends ArrayAdapter<Info_Envios> {

    public EnvArrayAdapter(Context context, List<Info_Envios> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            listItemView = inflater.inflate(R.layout.envios_box,parent,false);
        }

        //Obteniendo instancias de los elementos
        TextView numEnv = (TextView)listItemView.findViewById(R.id.box_num_envio);
        TextView user = (TextView)listItemView.findViewById(R.id.box_user);
        TextView probId = (TextView)listItemView.findViewById(R.id.box_prob_id);
        TextView leng = (TextView)listItemView.findViewById(R.id.box_leng);
        TextView res = (TextView)listItemView.findViewById(R.id.box_res);

        //Obteniendo instancia de la Info_Envios en la posición actual
        Info_Envios item = getItem(position);

        numEnv.setText(item.getNumEnvio());
        user.setText(item.getNameUser());
        probId.setText(item.getIdProb());
        leng.setText(item.getLeng());
        res.setText(item.getRes());


        //Devolver al ListView la fila creada
        return listItemView;

    }
}
