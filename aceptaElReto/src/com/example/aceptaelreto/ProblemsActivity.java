package com.example.aceptaelreto;

import ws.Traductor;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;



public class ProblemsActivity extends ActionBarActivity implements
NavigationDrawerFragment.NavigationDrawerCallbacks {
 
	
	private NavigationDrawerFragment mNavigationDrawerFragment;
	//ATRIBUTOS PARA MANEJO DEL MENU.
	CharSequence mTitle;
	String[] opcionesMenu;
	DrawerLayout drawerLayout;
	ListView drawerList;
	GridView tablaPerfil;
	public static String Token;
	public static String myId;
	Bundle args;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));
		
		Intent myIntent = getIntent(); 
		Token = (String) myIntent.getExtras().get("LoginResponse");
		args = new Bundle();
		args.putString("TOKEN",Token);		
		
		getSupportFragmentManager().beginTransaction().replace(R.id.container,ProbListFragment.newInstance(1,Token)).commit();
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		  // update the main content by replacing fragments
		 
		  FragmentManager fragmentManager = getSupportFragmentManager();
		  
		  switch (position+1) {
		    case 1:
		      fragmentManager.beginTransaction().replace(R.id.container,
		      Inicio_Fragment.newInstance(position + 1,Token)).commit();
		      break;
		    case 2:
		      fragmentManager.beginTransaction().replace(R.id.container,
		   	  Perfil_Fragment.newInstance(position + 1,Token,0)).commit();
		   	  break;
		    case 3:
		      fragmentManager.beginTransaction().replace(R.id.container,
		      Inicio_Fragment.newInstance(position + 1,Token)).commit();
		      break;
		    case 4:
			  fragmentManager.beginTransaction().replace(R.id.container,
			  Enviar_Fragment.newInstance(position + 1,Token)).commit();
			  break;
		    case 5:
			  fragmentManager.beginTransaction().replace(R.id.container,
			  ProbListFragment.newInstance(position + 1,Token)).commit();
			  break;
		    case 6:
			  fragmentManager.beginTransaction().replace(R.id.container,
			  ProbListFragment.newInstance(position + 1,Token)).commit();
			  break;
		    case 7:
		      fragmentManager.beginTransaction().replace(R.id.container,
		      ProbListFragment.newInstance(position + 1,Token)).commit();
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
	
  
} 