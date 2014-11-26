package com.example.testfr.aty;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.testfr.R;
import com.example.testfr.view.DragLayout;
import com.example.testfr.view.DragLayout.DragListener;

public class MainActivity extends BaseFragementActivity implements OnCheckedChangeListener, OnClickListener {

	private ArrayList<Fragment> fragments;
	private DragLayout dl;
	private ImageView iv_icon;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initDragLayout();
		initView();
	}

	private void initDragLayout() {
		dl = (DragLayout) findViewById(R.id.dl_whole);
		dl.setDragListener(new DragListener() {
			@Override
			public void onOpen() {
				// lv.smoothScrollToPosition(new Random().nextInt(30));
			}

			@Override
			public void onClose() {
				shake();
			}

			@Override
			public void onDrag(float percent) {
				// ViewHelper.setAlpha(iv_icon, 1 - percent);
			}
		});

	}

	private void initView() {
		iv_icon = (ImageView) findViewById(R.id.iv_icon);
		iv_icon.setOnClickListener(this);
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Tab1Fr tab1Fr = new Tab1Fr();
		Tab2Fr tab2Fr = new Tab2Fr();
		Tab3Fr tab3Fr = new Tab3Fr();
		fragments = new ArrayList<Fragment>();
		fragments.add(tab1Fr);
		fragments.add(tab2Fr);
		fragments.add(tab3Fr);
		fragmentTransaction.add(R.id.tab_content, tab1Fr);
		fragmentTransaction.commitAllowingStateLoss();// //commit不能被同一个FragmentTransaction调用多次
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.main_radio);
		radioGroup.setOnCheckedChangeListener(this);

		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.item_text, new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N" }));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Toast.makeText(getApplicationContext(), "click " + position, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
		for (int i = 0; i < radioGroup.getChildCount(); i++) {
			if (checkedId == radioGroup.getChildAt(i).getId()) {
				showTab(i);
			}
		}
	}

	private void showTab(int i) {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment fragment = fragments.get(i);
		if (fragment.isAdded()) {
			fragment.onResume(); // 启动目标tab的onResume()
		} else {
			fragmentTransaction.add(R.id.tab_content, fragment);
		}
		for (int j = 0; j < fragments.size(); j++) {
			if (i == j) {
				fragmentTransaction.show(fragments.get(j));
			} else {
				fragmentTransaction.hide(fragments.get(j));
			}
		}
		fragmentTransaction.commitAllowingStateLoss();// commit不能被同一个FragmentTransaction调用多次
	}

	private void shake() {
		// iv_icon.startAnimation(AnimationUtils.loadAnimation(this,
		// R.anim.shake));
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.iv_icon:
			dl.open();
			break;

		default:
			break;
		}
	}
}
