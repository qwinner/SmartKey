package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.dd.CircularProgressButton;

import hyzk.smartkeydevice.R;
import android_serialport_api.AsyncFingerprint;
import android_serialport_api.SerialPortManager;
import hyzk.smartkeydevice.app.ActivityList;
import android.fpi.MtGpio;
import android.widget.Toast;

import hyzk.smartkeydevice.utils.ExtApi;
import hyzk.smartkeydevice.utils.ToastUtil;

public class LoginActivity extends Activity implements OnItemClickListener {

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
    private Button fingerLogin;

    private AlertView mAlertViewExt;//窗口拓展例子
    private InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);

        InitView();
        ShowFringerView();
    }

    private void ShowFringerView(){
        mAlertViewExt = new AlertView(this.getResources().getString(R.string.finger_login), this.getResources().getString(R.string.finger_readme), this.getResources().getString(R.string.cancel), null, null, this, AlertView.Style.Alert, LoginActivity.this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setMarginBottom(0);
        mAlertViewExt.show();
    }

    private void InitView(){
        fingerLogin = (Button)findViewById(R.id.fingerLogin);
        fingerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFringerView();
            }
        });

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
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEmailView.getWindowToken(),0);
        imm.hideSoftInputFromWindow(mPasswordView.getWindowToken(), 0);
       //添加 键盘 位置

        ActivityList.getInstance().setMainContext(this);
        ActivityList.getInstance().LoadConfig();
        ActivityList.getInstance().CheckDate="Check";//ExtApi.getDataForID();
        ActivityList.getInstance().DeviceSN= ExtApi.getDeviceID(this);

       // mEmailView.setText(ActivityList.getInstance().DefaultUser);
        ExtApi.CreateDir(ActivityList.getInstance().DirWork);
        ExtApi.CreateDir(ActivityList.getInstance().TmpWork);
        ExtApi.CreateDir(ActivityList.getInstance().CacheWork);
        ExtApi.CreateDir(ActivityList.getInstance().DataWork);

        //UpdateApp.getInstance().setAppContext(this);
        MtGpio.getInstance().BCPowerSwitch(false);
    }


    @Override
    public void onItemClick(Object o,int position) {
        //closeKeyboard();
        //判断是否是拓展窗口View，而且点击的是非取消按钮
//        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
//            String name = etName.getText().toString();
//            if(name.isEmpty()){
//                Toast.makeText(this, "啥都没填呢", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(this, "hello,"+name, Toast.LENGTH_SHORT).show();
//            }
//
//            return;
//        }

    }

    private void LoginSuccess(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
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
                MtGpio.getInstance().RFPowerSwitch(false);

                cpBtn.setProgress(90); // set progress to 100 or -1 to indicate complete or error state
                LoginSuccess();

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
