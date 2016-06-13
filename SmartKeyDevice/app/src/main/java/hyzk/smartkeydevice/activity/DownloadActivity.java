package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.app.ActivityList;


/**
 * Created by wyq on 2016/6/13.
 */
public class DownloadActivity extends Activity {
    private Button rbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ActivityList.getInstance().addActivity(this);
        InitView();
    }

    private void InitView(){
        rbackBtn = (Button)findViewById(R.id.rbackD);
        rbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
                ActivityList.getInstance().removeActivity(DownloadActivity.this);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            ActivityList.getInstance().removeActivity(DownloadActivity.this);
            return true;
        }else {}
        return super.onKeyDown(keyCode, event);
    }
}
