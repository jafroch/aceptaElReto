package com.example.aceptaelreto;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
/*
 * clase que genera el fragment de 
 */
public class Layout1Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    /*
     * Argumentos para el manejo de la tabla.
     */
    TableLayout tabla;  
    TableLayout cabecera;  
    TableRow.LayoutParams layoutFila;  
    TableRow.LayoutParams layoutId;  
    TableRow.LayoutParams layoutTexto; 
    /*
     * Numero maximo de filas a mostar.
     */
    private int MAX_FILAS = 10;	
    
    Resources rs; 
    
    public static Layout1Fragment newInstance(int sectionNumber) {
        Layout1Fragment fragment = new Layout1Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public Layout1Fragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout1, container,
                false);
        //inicializamos los argumentos.
        rs = getActivity().getApplicationContext().getResources();  
        
        tabla = (TableLayout)rootView.findViewById(R.id.tabla);  
        cabecera = (TableLayout)rootView.findViewById(R.id.cabecera);  
        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);  
        layoutId = new TableRow.LayoutParams(70,TableRow.LayoutParams.WRAP_CONTENT);  
        layoutTexto = new TableRow.LayoutParams(160,TableRow.LayoutParams.WRAP_CONTENT); 
        //rellenamos
        agregarCabecera();  
        agregarFilasTabla();  
        return rootView;
    }
    
    
    
    /*
     * Metodo para agregar valortes a la cabecera de la tabla.
     */
    public void agregarCabecera(){  
        TableRow fila;  
        TextView txtId;  
        TextView txtNombre;  
     
        fila = new TableRow(getActivity().getApplicationContext());  
        fila.setLayoutParams(layoutFila);  
     
        txtId = new TextView(getActivity().getApplicationContext());  
        txtNombre = new TextView(getActivity().getApplicationContext());  
     
        txtId.setText(rs.getString(R.string.id));  
        txtId.setGravity(Gravity.CENTER_HORIZONTAL);  
        txtId.setTextAppearance(getActivity().getApplicationContext(),R.style.etiqueta);  
        txtId.setBackgroundResource(R.drawable.tabla_celda_cabecera);  
        txtId.setLayoutParams(layoutId);  
     
        txtNombre.setText(rs.getString(R.string.valor));  
        txtNombre.setGravity(Gravity.CENTER_HORIZONTAL);  
        txtNombre.setTextAppearance(getActivity().getApplicationContext(),R.style.etiqueta);  
        txtNombre.setBackgroundResource(R.drawable.tabla_celda_cabecera);  
        txtNombre.setLayoutParams(layoutTexto);  
     
        fila.addView(txtId);  
        fila.addView(txtNombre);  
        cabecera.addView(fila);  
       }
    
    /*
     * Metodo para agregar valores a las filas de la tabla.
     */
    public void agregarFilasTabla(){  
    	  
        TableRow fila;  
        TextView txtId;  
        TextView txtNombre;  
     
        for(int i = 0;i<MAX_FILAS;i++){  
            int posicion = i + 1;  
            fila = new TableRow(getActivity().getApplicationContext());  
            fila.setLayoutParams(layoutFila);  
     
            txtId = new TextView(getActivity().getApplicationContext());  
            txtNombre = new TextView(getActivity().getApplicationContext());  
     
            txtId.setText(String.valueOf(posicion));  
            txtId.setGravity(Gravity.CENTER_HORIZONTAL);  
            txtId.setTextAppearance(getActivity().getApplicationContext(),R.style.etiqueta);  
            txtId.setBackgroundResource(R.drawable.tabla_celda);  
            txtId.setLayoutParams(layoutId);  
     
            txtNombre.setText("Texto "+posicion);  
            txtNombre.setGravity(Gravity.CENTER_HORIZONTAL);  
            txtNombre.setTextAppearance(getActivity().getApplicationContext(),R.style.etiqueta);  
            txtNombre.setBackgroundResource(R.drawable.tabla_celda);  
            txtNombre.setLayoutParams(layoutTexto);  
     
            fila.addView(txtId);  
            fila.addView(txtNombre);  
     
            tabla.addView(fila);  
        }  
       }  
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
}