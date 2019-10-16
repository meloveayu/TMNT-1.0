package com.example.tmnt_10;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private static String TAG = "MainFragment";
    private char flag = 0;
    private ImageButton mImageButton;
    private TextView mTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediaPlayer = MediaPlayer.create(getActivity(),R.raw.tmnt_theme);
        Log.d(TAG,"MF.onCreate() called");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"MF.onCreateView() called");
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        RadioGroup radioGroup = v.findViewById(R.id.rediobutton_name);
        mImageButton = v.findViewById(R.id.turtles_image);
        mTextView = v.findViewById(R.id.textview_nameshow);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //i is RadioButton's id
                pickTurtle(i);
            }
        });
        mImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onClickTurtleImage();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMediaPlayer != null) {
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
        }
        Log.d(TAG,"MF.onResume() called");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"MF.onStop() called");
        mMediaPlayer.stop();
        //改成pause的话，就可以实现续播的功能了
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"MF.onDestroy() called");
        mMediaPlayer.release();
    }

    private void onClickTurtleImage(){

        //show full name on TextView
        switch (flag){
            default:
                break;
            case 'd':
                mTextView.setText(R.string.Don);
                break;
            case 'l':
                mTextView.setText(R.string.Leo);
                break;
            case 'm':
                mTextView.setText(R.string.Mikey);
                break;
            case 'r':
                mTextView.setText(R.string.Raph);
                break;
        }
    }

    private void pickTurtle(int i){

        //choose turtle by RadioButton
        switch (i){
            case R.id.radiobutton_leo:
                mImageButton.setImageResource(R.drawable.tmntleo);
                mTextView.setText("");
                flag = 'l';
                break;
            case R.id.radiobutton_don:
                mImageButton.setImageResource(R.drawable.tmntdon);
                mTextView.setText("");
                flag = 'd';
                break;
            case R.id.radiobutton_mikey:
                mImageButton.setImageResource(R.drawable.tmntmike);
                mTextView.setText("");
                flag = 'm';
                break;
            case R.id.radiobutton_raph:
                mImageButton.setImageResource(R.drawable.tmntraph);
                mTextView.setText("");
                flag = 'r';
                break;
        }

    }
}
