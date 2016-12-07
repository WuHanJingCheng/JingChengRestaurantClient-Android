package com.jingcheng.ordermeal.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.jingchengordermeal3.R;
import com.jingcheng.ordermeal.activity.DetailsActivity;
import com.jingcheng.ordermeal.activity.MainActivity;
import com.jingcheng.ordermeal.adapter.GridMenuAdapter;
import com.jingcheng.ordermeal.adapter.GridMenuAdapter.ViewHolder;
import com.jingcheng.ordermeal.bean.Dish;
import com.jingcheng.ordermeal.bean.Select;
import com.jingcheng.ordermeal.bean.SubMenu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.Animator.AnimatorListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuFragment extends BaseFragment {
	public static final String menu = "menu.broadcast.action";
	private MainActivity context;
	private LinearLayout gallery;
	private List<SubMenu> list_subMenu;
	private List<Dish> list_dish1;
	private List<Dish> list_dish2;
	private List<Dish> list_dish3;
	private List<Dish> list_dish4;
	private List<Dish> list_dish;
	
	private GridView grid_menu;
	private HashMap<String, Select> selectMap;
	private GridMenuAdapter mGridAdapter;
	private int position = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_menu, container,false);
		gallery = (LinearLayout) root.findViewById(R.id.gallery);
		grid_menu = (GridView) root.findViewById(R.id.grid);
		context = (MainActivity) getActivity();
		
		initData();
		initView();
		IntentFilter filter = new IntentFilter(menu);
		context.registerReceiver(broadcastReceiver, filter);
		return root;
	}


	private void initData() {
		//获取activity中的SelectMap
		selectMap = context.getSelectMap();
		
		//subMenu数据
		list_subMenu = new ArrayList<SubMenu>();
		list_subMenu = new ArrayList<SubMenu>();
		list_subMenu.add(new SubMenu(R.drawable.icon_rou, "肉类"));
		list_subMenu.add(new SubMenu(R.drawable.icon_haixian, "鱼类"));
		list_subMenu.add(new SubMenu(R.drawable.icon_shucai, "蔬菜"));
		list_subMenu.add(new SubMenu(R.drawable.icon_zhushi, "主食"));
		
		//dish数据
		list_dish1 = new ArrayList<Dish>();
		list_dish1.add(new Dish(1, "dishName_1", "dishName_1详情", 11, R.drawable.img_1, false, false));
		list_dish1.add(new Dish(2, "dishName_2", "dishName_2详情", 22, R.drawable.img_2,  true, false));
		list_dish1.add(new Dish(3, "dishName_3", "dishName_3详情", 33, R.drawable.img_3,  false, false));
		list_dish1.add(new Dish(4, "dishName_4", "dishName_4详情", 44, R.drawable.img_4,  false, false));
		list_dish1.add(new Dish(5, "dishName_5", "dishName_5详情", 55, R.drawable.img_5,  false, false));
		list_dish1.add(new Dish(6, "dishName_6", "dishName_6详情", 66, R.drawable.img_6,  true, false));
		list_dish1.add(new Dish(7, "dishName_7", "dishName_7详情", 77, R.drawable.img_7,  false, false));
		//dish数据
		list_dish2 = new ArrayList<Dish>();
		list_dish2.add(new Dish(11, "dishName_01", "dishName_01详情", 11, R.drawable.img_2,  false, false));
		list_dish2.add(new Dish(12, "dishName_02", "dishName_02详情", 22, R.drawable.img_5,  true, false));
		list_dish2.add(new Dish(13, "dishName_03", "dishName_03详情", 33, R.drawable.img_7,  false, false));
		list_dish2.add(new Dish(14, "dishName_04", "dishName_04详情", 44, R.drawable.img_6,  false, false));
		list_dish2.add(new Dish(15, "dishName_05", "dishName_05详情", 55, R.drawable.img_3,  false, false));
		list_dish2.add(new Dish(16, "dishName_06", "dishName_06详情", 66, R.drawable.img_4,  true, false));
		list_dish2.add(new Dish(17, "dishName_07", "dishName_07详情", 77, R.drawable.img_1,  false, false));
		//dish数据
		list_dish3 = new ArrayList<Dish>();
		list_dish3.add(new Dish(21, "dishName_001", "dishName_001详情", 11, R.drawable.img_7,  false, false));
		list_dish3.add(new Dish(22, "dishName_002", "dishName_002详情", 22, R.drawable.img_6,  true, false));
		list_dish3.add(new Dish(23, "dishName_003", "dishName_003详情", 33, R.drawable.img_5,  false, false));
		list_dish3.add(new Dish(24, "dishName_004", "dishName_004详情", 44, R.drawable.img_4,  false, false));
		list_dish3.add(new Dish(25, "dishName_005", "dishName_005详情", 55, R.drawable.img_3, false, false));
		list_dish3.add(new Dish(26, "dishName_006", "dishName_006详情", 66, R.drawable.img_1,  true, false));
		list_dish3.add(new Dish(27, "dishName_007", "dishName_007详情", 77, R.drawable.img_2,  false, false));
		//dish数据
		list_dish4 = new ArrayList<Dish>();
		list_dish4.add(new Dish(31, "dishName_0001", "dishName_0001详情", 11, R.drawable.img_5,  false, false));
		list_dish4.add(new Dish(32, "dishName_0002", "dishName_0002详情", 22, R.drawable.img_6,  true, false));
		list_dish4.add(new Dish(33, "dishName_0003", "dishName_0003详情", 33, R.drawable.img_1,  false, false));
		list_dish4.add(new Dish(34, "dishName_0004", "dishName_0004详情", 44, R.drawable.img_2,  false, false));
		list_dish4.add(new Dish(35, "dishName_0005", "dishName_0005详情", 55, R.drawable.img_4,  false, false));
		list_dish4.add(new Dish(36, "dishName_0006", "dishName_0006详情", 66, R.drawable.img_7, true, false));
		list_dish4.add(new Dish(37, "dishName_0007", "dishName_0007详情", 77, R.drawable.img_3,  false, false));
	}
	
