package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import hyzk.smartkeydevice.data.GlobalData;
import hyzk.smartkeydevice.event.MessageEvent;
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
    private ImageView fpImage;

    private Timer fpTimer=null;
    private TimerTask fpTask=null;
    private Handler fpHandler;
    private static boolean fpTimeOut=true;

    private long exitTime = 0;
    private Runnable runnableGetAdmin;
    private Runnable runnablePutLog;
    private String logonUser;
    private String logonType;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private CircularProgressButton cpBtn;
    private Button fingerLogin;

    private AlertView mAlertViewExt;
    private InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        ActivityList.getInstance().addActivity(this);

        InitView();
        //ShowFringerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        bIsCancel=false;
        bfpWork=false;
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event){
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            exitApplication();
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_HOME){
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_SEARCH){
            return true;
        }else {}
        return super.onKeyDown(keyCode, event);
    }
    public void exitApplication(){
        if((System.currentTimeMillis()-exitTime) > 2000){
            exitTime = System.currentTimeMillis();
        }
        else{
            finish();
            ActivityList.getInstance().removeActivity(LoginActivity.this);
            System.exit(0);
        }
    }
    private void ShowFringerView(){
        mAlertViewExt = new AlertView(this.getResources().getString(R.string.finger_login), null, this.getResources().getString(R.string.cancel), null, null, this, AlertView.Style.Alert, this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.setMarginBottom(0);
        mAlertViewExt.show();
        tvFpStatus = (TextView)findViewById(R.id.tvFpStatus);
        fpImage = (ImageView)findViewById(R.id.etFinger);

        FPProcess();
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

//        ActivityList.getInstance().setMainContext(this);
//        ActivityList.getInstance().LoadConfig();
//        ActivityList.getInstance().CheckDate="Check";//ExtApi.getDataForID();
//        ActivityList.getInstance().DeviceSN= ExtApi.getDeviceID(this);
//
//        mEmailView.setText(ActivityList.getInstance().DefaultUser);
//        ExtApi.CreateDir(ActivityList.getInstance().DirWork);
//        ExtApi.CreateDir(ActivityList.getInstance().TmpWork);
//        ExtApi.CreateDir(ActivityList.getInstance().CacheWork);
//        ExtApi.CreateDir(ActivityList.getInstance().DataWork);

        //UpdateApp.getInstance().setAppContext(this);
//        MtGpio.getInstance().BCPowerSwitch(false);
//
//        final IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_SCREEN_OFF);
//        filter.addAction(Intent.ACTION_SCREEN_ON);
//        registerReceiver(mBatInfoReceiver, filter);
//
//        SetScreenOffTimeOut();
//        GlobalData.LoadAdminList();
//        GlobalData.LoadHistoryList();

//        for(int n=0;n<GlobalData.adminList.size();n++){
//            if(ActivityList.getInstance().DefaultUser.equals(GlobalData.adminList.get(n).username)){
//                GlobalData.adminItem=GlobalData.adminList.get(n);
//            }
//        }
        InitRunnable();
    }



    @Override
    public void onItemClick(Object o,int position) {
        //closeKeyboard();

//        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
//            String name = etName.getText().toString();
//            if(name.isEmpty()){
//                Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
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
                    ActivityList.getInstance().removeActivity(LoginActivity.this);
                }catch (InterruptedException error){
                    Log.e("splash",error.toString());
                }
            }
        }).start();
    }

    private void CheckPwLogin(){
        String username=mEmailView.getText().toString();
        String password=mPasswordView.getText().toString();
//        ActivityList.getInstance().DefaultUser=username;
//        ActivityList.getInstance().SaveConfigByVal("DefaultUser",username);
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
                MtGpio.getInstance().RFPowerSwitch(false);
                finish();
                ActivityList.getInstance().removeActivity(LoginActivity.this);
            }else{
                ToastUtil.showToastTop(LoginActivity.this, this.getResources().getString(R.string.login_failed));
                cpBtn.setProgress(0);
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

    private void CheckFpLogin() {
        logonType = "1";
        new Thread(runnablePutLog).start();
    }

    public void fpTimerStart() {
        if(fpTimer!=null){
            return;
        }
        fpTimeOut=true;
        fpTimer = new Timer();
        fpHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                fpTimerStop();
                if(fpTimeOut){
                    bfpWork=false;
                    tvFpStatus.setText(getString(R.string.txt_fg_timeout));
                    SerialPortManager.getInstance().closeSerialPort();
                }
                super.handleMessage(msg);
            }
        };
        fpTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                fpHandler.sendMessage(message);
            }
        };
        fpTimer.schedule(fpTask, 2000, 2000);
    }

    public void fpTimerStop() {
        if (fpTimer!=null) {
            fpTimer.cancel();
            fpTimer = null;
            fpTask.cancel();
            fpTask=null;
        }
    }



    private void FPInit(){
        vFingerprint.setOnGetImageExListener(new AsyncFingerprint.OnGetImageExListener() {
            @Override
            public void onGetImageExSuccess() {
                if(bIsUpImage){
                    vFingerprint.PS_UpImageEx();
                    tvFpStatus.setText(getString(R.string.txt_fg_read));
                    fpTimerStart();
                }else{
                    tvFpStatus.setText(getString(R.string.txt_fg_process));
                    vFingerprint.PS_GenCharEx(1);
                }
            }

            @Override
            public void onGetImageExFail() {
                if(!bIsCancel)
                    vFingerprint.PS_GetImageEx();
            }
        });

        vFingerprint.setOnUpImageExListener(new AsyncFingerprint.OnUpImageExListener() {
            @Override
            public void onUpImageExSuccess(byte[] data) {
                Bitmap image = BitmapFactory.decodeByteArray(data, 0, data.length);
                fpImage.setImageBitmap(image);
                vFingerprint.PS_GenCharEx(1);
                tvFpStatus.setText(getString(R.string.txt_fg_rd_success));
                fpTimeOut=false;
            }

            @Override
            public void onUpImageExFail() {
                bfpWork=false;
            }
        });

        vFingerprint.setOnGenCharExListener(new AsyncFingerprint.OnGenCharExListener() {
            @Override
            public void onGenCharExSuccess(int bufferId) {
                //vFingerprint.PS_Search(1, 1, 256);
                tvFpStatus.setText(getString(R.string.txt_fg_identify));
                String finger1 = ActivityList.getInstance().GetValFromConfig("fingerSN");
                if(finger1.length() > 200){
                    vFingerprint.PS_DownChar(android.util.Base64.decode(finger1, Base64.DEFAULT));
                }else{
                    tvFpStatus.setText(getString(R.string.txt_fg_failed));
                    SerialPortManager.getInstance().closeSerialPort();
                    bfpWork=false;
               }

//                if(GlobalData.adminItem.getTempStr1().length()>200){
//                    iMatchId=1;
//                    vFingerprint.PS_DownChar(GlobalData.adminItem.getTemplate1());
//                }else if(GlobalData.adminItem.getTempStr2().length()>200){
//                    iMatchId=2;
//                    vFingerprint.PS_DownChar(GlobalData.adminItem.getTemplate2());
//                }else{
//                    tvFpStatus.setText(getString(R.string.txt_fg_failed));
//                    SerialPortManager.getInstance().closeSerialPort();
//                    bfpWork=false;
//                }
            }

            @Override
            public void onGenCharExFail() {
                tvFpStatus.setText(getString(R.string.txt_fg_gen_failed));
                bfpWork=false;
            }
        });

        vFingerprint.setOnDownCharListener(new AsyncFingerprint.OnDownCharListener(){
            @Override
            public void onDownCharSuccess() {
                vFingerprint.PS_Match();
            }

            @Override
            public void onDownCharFail() {
                tvFpStatus.setText(getString(R.string.txt_fg_failed));
                SerialPortManager.getInstance().closeSerialPort();
                bfpWork=false;
            }
        });

        vFingerprint.setOnMatchListener(new AsyncFingerprint.OnMatchListener(){
            @Override
            public void onMatchSuccess() {
                tvFpStatus.setText(getString(R.string.txt_fg_ok));
                SerialPortManager.getInstance().closeSerialPort();
                bfpWork=false;
                LoginSuccess();
            }

            @Override
            public void onMatchFail() {
                if(iMatchId==1){
//                    if(GlobalData.adminItem.getTempStr2().length()>200){
//                        iMatchId=2;
//                        vFingerprint.PS_DownChar(GlobalData.adminItem.getTemplate2());
//                        return;
//                    }
                }
                tvFpStatus.setText(getString(R.string.txt_fg_failed));
                SerialPortManager.getInstance().closeSerialPort();
                bfpWork=false;
            }
        });

        vFingerprint.setOnSearchListener(new AsyncFingerprint.OnSearchListener() {
            @Override
            public void onSearchSuccess(int pageId, int matchScore) {
                tvFpStatus.setText(getString(R.string.txt_fg_ok));
            }

            @Override
            public void onSearchFail() {
                tvFpStatus.setText(getString(R.string.txt_fg_failed));
            }
        });
    }

    private void FPProcess(){
        if(!bfpWork){
            new Thread(runnableGetAdmin).start();
            vFingerprint = SerialPortManager.getInstance().getNewAsyncFingerprint();
            FPInit();
            try {
                Thread.currentThread();
                Thread.sleep(200);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            tvFpStatus.setText(this.getResources().getString(R.string.txt_fingerplace));
            vFingerprint.PS_GetImageEx();
            bfpWork=true;
        }
    }


    public void SetScreenOffTimeOut(){
        int tm=0;
        try {
            tm=Settings.System.getInt(getContentResolver(),android.provider.Settings.System.SCREEN_OFF_TIMEOUT);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if(tm<300000){
            Settings.System.putInt(getContentResolver(),android.provider.Settings.System.SCREEN_OFF_TIMEOUT,300000);
        }
    }

    private void InitRunnable() {
        runnableGetAdmin = new Runnable() {
            @Override
            public void run() {
                getAdminItemFromNet(logonUser);
            }
        };

        runnablePutLog = new Runnable() {
            @Override
            public void run() {
                postAdminLogonToNet(logonType);
            }
        };
    }

    public boolean getAdminItemFromNet(String name){
        if(ActivityList.getInstance().isonline) {
        }
        return true;
    }

    public boolean postAdminLogonToNet(String logontype){
        if(ActivityList.getInstance().TestMode) {
            return true;
        }
        return true;
    }

}
