package com.example.objects;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.greektours.MyDebug;

import android.content.Context;
import android.util.Log;

import gr.charismichelakis.kpe_vamou.R;


public class Route extends InterestPoint{
	int id; //id of the route
	String name; // title of the point
	//String body; //main text
	//int ImageResourceId;
	int bodyResourceId;
    List<InterestPoint> interestPoints= new ArrayList<InterestPoint>(); // List of interest points in route
    List<String> ipNames = new ArrayList<String>();
    String description; //Short description of the route
    int distanceKm; //Distance of the route
    int durationHr; // Duration of the route
	boolean visited; //true if Route is considered as visited
	String googleMapLink; //Link to route in google map
	

	public Route(Context context, String name){
		this.name=name;
if (MyDebug.LOG) Log.i("Route/Route()","name: "+name);
		if(name.equals("Διαδρομή 1")){
//			this.ImageResourceId=R.drawable.route_photo_0_0;
			this.ImageResourceId=R.drawable.route1;
			this.bodyResourceId=R.raw.route_info_text_0_0;
			this.ipNames.add("Το Ενετικό Λιμάνι");this.ipNames.add("Παραλία Φαλάσσαρνα");this.ipNames.add("Φραγκοκάστελλο");this.ipNames.add("Σφακιά");
			this.googleMapLink="https://www.google.com/maps/preview#!data=!1m4!1m3!1d2719!2d24.0176777!3d35.5177275!4m53!3m50!1m4!3m2!3d35.5166577!4d24.017801!6e2!1m4!3m2!3d35.5177231!4d24.0181444!6e2!1m4!3m2!3d35.5182994!4d24.0186593!6e2!1m4!3m2!3d35.5181161!4d24.020215!6e2!1m4!3m2!3d35.5170943!4d24.0208695!6e2!1m4!3m2!3d35.5162211!4d24.020376!6e2!1m4!3m2!3d35.515863!4d24.020274!6e2!1m4!3m2!3d35.5159023!4d24.0207783!6e2!2e2!3m8!1m3!1d1359!2d24.0196035!3d35.5163106!3m2!1i1366!2i643!4f13.1!7m1!6i7&fid=0";
		}else{
			if(name.equals("Διαδρομή 2")){
				this.ImageResourceId=R.drawable.route_photo_1_0;
				this.bodyResourceId=R.raw.route_info_text_1_0;
				this.ipNames.add("Φραγκοκάστελλο");this.ipNames.add("Σφακιά");

			}else{//dummy values 
				if(name.equals("Διαδρομή 3")){
					this.ImageResourceId=R.drawable.route_photo_2_0;
					this.bodyResourceId=R.raw.route_info_text_2_0;
					this.ipNames.add("Κοντομαρί");this.ipNames.add("Σφακιά");this.ipNames.add("Φραγκοκάστελλο");
				}
			}
		}

		try 
		    {   
		        BufferedReader in = 
		            new BufferedReader(
		            new InputStreamReader(context.getResources().openRawResource(bodyResourceId)));
		        String line=in.readLine();
		        body = line;
		        in.close();
		    }
		    catch (Exception e) { body = body +e.toString(); }	
		}

	public Route(String name){
		this.name=name;
if (MyDebug.LOG) Log.i("Route/Route()","name: "+name);
		if(name.equals("�������� 1")){
//			this.ImageResourceId=R.drawable.route_photo_0_0;
			this.ImageResourceId=R.drawable.route1;
			this.bodyResourceId=R.raw.route_info_text_0_0;
			this.ipNames.add("�o ������� ������");this.ipNames.add("������� ����������");this.ipNames.add("��������������");this.ipNames.add("������");
			this.googleMapLink="https://www.google.com/maps/preview#!data=!1m4!1m3!1d2719!2d24.0176777!3d35.5177275!4m53!3m50!1m4!3m2!3d35.5166577!4d24.017801!6e2!1m4!3m2!3d35.5177231!4d24.0181444!6e2!1m4!3m2!3d35.5182994!4d24.0186593!6e2!1m4!3m2!3d35.5181161!4d24.020215!6e2!1m4!3m2!3d35.5170943!4d24.0208695!6e2!1m4!3m2!3d35.5162211!4d24.020376!6e2!1m4!3m2!3d35.515863!4d24.020274!6e2!1m4!3m2!3d35.5159023!4d24.0207783!6e2!2e2!3m8!1m3!1d1359!2d24.0196035!3d35.5163106!3m2!1i1366!2i643!4f13.1!7m1!6i7&fid=0";
		}else{
			if(name.equals("�������� 2")){
				this.ImageResourceId=R.drawable.route_photo_1_0;
				this.bodyResourceId=R.raw.route_info_text_1_0;
				this.ipNames.add("��������������");this.ipNames.add("������");

			}else{//dummy values 
				if(name.equals("�������� 3")){
					this.ImageResourceId=R.drawable.route_photo_2_0;
					this.bodyResourceId=R.raw.route_info_text_2_0;
					this.ipNames.add("���������");this.ipNames.add("������");this.ipNames.add("��������������");
				}
			}
		}
	}
	
	public List<String> getRouteIPNames(){
		return this.ipNames;
	}
//	public List<InterestPoint> getRouteInterestPoints(){
//    	for (int i=0;i<ipNames.size();i++){
//    		
//    	}

//	}
	public int getIpPos(String IpName){
		return this.ipNames.indexOf(IpName);
	}
	
	public String getIPItem(int pos){
		return this.ipNames.get(pos);
	}
	public String getGoogleMapLink(){
		return googleMapLink;
	}
}