//	public void selectDishMenu(int position){
//		
//	}
	
	private void initView() {
		// subMenu 
		for (int i = 0; i < list_subMenu.size(); i++) {
			View view = LayoutInflater.from(context).inflate(R.layout.gallery_item, gallery,false);
			ImageView gallery_iv = (ImageView) view.findViewById(R.id.gallery_iv);
			TextView gallery_tv = (TextView) view.findViewById(R.id.gallery_tv);
			gallery_rl = (RelativeLayout) view.findViewById(R.id.gallery_rl);
			gallery_iv.setImageResource(list_subMenu.get(i).getIcon());
			gallery_tv.setText(list_subMenu.get(i).getSubMenuName());
			gallery_rl.setBackground(getResources().getDrawable(R.drawable.gallery_item, null));
			if(i == position){
				gallery_rl.setBackground(getResources().getDrawable(R.drawable.gallery_item_down, null));
			}
			gallery_rl.setTag(i);
			gallery_rl.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					int i = (int) arg0.getTag();
//					selectDishMenu(position);
					position = i;
					gallery.removeAllViews();
					
					initView();
					initGrid();
				}
				
			});
			gallery.addView(view);
		}
		if(position == 0){
			list_dish = list_dish1;
		}else if(position == 1){
			list_dish = list_dish2;
		}else if(position == 2){
			list_dish = list_dish3;
		}else{
			list_dish = list_dish4;
		}
		initGrid();
	}
	
	
	private void initGrid() {
		// grid
		mGridAdapter = new GridMenuAdapter(list_dish, selectMap, new GridMenuAdapter.OnButtonClickListener() {
			
			@Override
			public void onButtonClick(View view) {
				final ViewHolder holder = (ViewHolder) view.getTag();
				Dish dish = holder.dish;
				String key = String.valueOf(dish.getDishId());
				Select select = selectMap.get(key);
				switch (view.getId()) {
				case R.id.grid_join:
					selectMap.put(key, new Select(dish.getDishId(), 
									dish.getDishName(), 
									dish.getDetails(), 
									dish.getPrice(), 
									dish.getImage(), 
									dish.isNew(), 
									dish.isHot(), 
									1));
					holder.grid_select.setVisibility(View.VISIBLE);
					view.setVisibility(View.GONE);
//							view.setEnabled(false);//此按钮设置成不可点击状态
					//v.getX()-holder.grid_cancle.getX();   //300原为holder.grid_cancle.getX()的测量值     
					PropertyValuesHolder v1 = PropertyValuesHolder.ofFloat("translationX",view.getX()-300, 0f);  
					PropertyValuesHolder v2 = PropertyValuesHolder.ofFloat("rotation", 0, 720);  
					PropertyValuesHolder v3 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.4f);  
					ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(holder.grid_cancel, v1, v2,v3);  
					objectAnimator1.setDuration(300); 
					objectAnimator1.start();
					//v.getX()-holder.grid_number.getX();  //334原为holder.grid_number.getX())的测量值    
					PropertyValuesHolder vv1 = PropertyValuesHolder.ofFloat("translationX", view.getX()-334, 0f);  
					PropertyValuesHolder vv2 = PropertyValuesHolder.ofFloat("rotation", 0, 360);  
					PropertyValuesHolder vv3 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.4f);  
					ObjectAnimator ObjectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(holder.grid_number, vv1, vv2,vv3);  
					ObjectAnimator2.setDuration(300);  
					ObjectAnimator2.start();
					holder.grid_cancel.setEnabled(true);
					context.countShow();
					context.sendBroadcast(new Intent(ShopFragment.shop).putExtra("data", 100));
					break;
				case R.id.grid_add:
					int con = selectMap.get(key).getCount();
					con++;
					select.setCount(con);
					holder.grid_number.setText(String.valueOf(con));
					selectMap.put(key, select);
					context.countShow();
					context.sendBroadcast(new Intent(ShopFragment.shop).putExtra("data", 100));
					break;
				case R.id.grid_cancel:
					int count = select.getCount();
					//当数量剪为0--view缩回动画，从selectMap集合中删除
					if(count == 1){
//								float a = holder.grid_cancle.getX();
//								float b = holder.grid_number.getX();
						view.setEnabled(false);
						holder.grid_add.setEnabled(false);
						selectMap.remove(key);
						PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX",  
								0f,holder.grid_add.getX() - view.getX());  
						PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("rotation", 0, 720);  
						ObjectAnimator.ofPropertyValuesHolder(holder.grid_cancel, p1, p2).setDuration(500).start();  
						PropertyValuesHolder pp1 = PropertyValuesHolder.ofFloat("translationX", 
								0f,holder.grid_add.getX()-holder.grid_number.getX());  
						PropertyValuesHolder pp2 = PropertyValuesHolder.ofFloat("rotation", 0, 360);  
						ObjectAnimator ObjectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(holder.grid_number, pp1, pp2);  
						ObjectAnimator1.setDuration(300);  
						ObjectAnimator1.start();  
						//监听动画结束后隐藏控件
						ObjectAnimator1.addListener(new AnimatorListener() {
							
							@Override
							public void onAnimationStart(Animator arg0) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animator arg0) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animator arg0) {
								holder.grid_select.setVisibility(View.GONE);
								holder.grid_add.setEnabled(true);//动画结束后添加按钮才允许继续点击
								holder.grid_join.setVisibility(View.VISIBLE);
								holder.grid_join.setEnabled(true);//来一份按钮设为可点击状态
							}
							
							@Override
							public void onAnimationCancel(Animator arg0) {
								// TODO Auto-generated method stub
							}
						});
						context.countShow();
						context.sendBroadcast(new Intent(ShopFragment.shop).putExtra("data", 100));
					}else{//若没有减至0，修改数量 存储起来
						count--;
						holder.grid_number.setText(String.valueOf(count));
						select.setCount(count);
						selectMap.put(key, select);
						context.countShow();
						context.sendBroadcast(new Intent(ShopFragment.shop).putExtra("data", 100));
					}
					break;
				case R.id.grid_iv:
					
					Intent intent  = new Intent();
					intent.setClass(context, DetailsActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("dishInfo", dish);
					if(select != null ){
						bundle.putInt("count", select.getCount());
					}
//							bundle.putSerializable("selectMap", selectMap);
					intent.putExtras(bundle);
					context.startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
		grid_menu.setAdapter(mGridAdapter);
	}


	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			int code = intent.getIntExtra("data", 0);
			
			if(code == 200){
				selectMap = ((MainActivity) context).getSelectMap();
				mGridAdapter.notifyDataSetChanged();
			}else{//从详情界面传递回来的广播
				int count = intent.getIntExtra("count", 0);
				String key = intent.getStringExtra("key");
				if(count == 0){
					selectMap.remove(key);
					selectMap = ((MainActivity) context).getSelectMap();
					mGridAdapter.notifyDataSetChanged();
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
					mGridAdapter.notifyDataSetChanged();
				}
				show();
			}
			
		}
	};
	private RelativeLayout gallery_rl;
	
	private void show(){
		context.countShow();
	}
	
	@Override
	public void onDestroyView() {
		context.unregisterReceiver(broadcastReceiver);
		super.onDestroyView();
	}
}
