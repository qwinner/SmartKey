package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import hyzk.smartkeydevice.R;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by wyq on 2016/6/13.
 */
public class WarningActivity extends Activity {

    private Spinner spinner1;
    private ArrayAdapter adapter1;

    private Spinner spinner2;
    private ArrayAdapter adapter2;

    private Button rbackBtn;

    private PtrClassicFrameLayout mPtrFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        InitView();
    }

    private void InitView(){
        spinner1 = (Spinner) findViewById(R.id.Spinner01W);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.statusD, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new SpinnerXMLSelectedListener1());
        spinner1.setVisibility(View.VISIBLE);

        spinner2 = (Spinner) findViewById(R.id.Spinner02W);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.posD, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new SpinnerXMLSelectedListener2());
        spinner2.setVisibility(View.VISIBLE);

        rbackBtn = (Button)findViewById(R.id.rbackW);
        rbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            }
        });

        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.view_frameW);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //updateData();
                mPtrFrame.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(true);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 1000);
    }

    class SpinnerXMLSelectedListener1 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            Toast.makeText(WarningActivity.this, "状态："+adapter1.getItem(arg2), Toast.LENGTH_SHORT).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }

    class SpinnerXMLSelectedListener2 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            Toast.makeText(WarningActivity.this, "状态："+adapter2.getItem(arg2), Toast.LENGTH_SHORT).show();
        }
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            return true;
        }else {}
        return super.onKeyDown(keyCode, event);
    }
}
