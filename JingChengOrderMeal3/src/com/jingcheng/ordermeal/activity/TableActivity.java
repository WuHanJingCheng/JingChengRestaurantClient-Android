package com.jingcheng.ordermeal.activity;

import com.example.jingchengordermeal3.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TableActivity extends BaseActivity implements OnClickListener {
	private Button bt_enter;
	private TextView et_tableId;
	private String table="";
	private SharedPreferences sp = null;
	private SharedPreferences.Editor editor = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		bt_enter = (Button) findViewById(R.id.bt_enter);
		et_tableId = (TextView) findViewById(R.id.et_tableId);
		
		initView();
	}

	private void initView() {
		bt_enter.setOnClickListener(this);
		et_tableId.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
							int arg3) {
						//s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
					}
					
					@Override
					public void afterTextChanged(Editable arg0) {
						 //s:变化后的所有字符
						table = arg0.toString();
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_enter:
			if(table.matches("[0-9]+")){
				if(sp == null){
					sp = getSharedPreferences("OrderMeal", Activity.MODE_PRIVATE);
					editor = sp.edit();
				}
				editor.putString("tableId", table).commit();
				et_tableId.setText("");
				startActivity(new Intent(this,MainActivity.class));
//				finish();
			}else if(table == null||table == ""){
				Toast.makeText(this, "请输入桌号", 0).show();
			}else{
				Toast.makeText(this, "请输入正确的桌号", 0).show();
			}
			
			
			break;

		default:
			break;
		}
	}
}
