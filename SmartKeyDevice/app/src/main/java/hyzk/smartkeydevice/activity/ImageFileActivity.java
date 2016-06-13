package hyzk.smartkeydevice.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.adapter.FolderAdapter;
import hyzk.smartkeydevice.app.ActivityList;
import hyzk.smartkeydevice.utils.Bimp;

//相册修改，从哪里过来取消的时候回去
public class ImageFileActivity extends Activity {

	private FolderAdapter folderAdapter;
	private Button bt_cancel;
	private Context mContext;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_image_file);
		ActivityList.getInstance().addActivity(this);

		mContext = this;
		bt_cancel = (Button) findViewById(R.id.cancel);
		bt_cancel.setOnClickListener(new CancelListener());
		GridView gridView = (GridView) findViewById(R.id.fileGridView);
		TextView textView = (TextView) findViewById(R.id.headerTitle);
		textView.setText(getString(R.string.photo));
		folderAdapter = new FolderAdapter(this);
		gridView.setAdapter(folderAdapter);
	}

	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			Bimp.tempSelectBitmap.clear();
			Bimp.max = 0;
//			Intent intent = new Intent();
//			intent.setClass(mContext, InspectionActivity.class);
//			startActivity(intent);
			finish();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			Intent intent = new Intent();
//			intent.setClass(mContext, InspectionActivity.class);
//			startActivity(intent);
			finish();
			ActivityList.getInstance().removeActivity(ImageFileActivity.this);
		}
		
		return true;
	}

}
