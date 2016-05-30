package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.CircularProgressButton;

import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.android_serialport_api.AsyncFingerprint;
import hyzk.smartkeydevice.android_serialport_api.SerialPortManager;
import hyzk.smartkeydevice.app.ActivityList;
import hyzk.smartkeydevice.fpi.MtGpio;
import hyzk.smartkeydevice.utils.ExtApi;
import hyzk.smartkeydevice.utils.ToastUtil;
import hyzk.smartkeydevice.fpi.MtGpio;

public class LoginActivity extends Activity {

    private static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

    private AsyncFingerprint vFingerprint;
    private boolean			 bIsUpImage=true;
    private boolean			 bIsCancel=false;
    private int				 iMatchId=0;
    private boolean			 bfpWork=false;

    private TextView		 tvFpStatus;
    private TextView		 tvWkStatus;
    private ImageView fpImage;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private CircularProgressButton cpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);

        cpBtn = (CircularProgressButton)findViewById(R.id.btnWithText);
        cpBtn.setProgress(0);
        cpBtn.setIndeterminateProgressMode(true); // turn on indeterminate progress
        cpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpBtn.setProgress(50);
                CheckPwLogin();
            }
        });
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        ActivityList.getInstance().setMainContext(this);
        ActivityList.getInstance().LoadConfig();
        ActivityList.getInstance().CheckDate="Check";//ExtApi.getDataForID();
        ActivityList.getInstance().DeviceSN= ExtApi.getDeviceID(this);

        mEmailView.setText(ActivityList.getInstance().DefaultUser);
        ExtApi.CreateDir(ActivityList.getInstance().DirWork);
        ExtApi.CreateDir(ActivityList.getInstance().TmpWork);
        ExtApi.CreateDir(ActivityList.getInstance().CacheWork);
        ExtApi.CreateDir(ActivityList.getInstance().DataWork);

        //UpdateApp.getInstance().setAppContext(this);
//        MtGpio.getInstance().BCPowerSwitch(false);
    }


    private void LoginSuccess(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    ActivityList.getInstance().TestMode=true;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }catch (InterruptedException error){
                    Log.e("splash",error.toString());
                }
            }
        }).start();

    }

    private void CheckPwLogin(){
        String username=mEmailView.getText().toString();
        String password=mPasswordView.getText().toString();
        ActivityList.getInstance().DefaultUser=username;
        ActivityList.getInstance().SaveConfigByVal("DefaultUser",username);
        if(username.equals("admin")){
            if(password.equals("1010")){
                bIsCancel=true;
                SerialPortManager.getInstance().closeSerialPort();
//                MtGpio.getInstance().RFPowerSwitch(false);

                cpBtn.setProgress(90); // set progress to 100 or -1 to indicate complete or error state
                LoginSuccess();
//                ActivityList.getInstance().TestMode=true;
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();
//                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

            }else if(password.equals("8888")){
                bIsCancel=true;
                SerialPortManager.getInstance().closeSerialPort();
//                MtGpio.getInstance().RFPowerSwitch(false);

                finish();
                cpBtn.setProgress(0); // set progress to 0 to switch back to normal state
            }else{
                ToastUtil.showToastTop(LoginActivity.this, "@string/login_failed");
                cpBtn.setProgress(0); // set progress to 0 to switch back to normal state
            }
        }else{
//            ActivityList.getInstance().TestMode=false;
//            if(username.equals(GlobalData.adminItem.username)&&password.equals(GlobalData.adminItem.password)){
//                logonType="0";
//                new Thread(runnablePutLog).start();
//            }else{
//                ToastUtil.showToastTop(LoginActivity.this, "@string/login_failed");
//            }
            cpBtn.setProgress(0); // set progress to 0 to switch back to normal state
        }
    }
}
