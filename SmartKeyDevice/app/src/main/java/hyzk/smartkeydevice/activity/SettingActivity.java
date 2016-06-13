package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import android_serialport_api.AsyncFingerprint;
import android_serialport_api.SerialPortManager;
import hyzk.smartkeydevice.R;
import hyzk.smartkeydevice.app.ActivityList;

/**
 * Created by wyq on 2016/6/13.
 */
public class SettingActivity extends Activity {
    private Button rbackBtn;
    private LinearLayout fingerLBtn1;

    private AsyncFingerprint vFingerprint;
    private boolean			 bIsUpImage=true;
    private TextView tvFpStatus;
    private ImageView fpImage;

    private Dialog fpDialog;
    private boolean bcheck=false;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActivityList.getInstance().addActivity(this);
        InitView();
    }

    private void InitView(){
        rbackBtn = (Button)findViewById(R.id.rbackSet);
        rbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                ActivityList.getInstance().removeActivity(SettingActivity.this);
            }
        });

        fingerLBtn1 = (LinearLayout)findViewById(R.id.fingerLBtn1);
        fingerLBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPDialogInput();
            }
        });
    }

    private void FPDialogInput(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle(getString(R.string.txt_fingerplace));
        final LayoutInflater inflater = LayoutInflater.from(SettingActivity.this);
        View vl = inflater.inflate(R.layout.dialog_enrolfinger, null);
        fpImage = (ImageView) vl.findViewById(R.id.imageView1);
        tvFpStatus= (TextView) vl.findViewById(R.id.textview1);
        builder.setView(vl);
        builder.setCancelable(false);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SerialPortManager.getInstance().closeSerialPort();
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                SerialPortManager.getInstance().closeSerialPort();
                dialog.dismiss();
            }
        });

        fpDialog = builder.create();
        fpDialog.setCanceledOnTouchOutside(false);
        fpDialog.show();

        vFingerprint = SerialPortManager.getInstance().getNewAsyncFingerprint();
        FPProcessSave();
    }
    private void FPProcessSave(){
        vFingerprint.setOnGetImageExListener(new AsyncFingerprint.OnGetImageExListener() {
            @Override
            public void onGetImageExSuccess() {
                if(bcheck){
                    vFingerprint.PS_GetImageEx();
                }else{
                    if(bIsUpImage){
                        vFingerprint.PS_UpImageEx();
                        tvFpStatus.setText(getString(R.string.txt_fg_read));
                    }else{
                        tvFpStatus.setText(getString(R.string.txt_fg_process));
                        vFingerprint.PS_GenCharEx(count);
                    }
                }
            }

            @Override
            public void onGetImageExFail() {
                if(bcheck){
                    bcheck=false;
                    tvFpStatus.setText(getString(R.string.txt_fingerplace2));
                    vFingerprint.PS_GetImageEx();
                    count++;
                }else{
                    vFingerprint.PS_GetImageEx();
                }
            }
        });

        vFingerprint.setOnUpImageExListener(new AsyncFingerprint.OnUpImageExListener() {
            @Override
            public void onUpImageExSuccess(byte[] data) {
                Bitmap image = BitmapFactory.decodeByteArray(data, 0, data.length);
                fpImage.setImageBitmap(image);
                vFingerprint.PS_GenCharEx(count);
                tvFpStatus.setText(getString(R.string.txt_fg_rd_success));
            }

            @Override
            public void onUpImageExFail() {
            }
        });

        vFingerprint.setOnGenCharExListener(new AsyncFingerprint.OnGenCharExListener() {
            @Override
            public void onGenCharExSuccess(int bufferId) {
                if (bufferId == 1) {
                    bcheck=true;
                    tvFpStatus.setText(getString(R.string.txt_fingerplace2));
                    vFingerprint.PS_GetImageEx();
                } else if (bufferId == 2) {
                    vFingerprint.PS_RegModel();
                }
            }

            @Override
            public void onGenCharExFail() {
                tvFpStatus.setText(getString(R.string.txt_fg_gen_failed));
            }
        });


        vFingerprint.setOnRegModelListener(new AsyncFingerprint.OnRegModelListener() {

            @Override
            public void onRegModelSuccess() {
                vFingerprint.PS_UpChar();
                tvFpStatus.setText(getString(R.string.txt_fp_bidui_ok));
            }

            @Override
            public void onRegModelFail() {
                tvFpStatus.setText(getString(R.string.txt_fp_bidui_failed));
            }
        });

        vFingerprint.setOnUpCharListener(new AsyncFingerprint.OnUpCharListener() {

            @Override
            public void onUpCharSuccess(byte[] model) {
                //save finger template
                ActivityList.getInstance().SaveConfigByVal("fingerSN", android.util.Base64.encodeToString(model, Base64.DEFAULT));
                tvFpStatus.setText(getString(R.string.txt_fg_input_ok));

                SerialPortManager.getInstance().closeSerialPort();
                fpDialog.cancel();
            }

            @Override
            public void onUpCharFail() {
                tvFpStatus.setText(getString(R.string.txt_fg_input_failed));
            }
        });

        vFingerprint.setOnSearchListener(new AsyncFingerprint.OnSearchListener() {
            @Override
            public void onSearchSuccess(int pageId, int matchScore) {
                tvFpStatus.setText("search success");
            }

            @Override
            public void onSearchFail() {
                tvFpStatus.setText("search failed");
            }
        });
        count = 1;
        //model = null;
        tvFpStatus.setText(getString(R.string.txt_fingerplace));
        try {
            Thread.currentThread();
            Thread.sleep(200);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        vFingerprint.PS_GetImageEx();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
            ActivityList.getInstance().removeActivity(SettingActivity.this);
            return true;
        }else {}
        return super.onKeyDown(keyCode, event);
    }
}
