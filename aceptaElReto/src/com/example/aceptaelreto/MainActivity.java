package com.example.aceptaelreto;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	
	//ATRIBUTOS PARA MANEJO DEL MENU.
	 CharSequence mTitle;
	 String[] opcionesMenu;
     DrawerLayout drawerLayout;
     ListView drawerList;
     GridView tablaPerfil;
     public static String Token;
     public static String myId;
     Bundle args;
     
     //Problemas prueba
   //the images to display
     Integer[] imageIDs = {
     R.drawable.problem1,
     R.drawable.problem2,
     };
   
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));
		
		try {
			Intent myIntent = getIntent(); 
			String login = (String) myIntent.getExtras().get("LoginResponse");
			Traductor trad = new Traductor(login);
			this.Token= trad.getSession().token;
			args = new Bundle();
			args.putString("TOKEN",Token);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		  // update the main content by replacing fragments
		 
		  FragmentManager fragmentManager = getSupportFragmentManager();
		  switch (position+1) {
		    case 1:
		      fragmentManager.beginTransaction().replace(R.id.container,
		      Inicio_Fragment.newInstance(position + 1,Token)).addToBackStack(null).commit();
		      break;
		    case 2:
		      fragmentManager.beginTransaction().replace(R.id.container,
		   	  Perfil_Fragment.newInstance(position + 1,Token,0)).addToBackStack(null).commit();
		   	  break;
		    case 3:
		      fragmentManager.beginTransaction().replace(R.id.container,
		      Inicio_Fragment.newInstance(position + 1,Token)).addToBackStack(null).commit();
		      break;
		    case 4:
		      fragmentManager.beginTransaction().replace(R.id.container,
		  	  ProbListFragment.newInstance(position + 1,Token)).addToBackStack(null).commit();
			  break;
		    case 5:
			  fragmentManager.beginTransaction().replace(R.id.container,
			  ProbMenuFragment.newInstance(position + 1,Token)).addToBackStack(null).commit();
			  break;
		    case 6:
			  fragmentManager.beginTransaction().replace(R.id.container,
			  ProbListFragment.newInstance(position + 1,Token)).addToBackStack(null).commit();
			  break;
		    case 7:
		      fragmentManager.beginTransaction().replace(R.id.container,
		      Doc_Fragment.newInstance(position + 1,Token)).addToBackStack(null).commit();
			  break;
		  }
		  
		}

	public void onSectionAttached(int number) {
		switch (number) {
		
		case 1:
			mTitle = getString(R.string.title_section0);
			break;
		case 2:
			mTitle = getString(R.string.title_section1);
			break;
		case 3:
			mTitle = getString(R.string.title_section2);
			break;
		case 4:
			mTitle = getString(R.string.title_section3);
			break;
		case 5:
			mTitle = getString(R.string.title_section4);
			break;
		case 6:
			mTitle = getString(R.string.title_section5);
			break;
		case 7:
			mTitle = getString(R.string.title_section6);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
    public void onBackPressed() {
    	if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
            
        }
    }
	
}