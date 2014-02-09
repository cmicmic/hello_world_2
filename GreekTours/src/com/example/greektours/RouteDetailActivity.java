package com.example.greektours;

import android.app.ActionBar;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import gr.charismichelakis.kpe_vamou.R;

/**
 * An activity representing a single Route detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link RouteListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link Route+DetailFragment}.
 */
public class RouteDetailActivity extends FragmentActivity {

	String currentRouteName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_detail);

		// Show the Up button in the action bar.
	    final ActionBar actionBar = getActionBar();

	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	    actionBar.setDisplayShowTitleEnabled(false);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			currentRouteName=getIntent().getStringExtra(RouteDetailFragment.ARG_ITEM_ID);
			Bundle arguments = new Bundle();
			arguments.putString(RouteDetailFragment.ARG_ITEM_ID, currentRouteName);

			RouteDetailFragment fragment = new RouteDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.route_detail_container, fragment)
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
		case R.id.action_start_route:
			Intent detailIntent = new Intent(this,RunRouteActivity.class);
			detailIntent.putExtra(RunRouteActivity.ARG_ITEM_ID, getIntent().getStringExtra(RouteDetailFragment.ARG_ITEM_ID)); //need to pass route id and runrouteactivity will return point
			detailIntent.putExtra(RunRouteActivity.ARG_RUN_MODE, "Start New Route");
			startActivity(detailIntent);
			return true;
		case R.id.action_map_ip:
//			Route route2 = new Route(this,currentRouteName);
//			String url2 = route2.getGoogleMapLink();
//			Intent i2 = new Intent(Intent.ACTION_VIEW);
//			i2.setData(Uri.parse(url2));
//			startActivity(i2); 
			////////////////////////
			Intent detailIntent2 = new Intent(this,RunClosestActivity.class);
			detailIntent2.putExtra(RunClosestActivity.ARG_ITEM_ID,currentRouteName);
			startActivity(detailIntent2);

			
			////////////////////////
//			LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
//			boolean enabled = service
//			  .isProviderEnabled(LocationManager.GPS_PROVIDER);
//			if (!enabled) {
//				  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//				  startActivity(intent);
//				}else{
//					
//				}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.route_detail, menu);
		
		return true;
	}
}
