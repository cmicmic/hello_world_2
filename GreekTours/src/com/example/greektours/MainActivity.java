package com.example.greektours;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import gr.charismichelakis.kpe_vamou.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);
		
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.filename_settings),MODE_PRIVATE); 
		String currentRouteNamePref = sharedPref.getString(getString(R.string.shared_pref_route_name), null);
		if (currentRouteNamePref==null){
			ImageButton btnResume = (ImageButton) findViewById(R.id.imageButton3);
			btnResume.setEnabled(false);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void displayPoints(View view) {
       	Intent intent = new Intent(this, InterestPointListActivity.class);
        startActivity(intent);    	
    }
    
    public void displayRoutes(View view) {
       	Intent intent = new Intent(this, RouteListActivity.class);
        startActivity(intent);    	
    }
    
    public void resumeRoute(View view) {
       	Intent intent = new Intent(this, RunRouteActivity.class);
       	intent.putExtra(RunRouteActivity.ARG_RUN_MODE, "resume");
        startActivity(intent);    	
    }
    
    public void settings(View view) {
       	Intent intent = new Intent(this, SettingsActivity.class);
//       	intent.putExtra(RunRouteActivity.ARG_RUN_MODE, "resume");
        startActivity(intent);    
}
	
}
