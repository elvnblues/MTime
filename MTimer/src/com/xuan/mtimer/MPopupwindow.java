package com.xuan.mtimer;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MPopupwindow {
	public PopupWindow m_popupWindow;
	private View contentView;
	private Activity activity;

	// UI
	// private ImageView iv_cd_icon; // 提示图片
	private TextView tv_cd_title;// 提示标题
	private TextView tv_cd_content;// 提示内容
	private Button btn_cd_one;// 第一个按钮
	private Button btn_cd_two;// 第二个按钮

	public MPopupwindow(Context context) {
		activity = (Activity) context;
		contentView = activity.getLayoutInflater().inflate(
				R.layout.popup_customdialog, null, true);
		m_popupWindow = new PopupWindow(contentView, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT, true);
		m_popupWindow.setBackgroundDrawable(new BitmapDrawable());// 有了这句才可以点击返回（撤销）按钮dismiss()popwindow
		m_popupWindow.setOutsideTouchable(true);
		m_popupWindow.setAnimationStyle(R.style.PopupAnimation);
		init();
	}

	private void init() {
		// iv_cd_icon = (ImageView) contentView.findViewById(R.id.iv_cd_icon);
		tv_cd_title = (TextView) contentView.findViewById(R.id.tv_cd_title);
		tv_cd_content = (TextView) contentView.findViewById(R.id.tv_cd_content);
		btn_cd_one = (Button) contentView.findViewById(R.id.btn_cd_one);
		btn_cd_two = (Button) contentView.findViewById(R.id.btn_cd_two);
	}

	/**
	 * 弹出对话框
	 * 
	 * @param v
	 */
	public void showAsDropDown(View v) {
		m_popupWindow.showAsDropDown(v);
		m_popupWindow.setFocusable(true);
	}

	/**
	 * 弹出对话框
	 * 
	 * @param v
	 * @param xoff
	 * @param yoff
	 */
	public void showAsDropDown(View v, int xoff, int yoff) {
		m_popupWindow.showAsDropDown(v, xoff, yoff);
		m_popupWindow.setFocusable(true);
	}

	/**
	 * 销毁popupwindow
	 */
	public void dismiss() {
		m_popupWindow.dismiss();
	}

	/**
	 * 弹出对话框
	 */
	public void show() {
		m_popupWindow.showAtLocation(contentView.findViewById(R.id.rl_parent),
				Gravity.CENTER | Gravity.CENTER, 0, 0);
		m_popupWindow.update();
		m_popupWindow.setFocusable(true);
	}

	// /**
	// * 设置提示图片
	// *
	// * @param resId
	// */
	// public void setIconSrc(int resId) {
	// iv_cd_icon.setImageResource(resId);
	// }
	/**
	 * 设置提示标题_内容
	 * 
	 * @param text
	 */
	public void setTitleValue(CharSequence text) {
		tv_cd_title.setText(text);
	}

	/**
	 * 设置提示标题_内容
	 * 
	 * @param resid
	 */
	public void setTitleValue(int resid) {
		tv_cd_title.setText(resid);
	}

	/**
	 * 设置提示标题_字体大小
	 * 
	 * @param size
	 */
	public void setTitleSize(float size) {
		tv_cd_title.setTextSize(size);
	}

	/**
	 * 设置提示标题_字体颜色
	 * 
	 * @param colors
	 */
	public void setTitleColor(ColorStateList colors) {
		tv_cd_title.setTextColor(colors);
	}

	/**
	 * 设置提示标题_字体颜色
	 * 
	 * @param color
	 */
	public void setTitleColor(int color) {
		tv_cd_title.setTextColor(color);
	}

	/**
	 * 设置提示内容_内容
	 * 
	 * @param text
	 */
	public void setContentValue(CharSequence text) {
		tv_cd_content.setText(text);
		tv_cd_content.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置提示内容_内容
	 * 
	 * @param resid
	 */
	public void setContentValue(int resid) {
		tv_cd_content.setText(resid);
		tv_cd_content.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置提示内容_字体大小
	 * 
	 * @param size
	 */
	public void setContentSize(float size) {
		tv_cd_content.setTextSize(size);
	}

	/**
	 * 设置提示内容_字体颜色
	 * 
	 * @param colors
	 */
	public void setContentColor(ColorStateList colors) {
		tv_cd_content.setTextColor(colors);
	}

	/**
	 * 设置提示内容_字体颜色
	 * 
	 * @param color
	 */
	public void setContentColor(int color) {
		tv_cd_content.setTextColor(color);
	}

	/**
	 * 设置第一个按钮_内容
	 * 
	 * @param text
	 */
	public void setOneButtonValue(CharSequence text) {
		btn_cd_one.setText(text);
	}

	/**
	 * 设置第一个按钮_内容
	 * 
	 * @param resid
	 */
	public void setOneButtonValue(int resid) {
		btn_cd_one.setText(resid);
	}

	/**
	 * 设置第一个按钮_字体大小
	 * 
	 * @param size
	 */
	public void setOneButtonSize(float size) {
		btn_cd_one.setTextSize(size);
	}

	/**
	 * 设置第一个按钮_字体颜色
	 * 
	 * @param colors
	 */
	public void setOneButtonColor(ColorStateList colors) {
		btn_cd_one.setTextColor(colors);
	}

	/**
	 * 设置第一个按钮_字体颜色
	 * 
	 * @param color
	 */
	public void setOneButtonColor(int color) {
		btn_cd_one.setTextColor(color);
	}

	/**
	 * 设置第一个按钮_点击事件
	 * 
	 * @param l
	 */
	public void setOneButtonListener(OnClickListener l) {
		btn_cd_one.setOnClickListener(l);
	}

	/**
	 * 设置第二个按钮_内容
	 * 
	 * @param text
	 */
	public void setTwoButtonValue(CharSequence text) {
		btn_cd_two.setText(text);
		btn_cd_two.setVisibility(View.VISIBLE);// 显示第二个按钮
	}

	/**
	 * 设置第二个按钮_内容
	 * 
	 * @param resid
	 */
	public void setTwoButtonValue(int resid) {
		btn_cd_two.setText(resid);
		btn_cd_two.setVisibility(View.VISIBLE);// 显示第二个按钮
	}

	/**
	 * 设置第二个按钮_字体大小
	 * 
	 * @param size
	 */
	public void setTwoButtonSize(float size) {
		btn_cd_two.setTextSize(size);
	}

	/**
	 * 设置第二个按钮_字体颜色
	 * 
	 * @param colors
	 */
	public void setTwoButtonColor(ColorStateList colors) {
		btn_cd_two.setTextColor(colors);
	}

	/**
	 * 设置第二个按钮_字体颜色
	 * 
	 * @param color
	 */
	public void setTwoButtonColor(int color) {
		btn_cd_two.setTextColor(color);
	}

	/**
	 * 设置第二个按钮_点击事件
	 * 
	 * @param l
	 */
	public void setTwoButtonListener(OnClickListener l) {
		btn_cd_two.setOnClickListener(l);
	}

}
