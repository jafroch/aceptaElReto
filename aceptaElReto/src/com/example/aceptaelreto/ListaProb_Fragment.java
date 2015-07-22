package com.example.aceptaelreto;

import ws.CallerWS;
import ws.WSquery;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.app.ListFragment;

public class ListaProb_Fragment extends ListFragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private CallerWS ws;
	private WSquery path;
	private Spinner sp1, sp2, sp3, sp4;
	
	public static Inicio_Fragment newInstance(int sectionNumber) {
        Inicio_Fragment Listfragment = new Inicio_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        Listfragment.setArguments(args);
        return Listfragment;
    }
	
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getProblems();
    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2" };
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    // TODO implement some logic 
  }
  
  public void getProblems(){
	  this.ws= new CallerWS();
      path = this.ws.getPath();
  }
} 