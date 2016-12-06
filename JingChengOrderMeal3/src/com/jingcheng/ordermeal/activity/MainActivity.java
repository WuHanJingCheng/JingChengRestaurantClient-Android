package com.jingcheng.ordermeal.activity;

import java.util.HashMap;

import com.example.jingchengordermeal3.R;
import com.jingcheng.ordermeal.bean.Dish;
import com.jingcheng.ordermeal.bean.Select;
import com.jingcheng.ordermeal.fragment.HotFragment;
import com.jingcheng.ordermeal.fragment.MenuFragment;
import com.jingcheng.ordermeal.fragment.ShopFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout hot, menu, shop, exit;
	private TextView bar_tv;
	private HotFragment hotFragment;
	private MenuFragment menuFragment;
	private ShopFragment shopFragment;
	private Fragment mContent = null;
	private View hot_view, menu_view, shop_view;
	public HashMap<String,Select> selectMap = new HashMap<String, Select>();
	private TextView count_show;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hot= (RelativeLayout) findViewById(R.id.hot);
		menu = (RelativeLayout) findViewById(R.id.menu);
		shop = (RelativeLayout) findViewById(R.id.shop);
		exit = (RelativeLayout) findViewById(R.id.exit);
		bar_tv = (TextView) findViewById(R.id.bar_tv);
		count_show = (TextView) findViewById(R.id.count);
		hot_view = findViewById(R.id.hot_view);
		menu_view = findViewById(R.id.menu_view);
		shop_view = findViewById(R.id.shop_view);
		
		hot.setOnClickListener(this);
		menu.setOnClickListener(this);
		shop.setOnClickListener(this);
		exit.setOnClickListener(this);
		
		hotFragment = new HotFragment();
		menuFragment = new MenuFragment();
		shopFragment = new ShopFragment();
		
		init();
	}

	private void init() {
		replceFragment(hotFragment);
		visiView(hot_view);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.hot://推荐
			bar_tv.setText("推荐");
			replceFragment(hotFragment);
			visiView(hot_view);
			break;
		case R.id.menu://菜单
			GoMenu();
			break;
		case R.id.shop://购物车
			bar_tv.setText("购物车");
			replceFragment(shopFragment);
			visiView(shop_view);
			break;
		case R.id.exit://退出
//			Toast.makeText(this, "确认弹框待制作。。", 0).show();
			finish();
			break;

		default:
			break;
		}
	}
	public void GoMenu(){
		bar_tv.setText("菜单");
		replceFragment(menuFragment);
		visiView(menu_view);
	}
	
	/**
	 * 替换fragment
	 * 
	 * @param fragment 要替换的fragment
	 * 
	 */
	private void replceFragment(Fragment fragment){
		if(mContent != fragment){
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			if(!fragment.isAdded()){
				if(mContent != null){
					ft.hide(mContent).add(R.id.framelayout, fragment).commit();
//					
				}else{
					ft.add(R.id.framelayout, fragment).commit();
				}
			}else{
				ft.hide(mContent).show(fragment).commit();
			}
			mContent = fragment;
		}
	}
	
	/**
	 * 选中图标更新
	 * @param view 要显示的view
	 */
	private void visiView(View view){
		hot_view.setVisibility(View.GONE);
		menu_view.setVisibility(View.GONE);
		shop_view.setVisibility(View.GONE);
		view.setVisibility(View.VISIBLE);
	}

	public HashMap<String, Select> getSelectMap() {
		return selectMap;
	}

	public void setSelectMap(HashMap<String, Select> selectMap) {
		this.selectMap = selectMap;
	}
	
	public void countShow(){
		int count = 0;
		if(!selectMap.isEmpty()){
			for (Select v : selectMap.values()) {
				count += v.getCount();
			}
		}
		if(count == 0){
			count_show.setText("0");
			count_show.setVisibility(View.GONE);
		}else{
			count_show.setText(String.valueOf(count));
			count_show.setVisibility(View.VISIBLE);
		}
	}
}
