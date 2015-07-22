package com.example.aceptaelreto;

import java.util.ArrayList;

import ws.CallerWS;
import ws.WSquery;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class ProblemsActivity extends Fragment {
 
  private static final String ARG_SECTION_NUMBER = "section_number";
  String[] groupParent = null;
  ArrayList<Object> groupChildren = new ArrayList<Object>();
  
  public static ProblemsActivity newInstance(int sectionNumber) {
	  ProblemsActivity fragment = new ProblemsActivity();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
  }
  
  public ProblemsActivity() {
  	 
  }
  
  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

	View rootView = inflater.inflate(R.layout.activity_problems, container, false);
    createData(0);
    ExpandableListView listView = (ExpandableListView)rootView.findViewById(R.id.listView);
    //MyExpandableListAdapter adapter = new MyExpandableListAdapter(getActivity(),groupParent,groupChildren);
    //listView.setAdapter(adapter);
    return rootView;
  }

  
  public void createData(int pos) {
	
	 Resources res = getActivity().getApplicationContext().getResources();
	 String[] aux;
	 groupParent = null;
	 switch (pos){
	 	case 0:
	 		groupParent = res.getStringArray(R.array.list_prob);
	 		aux = res.getStringArray(R.array.list_prob1);
	 		groupChildren.add(aux);
	 		aux = res.getStringArray(R.array.list_prob2);
	 		groupChildren.add(aux);
	 		break;
	 	case 21:
	 		groupParent[0] = "Programación";
	 		aux = res.getStringArray(R.array.list_prob21);
	 		groupChildren.add(aux);
	 		break;
	 }

 
  }

  
} 