package com.example.testfr.aty;

import com.example.testfr.R;
import com.example.testfr.R.id;
import com.example.testfr.R.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab2Fr extends BaseFr {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.from(mActivity).inflate(R.layout.fr_layout, null);
		TextView textView = (TextView) view.findViewById(R.id.tv_tv);
		textView.setText("Tab2");
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

}
