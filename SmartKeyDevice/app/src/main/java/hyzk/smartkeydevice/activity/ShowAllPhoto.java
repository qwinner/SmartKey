package hyzk.smartkeydevice.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.adapter.AlbumGridViewAdapter;
import hyzk.smartkeydevice.app.ActivityList;
import hyzk.smartkeydevice.utils.Bimp;
import hyzk.smartkeydevice.utils.ImageItem;

public class ShowAllPhoto extends Activity {
	private int PublicWaynum = 9;

	private GridView gridView;
	private ProgressBar progressBar;
	private AlbumGridViewAdapter gridImageAdapter;

	private Button okButton;

	private Button preview;

	private Button back;

	private Button cancel;

	private TextView headTitle;
	private Intent intent;
	private Context mContext;
	public static ArrayList<ImageItem> dataList = new ArrayList<ImageItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_show_all_photo);
		ActivityList.getInstance().addActivity(this);

		mContext = this;
		back =      (Button) findViewById(R.id.showallphoto_back);
		cancel =    (Button) findViewById(R.id.showallphoto_cancel);
		preview =   (Button) findViewById(R.id.showallphoto_preview);
		okButton =  (Button) findViewById(R.id.showallphoto_ok_button);
		headTitle=(TextView) findViewById(R.id.showallphoto_headtitle);
		this.intent = getIntent();
		String folderName = intent.getStringExtra("folderName");
		if (folderName.length() > 8) {
			folderName = folderName.substring(0, 9) + "...";
		}
		headTitle.setText(folderName);
		cancel.setOnClickListener(new CancelListener());
		back.setOnClickListener(new BackListener(intent));
		preview.setOnClickListener(new PreviewListener());
		init();
		initListener();
		isShowOkBt();
	}
	
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            // TODO Auto-generated method stub  
        	gridImageAdapter.notifyDataSetChanged();
        }  
    };  

	private class PreviewListener implements OnClickListener {
		public void onClick(View v) {
			if (Bimp.tempSelectBitmap.size() > 0) {
				intent.putExtra("position", "2");
				intent.setClass(ShowAllPhoto.this, GalleryActivity.class);
				startActivity(intent);
				finish();
				ActivityList.getInstance().removeActivity(ShowAllPhoto.this);
			}
		}

	}

	private class BackListener implements OnClickListener {
		Intent intent;

		public BackListener(Intent intent) {
			this.intent = intent;
		}

		public void onClick(View v) {
			intent.setClass(ShowAllPhoto.this, ImageFileActivity.class);
			startActivity(intent);
            finish();
			ActivityList.getInstance().removeActivity(ShowAllPhoto.this);
		}

	}

	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			//Bimp.tempSelectBitmap.clear();
			intent.setClass(mContext, ImageFileActivity.class);
			startActivity(intent);
            finish();
			ActivityList.getInstance().removeActivity(ShowAllPhoto.this);
		}
	}

	private void init() {
		IntentFilter filter = new IntentFilter("data.broadcast.action");  
		registerReceiver(broadcastReceiver, filter);  
		progressBar = (ProgressBar) findViewById(R.id.showallphoto_progressbar);
		progressBar.setVisibility(View.GONE);
		gridView = (GridView) findViewById(R.id.showallphoto_myGrid);
		gridImageAdapter = new AlbumGridViewAdapter(this,dataList,
				Bimp.tempSelectBitmap);
		gridView.setAdapter(gridImageAdapter);
		okButton = (Button) findViewById(R.id.showallphoto_ok_button);
	}

	private void initListener() {

		gridImageAdapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
					public void onItemClick(final ToggleButton toggleButton,
							int position, boolean isChecked,
							Button button) {
						if (Bimp.tempSelectBitmap.size() >= PublicWaynum&&isChecked) {
							button.setVisibility(View.GONE);
							toggleButton.setChecked(false);
							Toast.makeText(ShowAllPhoto.this, R.string.only_choose_num, Toast.LENGTH_SHORT)
									.show();
							return;
						}

						if (isChecked) {
							button.setVisibility(View.VISIBLE);
							Bimp.tempSelectBitmap.add(dataList.get(position));
                            preview.setText(getString(R.string.preview)+"(" + String.valueOf(Bimp.tempSelectBitmap.size()) + "/"+String.valueOf(PublicWaynum)+")");
						} else {
							button.setVisibility(View.GONE);
							Bimp.tempSelectBitmap.remove(dataList.get(position));
                            preview.setText(getString(R.string.preview)+"(" + String.valueOf(Bimp.tempSelectBitmap.size()) + "/"+String.valueOf(PublicWaynum)+")");
						}
						isShowOkBt();
					}
				});

		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				okButton.setClickable(false);
				intent.setClass(mContext, InspectionActivity.class);
				startActivity(intent);
				finish();
				ActivityList.getInstance().removeActivity(ShowAllPhoto.this);
			}
		});

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
            preview.setText(getString(R.string.preview)+"(" + String.valueOf(Bimp.tempSelectBitmap.size()) + "/"+String.valueOf(PublicWaynum)+")");
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
			this.finish();
			intent.setClass(ShowAllPhoto.this, ImageFileActivity.class);
			startActivity(intent);
			ActivityList.getInstance().removeActivity(ShowAllPhoto.this);
		}

		return false;

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		isShowOkBt();
		super.onRestart();
	}

}
