package com.example.greektours;

import gr.charismichelakis.kpe_vamou.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

/**
 * An activity representing a list of Routes. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link RouteDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link RouteListFragment} and the item details (if present) is a
 * {@link RouteDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link RouteListFragment.Callbacks} interface to listen for item selections.
 */
public class RouteListActivity extends FragmentActivity implements
		RouteListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	private String currentRouteName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_list);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);

		if (findViewById(R.id.route_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((RouteListFragment) getSupportFragmentManager().findFragmentById(
					R.id.route_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_start_route:
			Intent detailIntent = new Intent(this,RunRouteActivity.class);
			//detailIntent.putExtra(RunRouteActivity.ARG_ITEM_ID, getIntent().getStringExtra(RouteDetailFragment.ARG_ITEM_ID)); //need to pass route id and runrouteactivity will return point
			detailIntent.putExtra(RunRouteActivity.ARG_ITEM_ID, "Διαδρομή 1"); //need to pass route id and runrouteactivity will return point
			detailIntent.putExtra(RunRouteActivity.ARG_RUN_MODE, "Start New Route");
			startActivity(detailIntent);
			return true;
		case R.id.action_map_ip:
//			Route route2 = new Route(this,currentRouteName);
//			String url2 = route2.getGoogleMapLink();
//			Intent i2 = new Intent(Intent.ACTION_VIEW);
//			i2.setData(Uri.parse(url2));
//			startActivity(i2); 
			Intent detailIntent2 = new Intent(this,RunClosestActivity.class);
			detailIntent2.putExtra(RunClosestActivity.ARG_ITEM_ID,currentRouteName);
			startActivity(detailIntent2);

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Callback method from {@link RouteListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			currentRouteName=id;
			Bundle arguments = new Bundle();
			arguments.putString(RouteDetailFragment.ARG_ITEM_ID, id);
			RouteDetailFragment fragment = new RouteDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.route_detail_container, fragment).commit();
		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, RouteDetailActivity.class);
			detailIntent.putExtra(RouteDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		if (mTwoPane) {
			menu.add(0, R.id.action_map_ip, 0, getString(R.id.action_map_ip)).setIcon(R.drawable.ic_action_map).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			menu.add(0, R.id.action_start_route, 0, getString(R.id.action_start_route)).setIcon(R.drawable.ic_action_play_over_video).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
