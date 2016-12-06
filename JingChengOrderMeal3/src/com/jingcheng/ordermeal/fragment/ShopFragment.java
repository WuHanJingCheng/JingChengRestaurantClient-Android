package com.jingcheng.ordermeal.fragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingchengordermeal3.R;
import com.jingcheng.ordermeal.activity.MainActivity;
import com.jingcheng.ordermeal.adapter.ListShopAdapter;
import com.jingcheng.ordermeal.adapter.ListShopAdapter.OnButtonShopClickListener;
import com.jingcheng.ordermeal.adapter.ListShopAdapter.ViewHolder;
import com.jingcheng.ordermeal.bean.Dish;
import com.jingcheng.ordermeal.bean.Select;

public class ShopFragment extends BaseFragment implements OnClickListener {
	public static final String shop = "shop.broadcast.action";
	private MainActivity context;
	private ListView shop_lv;
	public HashMap<String,Select> selectMap;
	private List<Select> selectList = new ArrayList<Select>();
	private ListShopAdapter adapter;
	private Button shop_allDelete, shop_commit, shop_again, 
	shop_null_again, shop_null_begin, mild, medium, peppery;
	private TextView all_price, shop_tableId;
	private RelativeLayout shop_null_rl, shop_rl;
	private SharedPreferences sp = null;
	private SharedPreferences.Editor editor = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_shop, container,false);
		shop_lv = (ListView) root.findViewById(R.id.shop_lv);
		shop_null_rl = (RelativeLayout) root.findViewById(R.id.shop_null_rl);
		shop_rl = (RelativeLayout) root.findViewById(R.id.shop_rl);
		all_price = (TextView) root.findViewById(R.id.all_price);
		shop_allDelete = (Button) root.findViewById(R.id.shop_allDelete);
		shop_commit = (Button) root.findViewById(R.id.shop_commit);
		shop_again = (Button) root.findViewById(R.id.shop_again);
		shop_null_again = (Button) root.findViewById(R.id.shop_null_again);
		shop_null_begin = (Button) root.findViewById(R.id.shop_null_begin);
		shop_tableId = (TextView) root.findViewById(R.id.shop_tableId);
		
		mild = (Button) root.findViewById(R.id.mild);
		medium = (Button) root.findViewById(R.id.medium);
		peppery = (Button) root.findViewById(R.id.peppery);
		
		context = (MainActivity) getActivity();
		
		shop_allDelete.setOnClickListener(this);
		shop_commit.setOnClickListener(this);
		shop_again.setOnClickListener(this);
		shop_null_again.setOnClickListener(this);
		shop_null_begin.setOnClickListener(this);
		mild.setOnClickListener(this);
		medium.setOnClickListener(this);
		peppery.setOnClickListener(this);
		initData();
		IntentFilter filter = new IntentFilter(shop);
		context.registerReceiver(broadcastReceiver, filter);
		
		
		return root;
	}
	
	private void initView() {
		adapter = new ListShopAdapter(selectList, new OnButtonShopClickListener() {
			
			@Override
			public void onButtonClick(View v) {
				ViewHolder holder = (ViewHolder) v.getTag();
				Select select = holder.select;
				int count = select.getCount();
				String value = String.valueOf(select.getDishId());
				switch (v.getId()) {
				case R.id.bt_add_shop://加菜
					count++;
					select.setCount(count);//数量+1
//					holder.count.setText(String.valueOf(count));
					adapter.notifyDataSetChanged();//更新界面
					selectMap.put(value, select);//更新map集合
					initData();//更新list集合
					context.sendBroadcast(new Intent(MenuFragment.menu).putExtra("data", 100));
					((MainActivity) context).countShow();
					break;
				case R.id.bt_cancel_shop://减菜
					if(count == 1){//数量为1--此时要减为0
						selectMap.remove(value);//更新map集合 删除该菜品
						context.sendBroadcast(new Intent(MenuFragment.menu).putExtra("data", 100));
					}else{
						count--;
						select.setCount(count);//数量-1
						selectMap.put(value, select);//更新map集合
						context.sendBroadcast(new Intent(MenuFragment.menu).putExtra("data", 100));
					}
					initData();//更新list集合
					adapter.notifyDataSetChanged();//更新界面
					((MainActivity) context).countShow();
					break;
		
				default:
					break;
				}
			
			}
		});
		shop_lv.setAdapter(adapter);
	}

	private void initData() {
		if(sp == null){
			sp = context.getSharedPreferences("OrderMeal", Activity.MODE_PRIVATE);
			editor = sp.edit();
		}
		String tableId = sp.getString("tableId", null);
		if(tableId == null){
			context.finish();
			Toast.makeText(context, "内部错误，未获取到桌号，请重新开桌！", 0).show();
		}
		shop_tableId.setText("桌号：" + tableId);
		selectMap = context.getSelectMap();
		selectList.clear();
		if(selectMap.isEmpty() || selectMap == null || selectMap.size() == 0){
			shop_rl.setVisibility(View.GONE);
			shop_null_rl.setVisibility(View.VISIBLE);
		}else{
			shop_null_rl.setVisibility(View.GONE);
			shop_rl.setVisibility(View.VISIBLE);
			//将Map中的数据存储到List中  初始化Adapter数据
			for (Select v : selectMap.values()) {
				selectList.add(v);
			}
			statistics();
			initView();
		}
	}
	
	private void statistics() {
		int allCount = 0;
		float allPrice = 0f;
		for (int i = 0; i < selectList.size(); i++) {
			int count = selectList.get(i).getCount();
			allCount += count;
			float price = selectList.get(i).getPrice()*count;
			//保留两位小数
			price = new BigDecimal(price).setScale(2, 4).floatValue();   
			allPrice += price;
		}
		allPrice = new BigDecimal(allPrice).setScale(2, 4).floatValue();
//		all_count.setText(String.valueOf(allCount));
		all_price.setText(String.valueOf(allPrice)+"元");
		
	}
	
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			int code = intent.getIntExtra("data", 0);
			
			if(code == 200){
				initData();
				adapter.notifyDataSetChanged();
			}else{//从详情界面传递回来的广播
				int count = intent.getIntExtra("count", 0);
				String key = intent.getStringExtra("key");
				if(count == 0){
					selectMap.remove(key);
					initData();
					adapter.notifyDataSetChanged();
				}else{
					Dish dish = (Dish) intent.getSerializableExtra("dish");
					selectMap.put(String.valueOf(dish.getDishId()), 
							new Select(dish.getDishId(),
									dish.getDishName(), 
									dish.getDetails(), 
									dish.getPrice(), 
									dish.getImage(), 
									dish.isNew(), 
									dish.isHot(), 
									count));
					initData();
					adapter.notifyDataSetChanged();
				}
				show();
			}
			
		}
	};
	
	private void show(){
		context.countShow();
	}
	
	@Override
	public void onDestroyView() {
		context.unregisterReceiver(broadcastReceiver);
		super.onDestroyView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shop_allDelete:
			selectMap.clear();
			initData();
			adapter.notifyDataSetChanged();//更新界面
			((MainActivity) context).countShow();
			Toast.makeText(context, "购物车已清空", 0).show();
			context.sendBroadcast(new Intent(MenuFragment.menu).putExtra("data", 100));
			break;
		case R.id.shop_again://重新开桌
			context.finish();
			break;
		case R.id.shop_commit://发送下单
			Toast.makeText(context, "支付接口未开通", 0).show();
			break;
		case R.id.shop_null_again://空购物车-重新开桌
			context.finish();
			break;
		case R.id.shop_null_begin://空购物车 -点餐
			context.GoMenu();
			break;
		case R.id.mild://微辣
			NeedSelect(mild);
			break;
		case R.id.medium://麻辣
			NeedSelect(medium);
			break;
		case R.id.peppery://重辣
			NeedSelect(peppery);
			break;

		default:
			break;
		}
	}
	
	private void NeedSelect(Button view){
		mild.setBackground(getResources().getDrawable(R.drawable.need_bg, null));
		medium.setBackground(getResources().getDrawable(R.drawable.need_bg, null));
		peppery.setBackground(getResources().getDrawable(R.drawable.need_bg, null));
		view.setBackground(getResources().getDrawable(R.drawable.need_bg_down, null));
	}
}
