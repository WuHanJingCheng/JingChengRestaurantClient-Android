package com.jingcheng.ordermeal.activity;

import com.example.jingchengordermeal3.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private Button bt_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		bt_login = (Button) findViewById(R.id.bt_login);
		bt_login.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			startActivity(new Intent(this,TableActivity.class));
			break;

		default:
			break;
		}
	}
}
