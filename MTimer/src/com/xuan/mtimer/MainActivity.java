package com.xuan.mtimer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
/**
 * Elvn计时器
 * @author Elvn 軒
 *
 */
public class MainActivity extends Activity {

	// UI
	private View include_title;
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_title;
	
	private LinearLayout ll_main;
	private EditText et_first_time;
	private TextView tv_show_time;
	private TextView tv_round;
	private ImageView iv_start_stop;
	
	private EditText et_round_cound;
	private ToggleButton tb_shock;
	private Button btn_popup_submit;
	private Button btn_popup_cancel;

	// DATA
	private SharedPreferences sharedPreferences;
	private static final String SP_NAME = "elvn_timer_sp";
	private static final String SP_END_ROUND = "elvn_timer_end_round";
	private static final String SP_SHOCK = "elvn_timer_shock";
	private SoundPool soundPool;
	private Timer timer1;

	private int first_time = 0;
	private int show_time = 0;
	private double round = 0;
	private int end_round = 10;// 结束回合数

	private TimerTask task;
	private TimerTask task2;

	private Vibrator mVibrator;// 振动
	private boolean isShock;
	
	private boolean isStart = false;//是否开始

	// popup_menu
	private View contentView;
	private PopupWindow m_popupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
		setListener();
	}

	private void init() {
		//SharedPreferences
		sharedPreferences = getSharedPreferences(SP_NAME, 0);
		end_round = sharedPreferences.getInt(SP_END_ROUND, 1);
		isShock = sharedPreferences.getBoolean(SP_SHOCK, true);
		
		contentView = getLayoutInflater().inflate(R.layout.popupwindow_menu,
				null, true);
		include_title = (View)findViewById(R.id.include_title);
		tv_title = (TextView)findViewById(R.id.tv_title);
		iv_right = (ImageView)findViewById(R.id.iv_right);
		tv_title.setText(R.string.app_name);
		iv_right.setVisibility(View.VISIBLE);
		iv_right.setBackgroundResource(R.drawable.top_menu);
		
		ll_main = (LinearLayout) findViewById(R.id.ll_main);
		et_first_time = (EditText) findViewById(R.id.et_first_time);
		tv_show_time = (TextView) findViewById(R.id.tv_show_time);
		tv_round = (TextView) findViewById(R.id.tv_round);
		iv_start_stop = (ImageView) findViewById(R.id.iv_start_stop);
		
		et_round_cound = (EditText)contentView.findViewById(R.id.et_round_cound);
		tb_shock = (ToggleButton)contentView.findViewById(R.id.tb_shock);
		btn_popup_submit = (Button)contentView.findViewById(R.id.btn_popup_submit);
		btn_popup_cancel = (Button)contentView.findViewById(R.id.btn_popup_cancel);

		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 5);
		soundPool.load(MainActivity.this, R.raw.openbottle, 1);
		soundPool.load(MainActivity.this, R.raw.goodluck, 2);
		soundPool.load(MainActivity.this, R.raw.mymusic, 3);
		timer1 = new Timer();

		mVibrator = (Vibrator) getApplication().getSystemService(
				Service.VIBRATOR_SERVICE);// 获取振动器

		// PopupWindow弹出的窗口显示的view,第二和第三参数：分别表示此弹出窗口的大小
		m_popupWindow = new PopupWindow(contentView, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT, true);

		m_popupWindow.setBackgroundDrawable(new BitmapDrawable());// 有了这句才可以点击返回（撤销）按钮dismiss()popwindow
		m_popupWindow.setOutsideTouchable(true);
		m_popupWindow.setAnimationStyle(R.style.PopupAnimation);
		
		et_round_cound.setHint(end_round+" Rounds Now");
		tb_shock.setChecked(isShock);
	}

	private void setListener() {
		et_first_time.setOnTouchListener(my_onTouchListener);
		iv_start_stop.setOnClickListener(my_onClickListener);
		btn_popup_submit.setOnClickListener(my_onClickListener);
		btn_popup_cancel.setOnClickListener(my_onClickListener);
		iv_right.setOnClickListener(my_onClickListener);
	}
	private OnTouchListener my_onTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
			return false;
		}
	};
	private OnClickListener my_onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.iv_start_stop:
				if(isStart){//开始
					
					if (et_first_time.getText().toString().trim().equals("")) {
						Toast.makeText(MainActivity.this, "Please,Get me time.",
								Toast.LENGTH_SHORT).show();
						et_first_time.setFocusable(true);
					} else {
						isStart = false;
						iv_start_stop.setBackgroundResource(R.drawable.stop);
						round = 1;// 第一回合开始
						tv_round.setText((int) round + "");
						first_time = Integer.parseInt(et_first_time.getText()
								.toString()) * 1000;
						show_time = Integer.parseInt(et_first_time.getText()
								.toString()) + 1;
						tv_show_time.setText(show_time + "");
						timer1 = new Timer();
						timetask();
						timer1.schedule(task, first_time, first_time);
						timer1.scheduleAtFixedRate(task2, 0, 1000);
					}
					getWindow().addFlags(
							WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);// 隐藏软键盘
				}else{//停止
					Stop();
				}
				
				break;
			case R.id.btn_popup_submit://popup_window submit button
				Stop();//结束计时重新开始
				if(!et_round_cound.getText().toString().trim().equals("")){
					end_round = Integer.parseInt(et_round_cound.getText().toString().trim());
					sharedPreferences.edit().putInt(SP_END_ROUND, end_round).commit();
				}
				et_round_cound.setHint(end_round+" Rounds Now");
				et_round_cound.setText("");//清空数据
				isShock = tb_shock.isChecked();
				sharedPreferences.edit().putBoolean(SP_SHOCK, isShock).commit();
				m_popupWindow.dismiss();
				break;
			case R.id.btn_popup_cancel://Cancel
				m_popupWindow.dismiss();
				break;
			case R.id.iv_right:
				m_popupWindow.showAsDropDown(include_title);
				break;
			}
		}
	};

	private void timetask() {
		task = new TimerTask() {

			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}

		};
		task2 = new TimerTask() {

			public void run() {
				Message message2 = new Message();
				message2.what = 2;
				handler.sendMessage(message2);
			}

		};
	}

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				if (round == end_round) {
					Toast.makeText(
							MainActivity.this,
							"Congratulations! Today you insisted for "
									+ end_round + " rounds!", Toast.LENGTH_LONG)
							.show();
					soundPool.play(2, 1, 1, 0, 0, 1);
					Stop();
				} else {
					soundPool.play(1, 1, 1, 0, 0, 1);
					if(isShock){//判断是否震动
						mVibrator.vibrate(1000);
					}
					round = round + 0.5;
					tv_round.setText((int) round + "");
				}

				break;
			case 2:
				if (show_time > 1) {
					show_time--;
				} else {
					show_time = Integer.parseInt(et_first_time.getText()
							.toString());
				}

				tv_show_time.setText(show_time + "");
				break;
			}
			super.handleMessage(msg);
		}

	};

	protected void onStop() {
		super.onStop();
		Stop();

		// timer2.cancel();
	};

	/*
	 * Stop All
	 */
	private void Stop() {
		if (timer1 != null) {
			if (task != null && task2 != null) {
				task.cancel();
				task2.cancel();
				task = null;
				task2 = null;
			}
			timer1.cancel();
			timer1 = null;
			
			iv_start_stop.setBackgroundResource(R.drawable.start);
			isStart = true;
		}
		round = 0;// 重新开始
		tv_round.setText(((int) round) + "");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			m_popupWindow.showAsDropDown(include_title);
		}
		return super.onKeyDown(keyCode, event);
	}
}
