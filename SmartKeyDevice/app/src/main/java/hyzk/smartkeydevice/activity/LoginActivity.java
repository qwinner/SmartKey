package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);


        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPwLogin();
            }
        });

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
                ActivityList.getInstance().TestMode=true;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

            }else if(password.equals("8888")){
                bIsCancel=true;
                SerialPortManager.getInstance().closeSerialPort();
//                MtGpio.getInstance().RFPowerSwitch(false);

                finish();
            }else{
                ToastUtil.showToastTop(LoginActivity.this, "@string/login_failed");
            }
        }else{
//            ActivityList.getInstance().TestMode=false;
//            if(username.equals(GlobalData.adminItem.username)&&password.equals(GlobalData.adminItem.password)){
//                logonType="0";
//                new Thread(runnablePutLog).start();
//            }else{
//                ToastUtil.showToastTop(LoginActivity.this, "@string/login_failed");
//            }
        }
    }
}
