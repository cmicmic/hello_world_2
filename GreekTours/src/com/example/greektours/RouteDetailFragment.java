package com.example.greektours;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objects.Route;
import gr.charismichelakis.kpe_vamou.R;

/**
 * A fragment representing a single Route detail screen. This fragment is either
 * contained in a {@link RouteListActivity} in two-pane mode (on tablets) or a
 * {@link RouteDetailActivity} on handsets.
 */
public class RouteDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	//private DummyContent.DummyItem mItem;
	String mTitle;
	String mBody;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public RouteDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mTitle = getArguments().getString(ARG_ITEM_ID);
//			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
//					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_route_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mTitle != null) {
			((TextView) rootView.findViewById(R.id.route_detail))
				.setText(mTitle);
			Route route = new Route(getActivity(), mTitle);
			((ImageView) rootView.findViewById(R.id.imageView1)).setImageResource(route.getImageResourceId());
if(MyDebug.LOG) Log.i("RouteDetailFragment/onCreateView",Integer.toString(route.getImageResourceId()));			
			((TextView) rootView.findViewById(R.id.route_detail_text))
				.setText(route.getBody());			
		}

		return rootView;
	}
}
