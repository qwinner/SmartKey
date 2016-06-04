package hyzk.smartkeydevice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




import java.util.ArrayList;

import hyzk.smartkeydevice.R;

public class InspectionActivity extends Activity {

    private Button addpicBtn;
    private TextView txView;
    private static final int REQUEST_CODE = 123;
    private ArrayList<String> mResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        txView = (TextView)findViewById(R.id.imgText);
        addpicBtn = (Button)findViewById(R.id.addPic);
        addpicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // start multiple photos selector
//                Intent intent = new Intent(InspectionActivity.this, ImagesSelectorActivity.class);
//                // max number of images to be selected
//                intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 5);
//                // min size of image which will be shown; to filter tiny images (mainly icons)
//                intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
//                // show camera or not
//                intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
//                // pass current selected images as the initial value
//                intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
//                // start the selector
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        //Fresco.initialize(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get selected images from selector
//        if(requestCode == REQUEST_CODE) {
//            if(resultCode == RESULT_OK) {
//                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
//                assert mResults != null;
//
//                // show results in textview
//                StringBuffer sb = new StringBuffer();
//                sb.append(String.format("Totally %d images selected:", mResults.size())).append("\n");
//                for(String result : mResults) {
//                    sb.append(result).append("\n");
//                }
//                txView.setText(sb.toString());
//            }
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
