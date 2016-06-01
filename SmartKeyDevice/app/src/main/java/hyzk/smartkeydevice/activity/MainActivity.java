package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.app.ActivityList;
import hyzk.smartkeydevice.utils.ToastUtil;

public class MainActivity extends Activity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityList.getInstance().addActivity(this);
        setUpMenu();
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
//
//    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
//        @Override
//        public void openMenu() {
//            ToastUtil.showToastTop(MainActivity.this, "Menu is opened");
//        }
//
//        @Override
//        public void closeMenu() {
//            ToastUtil.showToastTop(MainActivity.this, "Menu is closed");
//        }
//    };

}
