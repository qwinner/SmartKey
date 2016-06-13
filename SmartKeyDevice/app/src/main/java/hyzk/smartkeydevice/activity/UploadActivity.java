package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.app.ActivityList;

public class UploadActivity extends Activity {

    private Button rbackBtn;
    private TextView upbtn;
    private NumberProgressBar bnp;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ActivityList.getInstance().addActivity(this);
        rbackBtn = (Button)findViewById(R.id.rback);
        rbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
                ActivityList.getInstance().removeActivity(UploadActivity.this);
            }
        });
        upbtn = (TextView)findViewById(R.id.upbtn);
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bnp = (NumberProgressBar)findViewById(R.id.numberbar1);
//        bnp.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(1);
                    }
                });
            }
        }, 1000, 100);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            ActivityList.getInstance().removeActivity(UploadActivity.this);
            return true;
        }else {}
        return super.onKeyDown(keyCode, event);
    }
}
