package com.paxmodept.demo;


import android.app.Activity;
import android.os.Bundle;
import java.io.InputStream;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import com.paxmodept.demo.IconTextItem;
import com.paxmodept.demo.IconTextListAdapter;
import com.paxmodept.demo.IconTextView;
import com.paxmodept.demo.R;
import com.paxmodept.demo.XmlData;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

public class Tab3 extends ListActivity {
    /** Called when the activity is first created. */

	ListView myListview;

	IconTextView temp;

	ArrayList<XmlData> m_xmlData = new ArrayList<XmlData>();

	IconTextListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Use an existing ListAdapter that will map an array
		// of strings to TextViews

		// getListView().setTextFilterEnabled(true);

		// window feature for no title - must be set prior to calling
		// setContentView.
		requestWindowFeature(Window.FEATURE_CONTEXT_MENU);

	//	setContentView(R.layout.tab3);

		// myListview = (ListView) findViewById(R.id.myListview);

		adapter = new IconTextListAdapter(this);
		// add four items
		Resources res = getResources();

		m_xmlData = getXmlData("vicolochurch");

		// m_xmlData 가져오기

		Iterator<XmlData> it = m_xmlData.iterator();

		while (it.hasNext()) {
			// Book str = it.next();
			XmlData xmlData = it.next();
			adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.arrow),
					xmlData.d_title, xmlData.d_link, xmlData.d_author));
		}

		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub

		IconTextItem Item = (IconTextItem) adapter.getItem(position);
		String[] data = Item.getData();

		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data[1]));
		startActivity(intent);
	}

	public ArrayList<XmlData> getXmlData(String searchTxt) {
		// rss 경로 지정. searchTxt 는 채널 이름
		String m_sConnectUrl = "http://gdata.youtube.com/feeds/base/users/" + searchTxt + "/uploads?orderby=updated&alt=rss&client=ytapi-youtube-rss-redirect&v=2";

		XmlData xmlData = null;

		String sTag;

		try {

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();

			URL u = new URL(m_sConnectUrl);
			// InputStream in = u.openConnection().getInputStream();
			InputStream in = u.openStream();
			xpp.setInput(in, "utf-8");

			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
					// System.out.println("Start document");
				} else if (eventType == XmlPullParser.END_DOCUMENT) {
					// System.out.println("End document");
				} else if (eventType == XmlPullParser.START_TAG) {

					Log.e("START_TAG", xpp.getName());
					sTag = xpp.getName();

					if (sTag.equals("title")) {
						// Log.e("title_getText",xpp.nextText());
						xmlData = new XmlData();
						xmlData.d_title = xpp.nextText();
					}
					if (sTag.equals("link")) {
						xmlData.d_link = xpp.nextText();
					}
					if (sTag.equals("author")) {
						// Log.e("title_getText",xpp.nextText());
						xmlData.d_author = xpp.nextText();
					}

					// System.out.println("Start tag "+xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					// System.out.println("End tag "+xpp.getName());
					sTag = xpp.getName();
					if (sTag.equals("item")) {
						m_xmlData.add(xmlData);
						xmlData = null;
					}
				} else if (eventType == XmlPullParser.TEXT) {
					// System.out.println("Text "+xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), "Connect failed",
					Toast.LENGTH_SHORT);
		}
		return m_xmlData;
	}

}