package com.example.greektours;

//import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objects.InterestPoint;
import gr.charismichelakis.kpe_vamou.R;

/**
 * A fragment representing a single Interest Point detail screen. This fragment
 * is either contained in a {@link InterestPointListActivity} in two-pane mode
 * (on tablets) or a {@link InterestPointDetailActivity} on handsets.
 */
public class InterestPointDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	//private DummyContent.DummyItem mItem;
	//private InterestPoint ipItem;
	String mTitle;
	String mBody;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public InterestPointDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			mTitle = getArguments().getString(ARG_ITEM_ID);
			mBody = getArguments().getString(ARG_ITEM_ID);
		}

	}
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
		// Prepare the loader.  Either re-connect with an existing one,
		// or start a new one.
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_interestpoint_detail, container, false);
		ImageView img=(ImageView) rootView.findViewById(R.id.imageView1);
		img.setOnClickListener(new View.OnClickListener() { 
			@Override public void onClick(View v) { 
if (MyDebug.LOG)Log.i("img.setOnClickListener", " click..."); 
			
			} 
			});

		// Show the dummy content as text in a TextView.
		if (mTitle != null) {
				((TextView) rootView.findViewById(R.id.interestpoint_detail)).setText(mTitle);
				InterestPoint interestPoint = new InterestPoint(getActivity(), mTitle);
				int imageResourceId = interestPoint.getImageResourceId();
				
				((ImageView) rootView.findViewById(R.id.imageView1)).setImageResource(imageResourceId);
				((TextView) rootView.findViewById(R.id.interestpoint_detail_text))
				.setText(interestPoint.getBody());			
				((TextView) rootView.findViewById(R.id.interestpoint_detail_text))
				.setTextSize(getTextViewTextSize(((GreekTours) this.getActivity().getApplication()).getSelectedTextSize()));       	
		}
		return rootView;
	}
	
	public float getTextViewTextSize(int textSize){
		float size=20;
		switch (textSize) {
		case 0:
			size=10;
			break;
		case 1:
			size=20;
			break;
		case 2:		
			size=30;
			break;
		case 3:	
			size=40;
			break;
		}
		
		return size;
	}





	@Override
	public void onLoadFinished(android.support.v4.content.Loader<Cursor> arg0,
			Cursor arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(android.support.v4.content.Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public android.support.v4.content.Loader<Cursor> onCreateLoader(int arg0,
			Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
