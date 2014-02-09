package com.example.greektours;
//testing git
//test 3
//test 4 from windows
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
		allRouteNames.add("Ξ”ΞΉΞ±Ξ΄Ο�ΞΏΞΌΞ® 1");
		allRouteNames.add("Ξ”ΞΉΞ±Ξ΄Ο�ΞΏΞΌΞ® 2");
		allRouteNames.add("Ξ”ΞΉΞ±Ξ΄Ο�ΞΏΞΌΞ® 3");
		this.allIPList.add(new InterestPoint(this, "Ξ¤ΞΏ Ξ•Ξ½ΞµΟ„ΞΉΞΊΟ� Ξ›ΞΉΞΌΞ¬Ξ½ΞΉ"));
		this.allIPList.add(new InterestPoint(this, "Ξ Ξ±Ο�Ξ±Ξ»Ξ―Ξ± Ξ¦Ξ±Ξ»Ξ¬ΟƒΟƒΞ±Ο�Ξ½Ξ±"));
		this.allIPList.add(new InterestPoint(this, "Ξ¦Ο�Ξ±Ξ³ΞΊΞΏΞΊΞ¬ΟƒΟ„ΞµΞ»Ξ»ΞΏ"));
		this.allIPList.add(new InterestPoint(this, "Ξ£Ο†Ξ±ΞΊΞΉΞ¬"));
		this.allIPList.add(new InterestPoint(this, "Ξ�ΞΏΞ½Ο„ΞΏΞΌΞ±Ο�Ξ―"));
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
