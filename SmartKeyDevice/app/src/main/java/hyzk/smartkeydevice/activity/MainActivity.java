package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.adapter.MyGridAdapter;
import hyzk.smartkeydevice.adapter.MyGridView;
import hyzk.smartkeydevice.app.ActivityList;
import hyzk.smartkeydevice.utils.ToastUtil;
import hyzk.smartkeydevice.widget.RadarScanView;
import hyzk.smartkeydevice.widget.RippleView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemExit;

    private RadarScanView radar;
    private RippleView rippleView;
    private boolean radarVisible = false;

    private MyGridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityList.getInstance().addActivity(this);
        setUpMenu();
        InitRadar();
        gridview=(MyGridView) findViewById(R.id.gridview);
        gridview.setAdapter(new MyGridAdapter(this));
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
        rippleView = (RippleView) findViewById(R.id.rippleView);
        rippleView.setMode(RippleView.MODE_OUT);
        rippleView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(radarVisible) {
                    StopRadar();
                }else{
                    StartRadar();
                }
            }
        });
        int fontColor = 0xff404040;
        int shadowColor = 0xFFEBCD;
        int TEXT_SIZE = 15;
        rippleView.setText(this.getResources().getString(R.string.bt_start_radar));
        rippleView.setTextColor(fontColor);
        rippleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        rippleView.setShadowLayer(1, 1, 1, shadowColor);
        rippleView.setGravity(Gravity.CENTER);
    }
    private void StartRadar(){
        radar.Show(true);
        rippleView.startRippleAnimation();
        rippleView.setText(this.getResources().getString(R.string.bt_stop_radar));
        radarVisible = true;
    }

    private void StopRadar(){
        radar.Show(false);
        rippleView.stopRippleAnimation();
        rippleView.setText(this.getResources().getString(R.string.bt_start_radar));
        radarVisible = false;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

//        if (view == itemHome){
//            changeFragment(new HomeFragment());
//        }else if (view == itemProfile){
//            changeFragment(new ProfileFragment());
//        }else if (view == itemCalendar){
//            changeFragment(new CalendarFragment());
//        }else if (view == itemSettings){
//            changeFragment(new SettingsFragment());
//        }

        resideMenu.closeMenu();
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

}
