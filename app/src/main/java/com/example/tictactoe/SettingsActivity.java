package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    Button backBtn;
    String previousActivity;
    ImageView oneImg, twoImg, threeImg, fourImg, fiveImg, sixImg;
    Switch musicSwitch;
    int NUM_COLOR_OPTIONS = 6, imgIdx, selectedImgIdx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        backBtn = findViewById(R.id.backBtn);
        musicSwitch = findViewById(R.id.musicSwitch);
        initializeBtnImg();
        loadData();
        getImgView(selectedImgIdx).setImageResource(getSelectedImgResId(selectedImgIdx));

        oneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgIdx = 1;
                setSelectedImg(imgIdx);
                saveData(imgIdx);
            }
        });

        twoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgIdx = 2;
                setSelectedImg(imgIdx);
                saveData(imgIdx);
            }
        });
        threeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgIdx = 3;
                setSelectedImg(imgIdx);
                saveData(imgIdx);
            }
        });
        fourImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgIdx = 4;
                setSelectedImg(imgIdx);
                saveData(imgIdx);
            }
        });
        fiveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgIdx = 5;
                setSelectedImg(imgIdx);
                saveData(imgIdx);
            }
        });
        sixImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgIdx = 6;
                setSelectedImg(imgIdx);
                saveData(imgIdx);
            }
        });


        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String msg = "";
                if(isChecked){
                     msg = "Music On";
                    musicSwitch.setText(msg);
                }
                else{
                    msg = "Music Off";
                    musicSwitch.setText(msg);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousActivity = getIntent().getExtras().getString("previousActivity");
                startActivity(moveToPrevActivity(previousActivity));

            }
        });
    }
    public void loadData( ) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        selectedImgIdx = sharedPreferences.getInt("selectedImgIdx", 1);
    }
    public void saveData(int imgIdx) {

        int xResId =0, oResId = 0, xSelectResId =0, oSelectResId =0;
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /*
                - settings activity sends res id of .jpg images to board activity
                     - save both res id of x and o img as xResId and oResId
                     - save both res id of x and o with outline img (used in choose piece/setting activity) as xSelectResId and oSelectedResId
         */
        switch (imgIdx){  //select correct color combo based on which IV was clicked on
            case 1:
                // red x, blue o
                xResId = R.drawable.redx; // no border
                oResId = R.drawable.blo; // no border
                xSelectResId = R.drawable.redxselect; // has border, selected
                oSelectResId = R.drawable.bloselected; // has border, selected
                break;
            case 2:
                // navy blue x, orange o
                xResId = R.drawable.navyblx;
                oResId = R.drawable.orangeo;
                xSelectResId = R.drawable.navyblxselect;
                oSelectResId = R.drawable.orangeoselect;
                break;
            case 3:
                // lavender x, green o
                xResId = R.drawable.lavx;
                oResId = R.drawable.gro;
                xSelectResId = R.drawable.lavxselect;
                oSelectResId = R.drawable.groselect;
                break;
            case 4:
                // black x, green o
                xResId = R.drawable.bckx;
                oResId = R.drawable.gro;
                xSelectResId = R.drawable.bckxselect;
                oSelectResId = R.drawable.groselect;
                break;
            case 5:
                // teal x, light pink o
                xResId = R.drawable.tealx;
                oResId = R.drawable.lpinko;
                xSelectResId = R.drawable.tealxselect;
                oSelectResId = R.drawable.lpinkoselect;
                break;
            case 6:
                // yellow x, hot pink o
                xResId = R.drawable.yellowx;
                oResId = R.drawable.hotpko;
                xSelectResId = R.drawable.yellowxselect;
                oSelectResId = R.drawable.hotpkoselect;
                break;
        }

        // save res id data
        editor.putInt("selectedImgIdx", imgIdx);
        editor.putInt("xResId",xResId);
        editor.putInt("oResId",oResId);
        editor.putInt("xSelectResId",xSelectResId);
        editor.putInt("oSelectResId",oSelectResId);
        editor.apply();
    }
    private void setSelectedImg(int selectedImgIdx){
        // if IV is clicked, change curr img to selected img which has a border, and the rest of the IV are changed to img w/o a border
        assert getImgView(selectedImgIdx) != null;
        getImgView(selectedImgIdx).setImageResource(getSelectedImgResId(selectedImgIdx)); // selected img (w/border)

        //deselect all other images
        for(int i =1; i<=NUM_COLOR_OPTIONS; i++){ //loop thru rest of IV, change img to no border
            if(i != selectedImgIdx){
                assert getImgView(i) != null;
                getImgView(i).setImageResource(getDeselectedImgResId(i));
            }
        }
    }
    private int getDeselectedImgResId(int imgIdx){
        switch (imgIdx) {
            case 1: return R.drawable.redbl;
            case 2: return R.drawable.nyblor;
            case 3: return R.drawable.lavgr;
            case 4: return R.drawable.bckgr;
            case 5: return R.drawable.teallpink;
            case 6: return R.drawable.ywpk;
            default: return R.drawable.whitespace;
        }
    }
    private int getSelectedImgResId(int imgIdx){
        switch (imgIdx) {
            case 1: return R.drawable.redblselected;
            case 2: return R.drawable.nyblorselected;
            case 3: return R.drawable.lavgrselected;
            case 4: return R.drawable.bckgrselect;
            case 5: return R.drawable.teallpinkselected;
            case 6: return R.drawable.ywpkselect;
            default: return R.drawable.whitespace;
        }
    }
    private ImageView getImgView(int imgIdx)
    {
        switch (imgIdx){
          //  case 1: return oneImg;
            case 2: return twoImg;
            case 3: return threeImg;
            case 4: return fourImg;
            case 5: return fiveImg;
            case 6: return sixImg;
            default: return oneImg;
        }
    }
    // before moving to settings activity, store the name of the activity via intent.putExtra
    // therefore, can go from settings activity and back to previous activity
    public Intent moveToPrevActivity(String previousActivity){

        switch (previousActivity) {
            case "BoardActivity" : return new Intent(SettingsActivity.this, BoardActivity.class);
            case "ChoosePieceActivity" : return new Intent(SettingsActivity.this, ChoosePieceActivity.class);
            //case "MainActivity" : return new Intent(SettingsActivity.this, MainActivity.class);
            case "PlayersActivity" : return new Intent(SettingsActivity.this, PlayersActivity.class);
            case "RoundsActivity" : return new Intent(SettingsActivity.this, RoundsActivity.class);
            default: return new Intent(SettingsActivity.this, MainActivity.class);
        }
    }
    void initializeBtnImg(){
        // six colors -> six IV
        oneImg = findViewById(R.id.imageView1);
        twoImg = findViewById(R.id.imageView2);
        threeImg = findViewById(R.id.imageView3);
        fourImg = findViewById(R.id.imageView4);
        fiveImg = findViewById(R.id.imageView5);
        sixImg = findViewById(R.id.imageView6);
    }


}
