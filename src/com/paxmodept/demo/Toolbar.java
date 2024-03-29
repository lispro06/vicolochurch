package com.paxmodept.demo;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Toolbar extends LinearLayout {

	public Toolbar(final Context context) {
		super(context);
	}
	
	public Toolbar(final Context con, AttributeSet attrs) {
		super(con,attrs);		
		setOrientation(HORIZONTAL);
		setBackgroundColor(getResources().
				getColor(android.R.color.transparent));

		LayoutInflater inflater = (LayoutInflater) 
		con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.navigation, this);
		
		TypedArray a = con.obtainStyledAttributes(attrs, 
				R.styleable.Toolbar);
		String option = a.getString(R.styleable.Toolbar_tab_id);
		
		String resourceId = "com.paxmodept.demo:id/"+option;
		int optionId = getResources().getIdentifier(resourceId,null,null);        		
		TextView currentOption = (TextView) findViewById(optionId);
		currentOption.setBackgroundColor(getResources().
				getColor(android.R.color.white));
		currentOption.setTextColor(getResources().
				getColor(android.R.color.black));
		currentOption.requestFocus(optionId);
		currentOption.setFocusable(false);
		currentOption.setClickable(false);
		
		TextView tab1 = (TextView) findViewById(R.id.tab1);
		tab1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(con, Tab1.class);
				con.startActivity(intent);
			}
		});
		
		TextView tab2 = (TextView) findViewById(R.id.tab2);
		tab2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(con, Tab2.class);
				con.startActivity(intent);
			}
		});
		
		TextView tab3 = (TextView) findViewById(R.id.tab3);
		tab3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(con, Tab3.class);
				con.startActivity(intent);
			}
		});

		TextView tab4 = (TextView) findViewById(R.id.tab4);
		tab4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(con, Tab4.class);
				con.startActivity(intent);
			}
		});
		
		TextView tab5 = (TextView) findViewById(R.id.tab5);
		tab5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(con, Tab5.class);
				con.startActivity(intent);
			}
		});
	}
}
