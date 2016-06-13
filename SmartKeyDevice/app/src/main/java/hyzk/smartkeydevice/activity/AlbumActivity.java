package hyzk.smartkeydevice.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.adapter.AlbumGridViewAdapter;
import hyzk.smartkeydevice.app.ActivityList;
import hyzk.smartkeydevice.utils.AlbumHelper;
import hyzk.smartkeydevice.utils.Bimp;
import hyzk.smartkeydevice.utils.ImageBucket;
import hyzk.smartkeydevice.utils.ImageItem;


public class AlbumActivity extends Activity {

	private int PublicWaynum = 9;

	private GridView gridView;
	private TextView tv;
	private AlbumGridViewAdapter gridImageAdapter;
	private Button okButton;
	private Button back;
	private Button cancel;
	private Intent intent;
	private Button preview;
	private Context mContext;
	private ArrayList<ImageItem> dataList;
	private AlbumHelper helper;
	public static List<ImageBucket> contentList;
	public static Bitmap bitmap;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityList.getInstance().addActivity(this);
		setContentView(R.layout.plugin_camera_album);
		mContext = this;
		IntentFilter filter = new IntentFilter("data.broadcast.action");
		registerReceiver(broadcastReceiver, filter);  
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.plugin_camera_no_pictures);
        init();
		initListener();
		isShowOkBt();
	}
	
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	//mContext.unregisterReceiver(this);
            // TODO Auto-generated method stub  
        	gridImageAdapter.notifyDataSetChanged();
        }  
    };  


	private class PreviewListener implements OnClickListener {
		public void onClick(View v) {
			if (Bimp.tempSelectBitmap.size() > 0) {
				intent.putExtra("position", "3");
				intent.setClass(AlbumActivity.this, GalleryActivity.class);
				startActivity(intent);
			}
		}

	}

	private class AlbumSendListener implements OnClickListener {
		public void onClick(View v) {
			overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
			intent.setClass(mContext, InspectionActivity.class);
			startActivity(intent);
			finish();
			ActivityList.getInstance().removeActivity(AlbumActivity.this);
		}

	}

	private class BackListener implements OnClickListener {
		public void onClick(View v) {
			intent.setClass(AlbumActivity.this, ImageFileActivity.class);
			startActivity(intent);
		}
	}

	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			Bimp.tempSelectBitmap.clear();
			intent.setClass(mContext, InspectionActivity.class);
			startActivity(intent);
            finish();
			ActivityList.getInstance().removeActivity(AlbumActivity.this);
		}
	}

	private void init() {
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		
		contentList = helper.getImagesBucketList(false);
		dataList = new ArrayList<ImageItem>();
		for(int i = 0; i<contentList.size(); i++){
			dataList.addAll( contentList.get(i).imageList );
		}
		
		back = (Button) findViewById(R.id.back);
		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new CancelListener());
		back.setOnClickListener(new BackListener());
		preview = (Button) findViewById(R.id.preview);
		preview.setOnClickListener(new PreviewListener());
		preview.setText(R.string.preview+"(" + String.valueOf(Bimp.tempSelectBitmap.size())
				+ "/"+String.valueOf(PublicWaynum)+")");
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		gridView = (GridView) findViewById(R.id.myGrid);
		gridImageAdapter = new AlbumGridViewAdapter(this,dataList,
				Bimp.tempSelectBitmap);
		gridView.setAdapter(gridImageAdapter);
		tv = (TextView) findViewById(R.id.myText);
		gridView.setEmptyView(tv);
		okButton = (Button) findViewById(R.id.ok_button);
	}

	private void initListener() {

		gridImageAdapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(final ToggleButton toggleButton,
							int position, boolean isChecked,Button chooseBt) {
						if (Bimp.tempSelectBitmap.size() >= PublicWaynum) {
							toggleButton.setChecked(false);
							chooseBt.setVisibility(View.GONE);
							if (!removeOneData(dataList.get(position))) {
								Toast.makeText(AlbumActivity.this, getString(R.string.only_choose_num),
										Toast.LENGTH_SHORT).show();
							}
							return;
						}
						if (isChecked) {
							chooseBt.setVisibility(View.VISIBLE);
							Bimp.tempSelectBitmap.add(dataList.get(position));
							preview.setText(getString(R.string.preview)+"(" + String.valueOf(Bimp.tempSelectBitmap.size())
									+ "/"+String.valueOf(PublicWaynum)+")");
						} else {
							Bimp.tempSelectBitmap.remove(dataList.get(position));
							chooseBt.setVisibility(View.GONE);
							preview.setText(getString(R.string.preview)+"(" + String.valueOf(Bimp.tempSelectBitmap.size()) + "/"+String.valueOf(PublicWaynum)+")");
						}
						isShowOkBt();
					}
				});

		okButton.setOnClickListener(new AlbumSendListener());

	}

	private boolean removeOneData(ImageItem imageItem) {
			if (Bimp.tempSelectBitmap.contains(imageItem)) {
				Bimp.tempSelectBitmap.remove(imageItem);
				preview.setText(getString(R.string.preview)+"(" +String.valueOf(Bimp.tempSelectBitmap.size()) + "/"+String.valueOf(PublicWaynum)+")");
				return true;
			}
		return false;
	}
	
	public void isShowOkBt() {
		if (Bimp.tempSelectBitmap.size() > 0) {
			preview.setText(getString(R.string.preview)+"(" + String.valueOf(Bimp.tempSelectBitmap.size()) + "/"+String.valueOf(PublicWaynum)+")");
			preview.setPressed(true);
			okButton.setPressed(true);
			preview.setClickable(true);
			okButton.setClickable(true);
			okButton.setTextColor(Color.WHITE);
			preview.setTextColor(Color.WHITE);
		} else {
			preview.setText(getString(R.string.preview)+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWaynum+")");
			preview.setPressed(false);
			preview.setClickable(false);
			okButton.setPressed(false);
			okButton.setClickable(false);
			okButton.setTextColor(Color.parseColor("#E1E0DE"));
			preview.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			intent.setClass(AlbumActivity.this, InspectionActivity.class);
			startActivity(intent);
            finish();
			ActivityList.getInstance().removeActivity(AlbumActivity.this);
		}
		return false;

	}
@Override
protected void onRestart() {
	isShowOkBt();
	super.onRestart();
}
}
