package com.example.greektours;

import gr.charismichelakis.kpe_vamou.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.objects.InterestPoint;
import com.example.objects.Route;

public class RunClosestActivity extends Activity {

	private Route route = new Route("Διαδρομή 1");
	public static final String ARG_ITEM_ID = "route_name";
	String selectedRouteName;
	private Location location=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_run_closest);
		setContentView(R.layout.activity_ip_radio_group);
		
	    final ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	    actionBar.setDisplayShowTitleEnabled(false);
	    
		selectedRouteName = getIntent().getStringExtra(ARG_ITEM_ID);
		route = new Route(selectedRouteName);

		createRadioGroup();
        
//		TextView t=(TextView)findViewById(R.id.text_closest);
//		
//	    // Get the location manager
//	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//	    Criteria criteria = new Criteria();
//	    provider = locationManager.getBestProvider(criteria, false);
//	    Location location = locationManager.getLastKnownLocation(provider);
//
	}

	private void createRadioGroup(){
		List<String> sortedIpNamesList = new ArrayList<String>();
    	// get reference to radio group in layout
    	RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radiogroup);

    	// layout params to use when adding each radio button
        LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        // add 20 radio buttons to the group
        sortedIpNamesList=getIpList();
        
        
        for (int i = 0; i < sortedIpNamesList.size(); i++){
            RadioButton newRadioButton = new RadioButton(this);
            String label = sortedIpNamesList.get(i);
            newRadioButton.setText(label);
            newRadioButton.setId(i);
            if (i==0) newRadioButton.setChecked(true);
            radiogroup.addView(newRadioButton, layoutParams);
        }
	}
	
	private List<String> getIpList(){
		 LocationManager locationManager;
		 String currentProvider;
		 String bestProvider;
		 String providerSuggestion;
		 List<InterestPoint> sortedIpList = new ArrayList<InterestPoint>();
		 List<String> ipNamesList = new ArrayList<String>();
		 List<String> sortedIpNamesList = new ArrayList<String>();
		 InterestPoint ip;
		 float distance;
		 
	    // Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	    Criteria criteria = new Criteria();
	    //get best possible provider
	    bestProvider= locationManager.getBestProvider(criteria, false);
	    //get current provider
	    currentProvider = locationManager.getBestProvider(criteria, true);
	    if (currentProvider!=null)
	    	location = locationManager.getLastKnownLocation(currentProvider);
	    
//	    Location location = locationManager.getLastLocation();
	    // Initialize the location fields
	    if (location != null) {
	    	Location locationTo=new Location("fromHardcodedData");
	    	if (currentProvider.equals(bestProvider)){ //*if the best possible provider is used  
	    		providerSuggestion="";
	    	}else{
	    		providerSuggestion="Not the best location provider is being used";
	    	}
	    	TextView textview = (TextView) findViewById(R.id.textview_header);
	    	textview.setText(providerSuggestion);
	    	//* 
	    	ipNamesList=route.getRouteIPNames();
	    	
	    	for (int i=0;i<ipNamesList.size();i++){
		    	ip= new InterestPoint(this, ipNamesList.get(i));
		    	locationTo.setLatitude(ip.getLocation().get(0));
		    	locationTo.setLongitude(ip.getLocation().get(1));	
		    	distance = location.distanceTo(locationTo);
		    	ip.setDistance(distance);
		    	sortedIpList.add(ip);
	    	}
    		Collections.sort(sortedIpList);

	    	for (int i=0;i<sortedIpList.size();i++){
		    	sortedIpNamesList.add(sortedIpList.get(i).getName()+ " ("+Float.toString(sortedIpList.get(i).getDistance())+"m)");
	    	}
	    	
	    } else {
	    	if (currentProvider!=null) // provider is alive but has not provide a location
	    		providerSuggestion="No location data provided yet. Please wait for a while and try again.";
	    	else //* no provider active
	    		providerSuggestion="No location provider is active. Please activate...";    
	    	//btn1
	    	TextView textview = (TextView) findViewById(R.id.textview_header);
	    	textview.setText(providerSuggestion);
	    }
	    return sortedIpNamesList;
	}
	
	public void showRouteToIp(View view){
		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        // get selected radio button from radioGroup
		int selectedId = radiogroup.getCheckedRadioButtonId();

		// find the radiobutton by returned id
		RadioButton radioButton = (RadioButton) findViewById(selectedId);
		
		String selectedIpName = parseIpNameFromRadio((String) radioButton.getText());
		InterestPoint selectedIp= new InterestPoint(this, selectedIpName);
if (MyDebug.LOG) Log.i("tag", "selectedIpName: " + selectedIpName);	  

		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
			    Uri.parse("http://maps.google.com/maps?saddr="
			    		+ Double.toString(location.getLatitude()) +"," + Double.toString(location.getLongitude())
			    		+ "&daddr="
			    		+ Double.toString(selectedIp.getLocation().get(0))+"," + Double.toString(selectedIp.getLocation().get(1))));
		startActivity(intent);
		
	}
	
	public void runRouteFromIp(View view){
		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        // get selected radio button from radioGroup
		int selectedId = radiogroup.getCheckedRadioButtonId();
		// find the radiobutton by returned id
		RadioButton radioButton = (RadioButton) findViewById(selectedId);

		
		String selectedIpName = parseIpNameFromRadio((String) radioButton.getText());

		SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.filename_settings),Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	editor.putString(getString(R.string.shared_pref_route_name), selectedRouteName);
    	editor.putString(getString(R.string.shared_pref_ip_name), selectedIpName);
    	editor.putInt(getString(R.string.shared_pref_route_pos), route.getIpPos(selectedIpName));
    	editor.commit();
    	
       	Intent intent = new Intent(this, RunRouteActivity.class);
       	intent.putExtra(RunRouteActivity.ARG_RUN_MODE, "resume");
        startActivity(intent);    	

    	
if (MyDebug.LOG) Log.i("tag", "radio btn id: " + radiogroup.getCheckedRadioButtonId());	  
	}
	
	
	
	private String parseIpNameFromRadio(String radioText){
		return radioText.substring(0, radioText.indexOf("(")-1);
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
			//NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.run_closest, menu);
		return true;
	}

}
