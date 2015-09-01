package com.example.aceptaelreto;

import Tools.TouchImageView;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

public class Enunc_Fragment extends Fragment{

	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private TouchImageView img;
    private static String urlImage;
	
	public static Enunc_Fragment newInstance(int sectionNumber, String tk, String url) {
		Enunc_Fragment fragment = new Enunc_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        urlImage = url;
        return fragment;
    }
    
    public Enunc_Fragment() {
    	 
    }
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.enunc_layout, container, false);
		
		img = (TouchImageView)rootView.findViewById(R.id.avatar);
		
		Picasso.with(getActivity()).load(urlImage).resize(1246,3340).into(img);	
		
        return rootView;
    }
	
	 @Override
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	                ARG_SECTION_NUMBER));
	 }
	 
}
