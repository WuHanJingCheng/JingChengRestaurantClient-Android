package com.jingcheng.ordermeal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jingchengordermeal3.R;
import com.jingcheng.ordermeal.bean.Dish;
import com.jingcheng.ordermeal.fragment.MenuFragment;
import com.jingcheng.ordermeal.fragment.ShopFragment;

public class DetailsActivity extends BaseActivity implements OnClickListener {
	
	private Button details_finish;
	private Button bt_add_details;
	private Button bt_cancel_details;
	private ImageView details_iv;
	private TextView details_tv;
	private TextView tv_number_details;
	private Intent intent;
	private Dish info;
	private TextView details_price;
	private TextView details_name;
	private Button bt_addshop;
	private int count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		details_iv = (ImageView) findViewById(R.id.details_iv);
		details_tv = (TextView) findViewById(R.id.details_tv);
		details_finish = (Button) findViewById(R.id.details_finish);
		bt_add_details = (Button) findViewById(R.id.bt_add_details);
		bt_cancel_details = (Button) findViewById(R.id.bt_cancel_details);
		tv_number_details = (TextView) findViewById(R.id.tv_number_details);
		details_price = (TextView) findViewById(R.id.details_price);
		details_name = (TextView) findViewById(R.id.details_name);
		bt_addshop = (Button) findViewById(R.id.bt_addshop);
		setFinishOnTouchOutside(false);//  …Ë÷√¥•≈ˆdialogÕ‚ «∑Òfinish
		init();
		
	}

	private void init() {
		intent = this.getIntent();
		info = (Dish) intent.getSerializableExtra("dishInfo");
		details_iv.setImageResource(info.getImage());
		details_tv.setText(info.getDetails());
		details_name.setText(info.getDishName());
		details_price.setText(String.valueOf(info.getPrice()));
		
		bt_add_details.setOnClickListener(this);
		bt_cancel_details.setOnClickListener(this);
		bt_addshop.setOnClickListener(this);
		details_finish.setOnClickListener(this);
		
		count = intent.getIntExtra("count", 0);
		if(count != 0){
			tv_number_details.setText(String.valueOf(count));
			bt_addshop.setVisibility(View.GONE);
			bt_add_details.setVisibility(View.VISIBLE);
			bt_cancel_details.setVisibility(View.VISIBLE);
			tv_number_details.setVisibility(View.VISIBLE);
		}else{
			bt_addshop.setVisibility(View.VISIBLE);
			bt_add_details.setVisibility(View.GONE);
			bt_cancel_details.setVisibility(View.GONE);
			tv_number_details.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bt_add_details:
			count++;
			tv_number_details.setText(String.valueOf(count));
			sendBroadcast(new Intent(ShopFragment.shop)
									.putExtra("count", count)
									.putExtra("key", String.valueOf(info.getDishId()))
									.putExtra("dish", info));
			sendBroadcast(new Intent(MenuFragment.menu).putExtra("count", count).putExtra("dish", info));
			break;
		case R.id.bt_cancel_details:
			if(count != 1 ){
				count--;
				tv_number_details.setText(String.valueOf(count));
			}else{
				count = 0;
				bt_add_details.setVisibility(View.GONE);
				bt_cancel_details.setVisibility(View.GONE);
				tv_number_details.setVisibility(View.GONE);
				bt_addshop.setVisibility(View.VISIBLE);
			}
			sendBroadcast(new Intent(ShopFragment.shop).putExtra("count", count).putExtra("dish", info).putExtra("key", String.valueOf(info.getDishId())));
			sendBroadcast(new Intent(MenuFragment.menu).putExtra("count", count).putExtra("dish", info).putExtra("key", String.valueOf(info.getDishId())));
			break;
		case R.id.bt_addshop:
			count = 1;
			tv_number_details.setText("1");
			bt_addshop.setVisibility(View.GONE);
			bt_add_details.setVisibility(View.VISIBLE);
			bt_cancel_details.setVisibility(View.VISIBLE);
			tv_number_details.setVisibility(View.VISIBLE);
			sendBroadcast(new Intent(ShopFragment.shop).putExtra("count", count).putExtra("dish", info));
			sendBroadcast(new Intent(MenuFragment.menu).putExtra("count", count).putExtra("dish", info));
			break;
		case R.id.details_finish:
			finish();
			break;

		default:
			break;
		}
	}
}
