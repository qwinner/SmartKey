package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.content.Intent;
import android.fpi.MtGpio;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.Timer;
import java.util.TimerTask;

import android_serialport_api.RfidReader;
import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.adapter.MyGridAdapter;
import hyzk.smartkeydevice.adapter.MyGridView;
import hyzk.smartkeydevice.app.ActivityList;
import hyzk.smartkeydevice.utils.ToastUtil;
import hyzk.smartkeydevice.widget.RadarScanView;
import hyzk.smartkeydevice.widget.RippleView;

public class MainActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemExit;

    private RadarScanView radar;
    private boolean radarVisible = false;
    private Button txViewBtn;

    private MyGridView gridview;

    private Button ExitBtn;
    private String deviceSn = null;

    private Timer startTimer=null;
    private TimerTask startTask;
    Handler startHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityList.getInstance().addActivity(this);

        setUpMenu();
        InitRadar();
        gridview=(MyGridView) findViewById(R.id.gridview);
        gridview.setAdapter(new MyGridAdapter(this));
        gridview.setOnItemClickListener(this);
        MtGpio.getInstance().RFPowerSwitch(true);
        RfidReader.getInstance().openSerialPort();
        RfidReader.getInstance().SetMessageHandler(rfidHandler);

        ExitBtn= (Button)findViewById(R.id.title_bar_right_menu);
        ExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityList.getInstance().Relogon();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void setUpMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        //resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);
        resideMenu.setShadowVisible(false);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_user,     "User");
        itemSettings  = new ResideMenuItem(this, R.drawable.icon_settings,  "Settings");
        itemExit = new ResideMenuItem(this, R.drawable.icon_exit,  "Exit");

        itemHome.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemExit.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemExit, ResideMenu.DIRECTION_LEFT);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }

    private void InitRadar(){
        radar = (RadarScanView)findViewById(R.id.radarView);
        txViewBtn = (Button)findViewById(R.id.txView);
        txViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radarVisible) {
                    StopRadar();
                }else{
                    StartRadar();
                }
            }
        });

    }
    private void StartRadar(){
        radar.Show(true);
        txViewBtn.setText(this.getResources().getString(R.string.bt_stop_radar));
        radarVisible = true;
        TimerStart();
        //RfidReader.getInstance().SendCmd(0x02);
    }

    private void StopRadar(){
        radar.Show(false);
        txViewBtn.setText(this.getResources().getString(R.string.bt_start_radar));
        radarVisible = false;
    }

    public void TimerStart() {
        if(startTimer!=null)
            return;

        startTimer = new Timer();
        startHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if((deviceSn != null) && (deviceSn.length()>5)){
                    TimerStop();
                }else{
                    RfidReader.getInstance().SendCmd(0x02);
                }
                super.handleMessage(msg);
            }
        };
        startTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                startHandler.sendMessage(message);
            }
        };
        startTimer.schedule(startTask, 1000, 1000);
    }

    public void TimerStop() {
        if (startTimer!=null) {
            startTimer.cancel();
            startTimer = null;
            startTask.cancel();
            startTask=null;
        }
    }

    private final Handler rfidHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        StopRadar();
        int sv=msg.arg2;
        byte[] sn=(byte[])msg.obj;
        Intent intent = new Intent(MainActivity.this,
                InspectionActivity.class);
        deviceSn = new String(sn);
        intent.putExtra("SN", deviceSn);
        startActivity(intent);
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){

        }else if (view == itemSettings){
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        }else if (view == itemExit){
            ActivityList.getInstance().Relogon();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }else {}

        resideMenu.closeMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RfidReader.getInstance().closeSerialPort();
        MtGpio.getInstance().RFPowerSwitch(false);
        RfidReader.getInstance().SetMessageHandler(null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_HOME){
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_SEARCH){
            return true;
        }else {}
        return super.onKeyDown(keyCode, event);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        StopRadar();
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, UploadActivity.class));
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, DownloadActivity.class));
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, WarningActivity.class));
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            default:
                break;
        }
    }

}
