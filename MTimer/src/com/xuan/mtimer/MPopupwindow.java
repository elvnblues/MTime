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
	// private ImageView iv_cd_icon; // ��ʾͼƬ
	private TextView tv_cd_title;// ��ʾ����
	private TextView tv_cd_content;// ��ʾ����
	private Button btn_cd_one;// ��һ����ť
	private Button btn_cd_two;// �ڶ�����ť

	public MPopupwindow(Context context) {
		activity = (Activity) context;
		contentView = activity.getLayoutInflater().inflate(
				R.layout.popup_customdialog, null, true);
		m_popupWindow = new PopupWindow(contentView, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT, true);
		m_popupWindow.setBackgroundDrawable(new BitmapDrawable());// �������ſ��Ե�����أ���������ťdismiss()popwindow
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
	 * �����Ի���
	 * 
	 * @param v
	 */
	public void showAsDropDown(View v) {
		m_popupWindow.showAsDropDown(v);
		m_popupWindow.setFocusable(true);
	}

	/**
	 * �����Ի���
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
	 * ����popupwindow
	 */
	public void dismiss() {
		m_popupWindow.dismiss();
	}

	/**
	 * �����Ի���
	 */
	public void show() {
		m_popupWindow.showAtLocation(contentView.findViewById(R.id.rl_parent),
				Gravity.CENTER | Gravity.CENTER, 0, 0);
		m_popupWindow.update();
		m_popupWindow.setFocusable(true);
	}

	// /**
	// * ������ʾͼƬ
	// *
	// * @param resId
	// */
	// public void setIconSrc(int resId) {
	// iv_cd_icon.setImageResource(resId);
	// }
	/**
	 * ������ʾ����_����
	 * 
	 * @param text
	 */
	public void setTitleValue(CharSequence text) {
		tv_cd_title.setText(text);
	}

	/**
	 * ������ʾ����_����
	 * 
	 * @param resid
	 */
	public void setTitleValue(int resid) {
		tv_cd_title.setText(resid);
	}

	/**
	 * ������ʾ����_�����С
	 * 
	 * @param size
	 */
	public void setTitleSize(float size) {
		tv_cd_title.setTextSize(size);
	}

	/**
	 * ������ʾ����_������ɫ
	 * 
	 * @param colors
	 */
	public void setTitleColor(ColorStateList colors) {
		tv_cd_title.setTextColor(colors);
	}

	/**
	 * ������ʾ����_������ɫ
	 * 
	 * @param color
	 */
	public void setTitleColor(int color) {
		tv_cd_title.setTextColor(color);
	}

	/**
	 * ������ʾ����_����
	 * 
	 * @param text
	 */
	public void setContentValue(CharSequence text) {
		tv_cd_content.setText(text);
		tv_cd_content.setVisibility(View.VISIBLE);
	}

	/**
	 * ������ʾ����_����
	 * 
	 * @param resid
	 */
	public void setContentValue(int resid) {
		tv_cd_content.setText(resid);
		tv_cd_content.setVisibility(View.VISIBLE);
	}

	/**
	 * ������ʾ����_�����С
	 * 
	 * @param size
	 */
	public void setContentSize(float size) {
		tv_cd_content.setTextSize(size);
	}

	/**
	 * ������ʾ����_������ɫ
	 * 
	 * @param colors
	 */
	public void setContentColor(ColorStateList colors) {
		tv_cd_content.setTextColor(colors);
	}

	/**
	 * ������ʾ����_������ɫ
	 * 
	 * @param color
	 */
	public void setContentColor(int color) {
		tv_cd_content.setTextColor(color);
	}

	/**
	 * ���õ�һ����ť_����
	 * 
	 * @param text
	 */
	public void setOneButtonValue(CharSequence text) {
		btn_cd_one.setText(text);
	}

	/**
	 * ���õ�һ����ť_����
	 * 
	 * @param resid
	 */
	public void setOneButtonValue(int resid) {
		btn_cd_one.setText(resid);
	}

	/**
	 * ���õ�һ����ť_�����С
	 * 
	 * @param size
	 */
	public void setOneButtonSize(float size) {
		btn_cd_one.setTextSize(size);
	}

	/**
	 * ���õ�һ����ť_������ɫ
	 * 
	 * @param colors
	 */
	public void setOneButtonColor(ColorStateList colors) {
		btn_cd_one.setTextColor(colors);
	}

	/**
	 * ���õ�һ����ť_������ɫ
	 * 
	 * @param color
	 */
	public void setOneButtonColor(int color) {
		btn_cd_one.setTextColor(color);
	}

	/**
	 * ���õ�һ����ť_����¼�
	 * 
	 * @param l
	 */
	public void setOneButtonListener(OnClickListener l) {
		btn_cd_one.setOnClickListener(l);
	}

	/**
	 * ���õڶ�����ť_����
	 * 
	 * @param text
	 */
	public void setTwoButtonValue(CharSequence text) {
		btn_cd_two.setText(text);
		btn_cd_two.setVisibility(View.VISIBLE);// ��ʾ�ڶ�����ť
	}

	/**
	 * ���õڶ�����ť_����
	 * 
	 * @param resid
	 */
	public void setTwoButtonValue(int resid) {
		btn_cd_two.setText(resid);
		btn_cd_two.setVisibility(View.VISIBLE);// ��ʾ�ڶ�����ť
	}

	/**
	 * ���õڶ�����ť_�����С
	 * 
	 * @param size
	 */
	public void setTwoButtonSize(float size) {
		btn_cd_two.setTextSize(size);
	}

	/**
	 * ���õڶ�����ť_������ɫ
	 * 
	 * @param colors
	 */
	public void setTwoButtonColor(ColorStateList colors) {
		btn_cd_two.setTextColor(colors);
	}

	/**
	 * ���õڶ�����ť_������ɫ
	 * 
	 * @param color
	 */
	public void setTwoButtonColor(int color) {
		btn_cd_two.setTextColor(color);
	}

	/**
	 * ���õڶ�����ť_����¼�
	 * 
	 * @param l
	 */
	public void setTwoButtonListener(OnClickListener l) {
		btn_cd_two.setOnClickListener(l);
	}

}
