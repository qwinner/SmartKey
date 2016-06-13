package hyzk.smartkeydevice.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hyzk.smartkeydevice.app.ActivityList;
import hyzk.smartkeydevice.utils.Bimp;
import hyzk.smartkeydevice.zoom.PhotoView;
import hyzk.smartkeydevice.zoom.ViewPagerFixed;

import hyzk.smartkeydevice.R;

public class GalleryActivity extends Activity {
	private int PublicWaynum = 9;

	private Intent intent;
    private Button back_bt;
	private Button send_bt;
	private Button del_bt;
	private TextView positionTextView;
	private int position;
	private int location = 0;
	
	private ArrayList<View> listViews = null;
	private ViewPagerFixed pager;
	private MyPageAdapter adapter;

	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	public List<String> del = new ArrayList<String>();
	
	private Context mContext;

	RelativeLayout photo_relativeLayout;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_gallery);// 切屏到主界面
		ActivityList.getInstance().addActivity(this);

		mContext = this;
		positionTextView = (TextView)findViewById(R.id.textview);
		back_bt = (Button) findViewById(R.id.gallery_back);
		send_bt = (Button) findViewById(R.id.send_button);
		del_bt =   (Button)findViewById(R.id.gallery_del);
		back_bt.setOnClickListener(new BackListener());
		send_bt.setOnClickListener(new GallerySendListener());
		del_bt.setOnClickListener(new DelListener());
		intent = getIntent();
		position = Integer.parseInt(intent.getStringExtra("position"));
		isShowOkBt();
		// 为发送按钮设置文字
		pager = (ViewPagerFixed) findViewById(R.id.gallery01);
		pager.setOnPageChangeListener(pageChangeListener);
		for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
			initListViews( Bimp.tempSelectBitmap.get(i).getBitmap() );
		}
		
		adapter = new MyPageAdapter(listViews);
		pager.setAdapter(adapter);
		pager.setPageMargin(10);
		int id = intent.getIntExtra("ID", 0);
		pager.setCurrentItem(id);
	}
	
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {
			location = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			positionTextView.setText("(" + String.valueOf(location + 1) + "/"+String.valueOf(PublicWaynum)+")");
		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};
	
	private void initListViews(Bitmap bm) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		PhotoView img = new PhotoView(this);
		img.setBackgroundColor(0xff000000);
		img.setImageBitmap(bm);
		img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		listViews.add(img);
	}

	private class BackListener implements OnClickListener {

		public void onClick(View v) {
			intent.setClass(GalleryActivity.this, ImageFileActivity.class);
			startActivity(intent);

		}
	}
	//删除当前图片
	private class DelListener implements OnClickListener {
		public void onClick(View v) {
			if (listViews.size() == 1) {
				Bimp.tempSelectBitmap.clear();
				Bimp.max = 0;
				positionTextView.setText("(" + String.valueOf(Bimp.tempSelectBitmap.size()) + "/"+String.valueOf(PublicWaynum)+")");
				Intent intent = new Intent("data.broadcast.action");
                sendBroadcast(intent);
				finish();
				ActivityList.getInstance().removeActivity(GalleryActivity.this);
			} else {
				Bimp.tempSelectBitmap.remove(location);
				Bimp.max--;
				pager.removeAllViews();
				listViews.remove(location);
				adapter.setListViews(listViews);
				positionTextView.setText("(" + String.valueOf(Bimp.tempSelectBitmap.size()) + "/"+String.valueOf(PublicWaynum)+")");
				adapter.notifyDataSetChanged();
			}
		}
	}

	private class GallerySendListener implements OnClickListener {
		public void onClick(View v) {
			finish();
			intent.setClass(mContext,InspectionActivity.class);
			startActivity(intent);
			ActivityList.getInstance().removeActivity(GalleryActivity.this);
		}

	}

	public void isShowOkBt() {
		if (Bimp.tempSelectBitmap.size() > 0) {
			positionTextView.setText("(" + Bimp.tempSelectBitmap.size() + "/"+PublicWaynum+")");
			send_bt.setPressed(true);
			send_bt.setClickable(true);
			send_bt.setTextColor(Color.WHITE);
		} else {
			send_bt.setPressed(false);
			send_bt.setClickable(false);
			send_bt.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(position==1){
				intent.setClass(GalleryActivity.this, InspectionActivity.class);//AlbumActivity
				startActivity(intent);
				this.finish();
				ActivityList.getInstance().removeActivity(GalleryActivity.this);
			}else if(position==2){
				intent.setClass(GalleryActivity.this, ShowAllPhoto.class);
				startActivity(intent);
				this.finish();
				ActivityList.getInstance().removeActivity(GalleryActivity.this);
			}else if(position==3){
				intent.setClass(GalleryActivity.this, AlbumActivity.class);
				startActivity(intent);
				this.finish();
				ActivityList.getInstance().removeActivity(GalleryActivity.this);
			}
		}
		return true;
	}
	
	
	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;

		private int size;
		public MyPageAdapter(ArrayList<View> listViews) {
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {
			try {
				((ViewPagerFixed) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
