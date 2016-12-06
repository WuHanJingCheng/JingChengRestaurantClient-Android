package com.jingcheng.ordermeal.adapter;


import java.util.HashMap;
import java.util.List;

import com.example.jingchengordermeal3.R;
import com.jingcheng.ordermeal.adapter.GridMenuAdapter.OnButtonClickListener;
import com.jingcheng.ordermeal.bean.Select;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ListShopAdapter extends BaseAdapter implements OnClickListener {
	private ViewHolder holder;
	public List<Select> list;
	private OnButtonShopClickListener mListener;
	
	public ListShopAdapter(List<Select> selectList, OnButtonShopClickListener Listener) {
		super();
		this.list = selectList;
		this.mListener = Listener;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_shop_item, null);
			holder.tv_con_shop = (TextView) convertView.findViewById(R.id.tv_con_shop);
			holder.iv_dish_shop = (ImageView) convertView.findViewById(R.id.iv_dish_shop);
			holder.tv_dishName_shop = (TextView) convertView.findViewById(R.id.tv_dishName_shop);
			holder.bt_cancel_shop = (Button) convertView.findViewById(R.id.bt_cancel_shop);
			holder.tv_number_shop = (TextView) convertView.findViewById(R.id.tv_number_shop);
			holder.bt_add_shop = (Button) convertView.findViewById(R.id.bt_add_shop);
			holder.tv_price_shop = (TextView) convertView.findViewById(R.id.tv_price_shop);
			holder.tv_tag_shop = (TextView) convertView.findViewById(R.id.tv_tag_shop);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Select select = list.get(position);
		holder.tv_con_shop.setText(String.valueOf(position+1));
		holder.iv_dish_shop.setImageResource(select.getImage());
		holder.tv_dishName_shop.setText(select.getDishName());
		holder.tv_number_shop.setText(String.valueOf(select.getCount()));
		holder.tv_price_shop.setText("гд"+String.valueOf(select.getPrice()));
		
		holder.position = position;
		holder.select = select;
		holder.bt_add_shop.setTag(holder);
		holder.bt_cancel_shop.setTag(holder);
		holder.bt_add_shop.setOnClickListener(this);
		holder.bt_cancel_shop.setOnClickListener(this);
		return convertView;
	}
	public class ViewHolder{
		public TextView tv_con_shop;
		public ImageView iv_dish_shop;
		public TextView tv_dishName_shop;
		public Button bt_cancel_shop;
		public TextView tv_number_shop;
		public Button bt_add_shop;
		public TextView tv_price_shop;
		public TextView tv_tag_shop;
		public int position;
		public Select select;
	}
	public interface OnButtonShopClickListener{
		void onButtonClick(View view);
	}
	@Override
	public void onClick(View view) {
		mListener.onButtonClick(view);
	}
}
