package com.example.greektours;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.objects.IPNamesData;

public class ByDistanceListAdapter extends BaseAdapter implements OnClickListener {
    /** A list containing some sample data to show. */
    private List<IPNamesData> dataList = new ArrayList<IPNamesData>(); 
    
    public ByDistanceListAdapter(LayoutInflater inflator){
        super();
//        this.inflator = inflator;
//        Route route = new Route("Διαδρομή 1");
//        
//        dataList = new ArrayList<IPNamesData>();
//        data
//        		route.getRouteIPNames();
//        dataList
    }
    
    @Override
    public int getCount() {
            return dataList.size();
    }

    @Override
    public Object getItem(int position) {
            return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
            return position;
    }
    
    public View getView(int position, View view, ViewGroup viewGroup) {
//
//        // We only create the view if its needed
//        if (view == null) {
//                view = inflator.inflate(R.layout.activity_ip_row, null);
//
//                // Set the click listener for the checkbox
//                view.findViewById(R.id.checkBox1).setOnClickListener(this);
//        }
//
//        String data = (String) getItem(position);
//
//        // Set the example text and the state of the checkbox
//        CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox1);
//        cb.setChecked(data.isSelected());
//        // We tag the data object to retrieve it on the click listener.
//        cb.setTag(data);
//
//        TextView tvIPName = (TextView) view.findViewById(R.id.textview_ip_name);
//        tvIPName.setText(data);
//        
//        TextView tvIPDistance = (TextView) view.findViewById(R.id.textview_ip_distance);
//        tvIPDistance.setText("200m");
//
        return view;
    }
    
    @Override
    /** Will be called when a checkbox has been clicked. */
    public void onClick(View view) {
    		
//            category.setSelected(((CheckBox) view).isChecked());
//            //Update global variable
//            String categoryClicked=category.getName();
//            LexiconApp.getAppInstance().updateSelectionOfCategory(categoryClicked,((CheckBox) view).isChecked());

          //Update shared preferences

            
    }

}
