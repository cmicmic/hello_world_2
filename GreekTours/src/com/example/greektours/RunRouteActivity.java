package com.example.greektours;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.objects.InterestPoint;
import com.example.objects.Route;
import gr.charismichelakis.kpe_vamou.R;

public class RunRouteActivity extends FragmentActivity {

	public static final String ARG_ITEM_ID = "ip_list_name";
	public static final String ARG_RUN_MODE = "run_mode";
	private String currentIPName;
	private Route currentRoute;
	private String currentRouteName;
	private int currentRoutePos=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run_route);
		// Show the Up button in the action bar.
	    final ActionBar actionBar = getActionBar();

	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	    actionBar.setDisplayShowTitleEnabled(false);

	    //* get data from shared pref
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.filename_settings),MODE_PRIVATE); 
		//* get sharedpref info  if there is a route paused
		String currentRouteNamePref = sharedPref.getString(getString(R.string.shared_pref_route_name), null);
		String currentIPNamePref = sharedPref.getString(getString(R.string.shared_pref_ip_name), null);
		int currentRoutePosPref = sharedPref.getInt(getString(R.string.shared_pref_route_pos), 0);

		//* get Intent  whether this activity came from resume command or start new or continue
		String runMode = getIntent().getStringExtra(ARG_RUN_MODE);
		//* get Intent route name
		String currentRouteNameIntent = getIntent().getStringExtra(ARG_ITEM_ID);
		
		//* fetch current position 
		if (savedInstanceState == null) {	
			if (runMode.equals("Start New Route")){//* activity has been called from start route point
				//* if shared prefs ask if user wants to resume or start new
				currentRouteName = currentRouteNameIntent;
				currentRoute = new Route(this,currentRouteName);
				currentIPName = currentRoute.getRouteIPNames().get(0);
			}else{ 
				if (runMode.equals("Next IP")) {//* activity has been called from next
					currentRouteName = currentRouteNameIntent;
					currentIPName = currentRouteNameIntent; //get next
				}else{ //* activity has been called from resume or select route by distance
					//currentRoute = new Route(this,currentRouteNameIntent);
					currentIPName=currentIPNamePref;
					currentRouteName = currentRouteNamePref;
					currentRoutePos = currentRoutePosPref;
				}
			}
		}else{
			currentIPName = currentIPNamePref;
			currentRouteName = currentRouteNamePref;	
			currentRoutePos = currentRoutePosPref;
		}
		
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","-----------***-----------");
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","currentRouteNamePref: "+ currentRouteNamePref);
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","currentRoutePosPref: "+Integer.toString(currentRoutePosPref));
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","currentIPNamePref: "+ currentIPNamePref);
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","----------------------");

if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","currentRouteNameIntent: "+ currentRouteNameIntent);
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","runMode: "+runMode);
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","currentRouteNameIntent: "+ currentRouteNameIntent);
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","----------------------");

if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","currentRouteName: "+ currentRouteName);
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","routePos: "+Integer.toString(currentRoutePos));
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","currentIPName: "+ currentIPName);
if (MyDebug.LOG) Log.i("RunRouteActivity/onCreate","-----------=====-----------");

		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(InterestPointDetailFragment.ARG_ITEM_ID,currentIPName);
			InterestPointDetailFragment fragment = new InterestPointDetailFragment();
			fragment.setArguments(arguments);
			
        	//Save preference GameMode in shared preferences 
        	sharedPref = this.getSharedPreferences(getString(R.string.filename_settings),Context.MODE_PRIVATE);
        	SharedPreferences.Editor editor = sharedPref.edit();
        	editor.putString(getString(R.string.shared_pref_route_name), currentRouteName);
        	editor.putString(getString(R.string.shared_pref_ip_name), currentIPName);
        	editor.putInt(getString(R.string.shared_pref_route_pos), currentRoutePos);
        	editor.commit();

			getSupportFragmentManager().beginTransaction()
					.add(R.id.interestpoint_detail_container, fragment)
					.commit();
		}
	}

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this,
					RouteListActivity.class));
			return true;
		case R.id.action_next_ip:
			currentRoute = new Route(this,currentRouteName);
			if (currentRoute.getRouteIPNames().size()>currentRoutePos+1)
				currentRoutePos++;
			else
				if (currentRoute.getRouteIPNames().size()==currentRoutePos+1)
					currentRoutePos=0;
			
			showIPDetailFragment();
			break;
		case R.id.action_previous_ip:
			currentRoute = new Route(this,currentRouteName);
			if (currentRoutePos>0)
				currentRoutePos--;
			else
				currentRoutePos=currentRoute.getRouteIPNames().size()-1;
			showIPDetailFragment();
			break;
		case R.id.action_wiki_ip:
			InterestPoint ip = new InterestPoint(this,currentIPName);
			String url = ip.getWikiUrl();
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i); 
			break;
		case R.id.action_map_ip:
			InterestPoint ip2 = new InterestPoint(this,currentIPName);
			ip2.getLocation().get(0);
			double latitude = ip2.getLocation().get(0);
			double longitude = 	ip2.getLocation().get(1);

			String label = currentIPName;
			String uriBegin = "geo:" + latitude + "," + longitude;
			String query = latitude + "," + longitude + "(" + label + ")";
			String encodedQuery = Uri.encode(query);
			String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
			Uri uri = Uri.parse(uriString);
			Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
			startActivity(intent);
