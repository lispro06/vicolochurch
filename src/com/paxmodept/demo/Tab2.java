package com.paxmodept.demo;

//import android.app.Activity;
import android.os.Bundle;
import com.google.android.maps.MapActivity;

public class Tab2 extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2);
    }
    

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}