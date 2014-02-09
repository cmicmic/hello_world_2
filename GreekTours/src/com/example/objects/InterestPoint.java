package com.example.objects;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import gr.charismichelakis.kpe_vamou.R;

public class InterestPoint  implements Comparable<InterestPoint>{
	int id;
	String name; // title of the point
	String body; //main text
	String wikiUrl;
	double latitude;
	double longitude;
	int ImageResourceId;
	int bodyResourceId;
	String textResourceName;
	float distance;
	
	List<IPInfo> IPInfos = new ArrayList<IPInfo>(); // List of IPInfo in InterestPoint
	List<Double> location = new ArrayList<Double>(); //Point coordinates
	IPInfo info;//Object containing information such as text, photos
	boolean visited; //true if Interest Point is considered as visited
	String googleMapLink; //url for this location in google maps
	
	/*Constructors*/
	public InterestPoint(){
		this.id=0;
	}
	
//	public InterestPoint(int id){
//		this.id=id;
//		switch(id){
//		case 0:
//			this.ImageResourceId=R.drawable.ipinfo_photo_0_0;
//			this.textResourceName="ipinfo_photo_0_0";
//			break;
//		case 1:
//			this.ImageResourceId=R.drawable.ipinfo_photo_0_1;
//			this.textResourceName="ipinfo_photo_0_1";
//			break;
//		}
//	}
	