//			InterestPoint ip2 = new InterestPoint(this,currentIPName);
//			String url2 = ip2.getGoogleMapLink();
//			Intent i2 = new Intent(Intent.ACTION_VIEW);
//			i2.setData(Uri.parse(url2));
//			startActivity(i2); 
			break;			
		}
//			Bundle arguments = new Bundle();
//			arguments = new Bundle();
//			currentIPName = currentRoute.getRouteIPNames().get(currentRoutePos);
//			
//if (MyDebug.LOG) Log.i("RunRouteActivity/onOptionsItemSelected","currentRoute.getRouteIPNames().size(): "+Integer.toString(currentRoute.getRouteIPNames().size()));
//if (MyDebug.LOG) Log.i("RunRouteActivity/onOptionsItemSelected","routePos: "+Integer.toString(currentRoutePos));
//if (MyDebug.LOG) Log.i("RunRouteActivity/onOptionsItemSelected",currentIPName);
//
//			arguments.putString(InterestPointDetailFragment.ARG_ITEM_ID,currentIPName);
//			InterestPointDetailFragment fragment = new InterestPointDetailFragment();
//			fragment.setArguments(arguments);
//
//        	//Save preference GameMode in shared preferences 
//			SharedPreferences sharedPref = getPreferences(MODE_PRIVATE); 
//        	sharedPref = this.getSharedPreferences(getString(R.string.filename_settings),Context.MODE_PRIVATE);
//        	SharedPreferences.Editor editor = sharedPref.edit();
//        	editor.putString(getString(R.string.shared_pref_route_name), currentRouteName);
//        	editor.putString(getString(R.string.shared_pref_ip_name), currentIPName);
//        	editor.putInt(getString(R.string.shared_pref_route_pos), currentRoutePos);
//
//        	editor.commit();
//        	
//			getSupportFragmentManager().beginTransaction().replace(R.id.interestpoint_detail_container, fragment).commit();

		return super.onOptionsItemSelected(item);
	}
	
	private void showIPDetailFragment(){
		Bundle arguments = new Bundle();
		arguments = new Bundle();
		currentIPName = currentRoute.getRouteIPNames().get(currentRoutePos);
		
if (MyDebug.LOG) Log.i("RunRouteActivity/onOptionsItemSelected","currentRoute.getRouteIPNames().size(): "+Integer.toString(currentRoute.getRouteIPNames().size()));
if (MyDebug.LOG) Log.i("RunRouteActivity/onOptionsItemSelected","routePos: "+Integer.toString(currentRoutePos));
if (MyDebug.LOG) Log.i("RunRouteActivity/onOptionsItemSelected",currentIPName);

		arguments.putString(InterestPointDetailFragment.ARG_ITEM_ID,currentIPName);
		InterestPointDetailFragment fragment = new InterestPointDetailFragment();
		fragment.setArguments(arguments);

    	//Save preference GameMode in shared preferences 
		SharedPreferences sharedPref = getPreferences(MODE_PRIVATE); 
    	sharedPref = this.getSharedPreferences(getString(R.string.filename_settings),Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	editor.putString(getString(R.string.shared_pref_route_name), currentRouteName);
    	editor.putString(getString(R.string.shared_pref_ip_name), currentIPName);
    	editor.putInt(getString(R.string.shared_pref_route_pos), currentRoutePos);

    	editor.commit();
    	
		getSupportFragmentManager().beginTransaction().replace(R.id.interestpoint_detail_container, fragment).commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.run_route, menu);
		return true;
	}

}
