package com.jingcheng.ordermeal.adapter;


import java.util.HashMap;
import java.util.List;

import com.example.jingchengordermeal3.R;
import com.jingcheng.ordermeal.bean.Dish;
import com.jingcheng.ordermeal.bean.Select;

import android.R.raw;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridMenuAdapter extends BaseAdapter implements OnClickListener {
	private ViewHolder holder;
	private List<Dish> list_dish;
	private Dish dish;
	private OnButtonClickListener mListener;
	private HashMap<String,Select> selectMap;
	
	public GridMenuAdapter(List<Dish> list_dish, HashMap<String,Select> selectMap, OnButtonClickListener Listener) {
		super();
		this.list_dish = list_dish;
		this.mListener = Listener;
		this.selectMap = selectMap;
	}

	@Override
	public int getCount() {
		return list_dish.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list_dish.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.grid_menu_item, null);
			holder = new ViewHolder();
			holder.grid_dishName = (TextView) convertView.findViewById(R.id.grid_dishName);
			holder.grid_iv = (ImageView) convertView.findViewById(R.id.grid_iv);
			holder.grid_new = (TextView) convertView.findViewById(R.id.grid_new);
			holder.grid_price = (TextView) convertView.findViewById(R.id.grid_price);
			holder.grid_join = (Button) convertView.findViewById(R.id.grid_join);
			holder.grid_select = (RelativeLayout) convertView.findViewById(R.id.grid_select);
			holder.grid_add = (Button) convertView.findViewById(R.id.grid_add);
			holder.grid_cancel = (Button) convertView.findViewById(R.id.grid_cancel);
			holder.grid_number = (TextView) convertView.findViewById(R.id.grid_number);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		dish = list_dish.get(position);
		holder.dish = dish;
		holder.position = position;
		holder.grid_dishName.setText(dish.getDishName());
		holder.grid_iv.setImageResource(dish.getImage());
		holder.grid_price.setText(String.valueOf(dish.getPrice()));
		//判断是否新品
		if(dish.isNew()){
			holder.grid_new.setVisibility(View.VISIBLE);
		}else{
			holder.grid_new.setVisibility(View.GONE);
		}
		//判断是否已选
		Select select = selectMap.get(String.valueOf(dish.getDishId()));
		if(select == null){
			holder.grid_join.setVisibility(View.VISIBLE);
			holder.grid_number.setText("1");
			holder.grid_select.setVisibility(View.GONE);
		}else{
			holder.grid_join.setVisibility(View.GONE);
			holder.grid_select.setVisibility(View.VISIBLE);
			holder.grid_number.setText(String.valueOf(select.getCount()));
		}
		
		//设置监听
		holder.grid_join.setTag(holder);
		holder.grid_join.setOnClickListener(this);
		holder.grid_add.setTag(holder);
		holder.grid_add.setOnClickListener(this);
		holder.grid_cancel.setTag(holder);
		holder.grid_cancel.setOnClickListener(this);
		holder.grid_iv.setTag(holder);
		holder.grid_iv.setOnClickListener(this);
		return convertView;
	}
	
	public class ViewHolder{
		public ImageView grid_iv;
		public TextView grid_dishName;
		public TextView grid_new;
		public Button grid_join;
		public Button grid_add;
		public Button grid_cancel;
		public TextView grid_number;
		public TextView grid_price;
		public RelativeLayout grid_select;
		public int position;
		public Dish dish;
	}
	
	public interface OnButtonClickListener{
		void onButtonClick(View view);
	}

	@Override
	public void onClick(View view) {
		mListener.onButtonClick(view);
	}
}