	public InterestPoint(Context context, String name){
		this.name=name;
		if(name.equals("Το Ενετικό Λιμάνι")){
			this.ImageResourceId=R.drawable.ipinfo_photo_0_0;
			this.bodyResourceId=R.raw.ipinfo_text_0_0;
			this.wikiUrl="http://el.wikipedia.org/wiki/%CE%A7%CE%B1%CE%BD%CE%B9%CE%AC";
			this.googleMapLink="https://www.google.com/maps/preview#!q=Akti+Tompazi%2C+Chania%2C+Greece&data=!4m15!2m14!1m13!1s0x149c7dbc3e308eb7%3A0xd55c1616498387f5!3m8!1m3!1d1359!2d24.0183393!3d35.5170487!3m2!1i1366!2i643!4f13.1!4m2!3d35.5166829!4d24.0178087";
			this.location.add(35.516600);
			this.location.add(24.017798);
		}else{
			if(name.equals("Παραλία Φαλάσσαρνα")){
				this.ImageResourceId=R.drawable.ipinfo_photo_1_0;
				this.bodyResourceId=R.raw.ipinfo_text_0_1;
				this.wikiUrl="http://www.cretanbeaches.com/paralies/hania/paralia-falassarna/";
				this.googleMapLink="https://www.google.com/maps/preview#!q=%CE%A6%CE%B1%CE%BB%CE%AC%CF%83%CE%B1%CF%81%CE%BD%CE%B1%2C+%CE%A7%CE%B1%CE%BD%CE%B9%CE%AC%2C+%CE%95%CE%BB%CE%BB%CE%AC%CE%B4%CE%B1&data=!4m15!2m14!1m13!1s0x149cf75377ef2fd1%3A0xa00bd2f74c2d5b0!3m8!1m3!1d1359!2d24.0178087!3d35.5166829!3m2!1i1366!2i643!4f13.1!4m2!3d35.5019631!4d23.5799002";
				this.location.add(35.501963);
				this.location.add(23.579900);
			}else{//dummy values 
				if(name.equals("Φραγκοκάστελλο")){
					this.ImageResourceId=R.drawable.ipinfo_photo_2_0;
					this.bodyResourceId=R.raw.ipinfo_text_2_0;
					this.wikiUrl="http://el.wikipedia.org/wiki/%CE%A6%CF%81%CE%B1%CE%B3%CE%BA%CE%BF%CE%BA%CE%AC%CF%83%CF%84%CE%B5%CE%BB%CE%BB%CE%BF";
					this.googleMapLink="https://www.google.com/maps/preview#!q=Fragkokastello%2C+Sfakia%2C+Greece&data=!1m4!1m3!1d10919!2d24.2284512!3d35.1857853!2m1!1e3!4m15!2m14!1m13!1s0x149b614f6e292991%3A0x3ce9902f64707cb1!3m8!1m3!1d5439!2d23.5817259!3d35.4994435!3m2!1i1366!2i643!4f13.1!4m2!3d35.1817316!4d24.2315394&fid=7";
					this.location.add(35.181732);
					this.location.add(24.231539);
				}else{
					if(name.equals("Σφακιά")){
						this.ImageResourceId=R.drawable.ipinfo_photo_3_0;
						this.bodyResourceId=R.raw.ipinfo_text_3_0;	
						this.wikiUrl="http://el.wikipedia.org/wiki/%CE%A6%CF%81%CE%B1%CE%B3%CE%BA%CE%BF%CE%BA%CE%AC%CF%83%CF%84%CE%B5%CE%BB%CE%BB%CE%BF";
						this.googleMapLink="https://www.google.com/maps/preview#!q=%CE%A3%CF%86%CE%B1%CE%BA%CE%B9%CE%AC&data=!1m4!1m3!1d13166!2d24.1391684!3d35.2023573!2m1!1e3!4m15!2m14!1m13!1s0x149c9c50b6e19677%3A0x400bd2ce2b9bb10!3m8!1m3!1d3976!2d24.2284512!3d35.1857853!3m2!1i1366!2i643!4f35!4m2!3d35.2689729!4d24.0963705&fid=7";
						this.location.add(35.201864);
						this.location.add(24.134458);
					}else{
						if(name.equals("Κοντομαρί")){
							this.ImageResourceId=R.drawable.ipinfo_photo_4_0;
							this.bodyResourceId=R.raw.ipinfo_text_4_0;	
							this.wikiUrl="http://www.youtube.com/watch?v=uEJB8Q2pzAM";
							this.googleMapLink="https://www.google.com/maps/preview#!q=%CE%9A%CE%BF%CE%BD%CF%84%CE%BF%CE%BC%CE%AC%CF%81%CE%B9%2C+%CE%A7%CE%B1%CE%BD%CE%B9%CE%AC%2C+%CE%95%CE%BB%CE%BB%CE%AC%CE%B4%CE%B1&data=!1m4!1m3!1d5438!2d23.8563314!3d35.5076794!2m1!1e3!4m15!2m14!1m13!1s0x149c8a1fad716d3d%3A0xa7a116eb403e7959!3m8!1m3!1d4795!2d24.1391684!3d35.2023573!3m2!1i1366!2i643!4f35!4m2!3d35.5071585!4d23.8566916&fid=7";
							this.location.add(35.507159);
							this.location.add(23.856692);
						}else{
							this.ImageResourceId=R.drawable.ipinfo_photo_2_0;
							this.bodyResourceId=R.raw.ipinfo_text_2_0;
						}
					}
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
		    catch (Exception e) {body = body +e.toString(); }
	}
	
	
//	public InterestPoint(int id, String name){
//		this.name=name;
//	}
//	
//	public InterestPoint(float distance, String name){
//		this.name=name;
//		this.distance=distance;
//	}
	
	/*Setters*/
	
	public void setInfo(IPInfo info){
		this.info=info;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setLocation(List<Double> location){
		this.location=location;
	}
	
	public void setDistance(float distance){
		this.distance=distance;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public void setVisited(boolean visited){
		this.visited=visited;
	}
	
/*Getters*/
	
//	public int getIdFromName(String name){
//		
//		return id;
//	}
	
	public IPInfo getInfo(){
		return info;
	}
	
	public int getImageResourceId(){
		return ImageResourceId;
	}
	
	public String getName(){
		return name;
	}
	
	public float getDistance(){
		return distance;
	}
	
	public String getBody(){
		return body;
	}
	public String getWikiUrl(){
		return wikiUrl;
	}
	public List<Double> getLocation(){
		return location;
	}
	public String getGoogleMapLink(){
		return googleMapLink;
	}
	public int getId(){
		return id;
	}
	
	public boolean getVisited(){
		return visited;
	}

	@Override
	public int compareTo(InterestPoint arg0) {
		float compareDistance = ((InterestPoint) arg0).getDistance();
		return (int) (this.distance - compareDistance);
	}
}
