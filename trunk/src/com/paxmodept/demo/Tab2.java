package com.paxmodept.demo;
import java.util.List;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;  
import com.google.android.maps.GeoPoint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
public class Tab2 extends MapActivity {
	MapView mapView;
    MapController mc;
    GeoPoint p, p2;
    
    class MapOverlay extends com.google.android.maps.Overlay
    {
        @Override
        public boolean draw(Canvas canvas, MapView mapView, 
        boolean shadow, long when) 
        {
            super.draw(canvas, mapView, shadow);                   
            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView.getProjection().toPixels(p, screenPts);
 
            //---add the marker---
            Bitmap bmp = BitmapFactory.decodeResource(
                getResources(), R.drawable.marker);            
            canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);
            
            //---translate the GeoPoint to screen pixels---
            Point screenPts2 = new Point();
            mapView.getProjection().toPixels(p2, screenPts2);
 
            //---add the marker---
            Bitmap bmp2 = BitmapFactory.decodeResource(
                getResources(), R.drawable.marker);            
            canvas.drawBitmap(bmp2, screenPts2.x, screenPts2.y-50, null);         
            return true;
        }
    } 
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2);
        
        mapView = (MapView) findViewById(R.id.mapview);
        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);  
        View zoomView = mapView.getZoomControls(); 
 
        zoomLayout.addView(zoomView, 
            new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, 
                LayoutParams.WRAP_CONTENT)); 
        mapView.displayZoomControls(true);
        mc = mapView.getController();
        String coordinates[] = {"37.5589099", "126.9444183"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
 
        p = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));
        String coordinates2[] = {"37.557465", "126.9463232"};
        double lat2 = Double.parseDouble(coordinates2[0]);
        double lng2 = Double.parseDouble(coordinates2[1]);
 
        p2 = new GeoPoint(
            (int) (lat2 * 1E6), 
            (int) (lng2 * 1E6));
 
        mc.animateTo(p);
        mc.setZoom(18); 
        MapOverlay mapOverlay = new MapOverlay();
        List<Overlay> listOfOverlays = mapView.getOverlays();
        listOfOverlays.clear();
        listOfOverlays.add(mapOverlay);
        mapView.invalidate();
    }
    

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}