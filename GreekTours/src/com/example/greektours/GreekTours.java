package com.example.greektours;
//testing git
//test 3
import gr.charismichelakis.kpe_vamou.R;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.objects.InterestPoint;

public class GreekTours extends Application {
	private static GreekTours mInstance;
	private List<InterestPoint> allIPList= new  ArrayList<InterestPoint>();
	private List<String> allRouteNames = new ArrayList<String>();
	
	public void onCreate(){
		mInstance = this;
		allRouteNames.add("Διαδρομή 1");
		allRouteNames.add("Διαδρομή 2");
		allRouteNames.add("Διαδρομή 3");
		this.allIPList.add(new InterestPoint(this, "Το Ενετικό Λιμάνι"));
		this.allIPList.add(new InterestPoint(this, "Παραλία Φαλάσσαρνα"));
		this.allIPList.add(new InterestPoint(this, "Φραγκοκάστελλο"));
		this.allIPList.add(new InterestPoint(this, "Σφακιά"));
		this.allIPList.add(new InterestPoint(this, "Κοντομαρί"));
	}
	
	public List<String> getAllIPNames(){
		List<String> allIPNames = new ArrayList<String>();
		for (int i=0;i<allIPList.size();i++)
			allIPNames.add(allIPList.get(i).getName());
		return allIPNames;
	}
	
	public List<String> getAllRouteNames(){
		return allRouteNames;
	}
	
	
	public void setSelectedTextSize(int size){
    	//Save preference GameMode in shared preferences 
    	SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.filename_settings),Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	editor.putInt(getString(R.string.shared_pref_text_size), size);
    	editor.commit();
	}
	
	public int getSelectedTextSize(){
		  SharedPreferences sharedPref = getSharedPreferences(getString(R.string.filename_settings),Context.MODE_PRIVATE);
		  int selectedTextSize = sharedPref.getInt(getString(R.string.shared_pref_text_size), 1);
		  return selectedTextSize;
	}
	
	public static GreekTours getAppInstance() { return mInstance; }
}
