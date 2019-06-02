package com.example.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private  final int MODE_FRIEND=1;
    private  final int MODE_YUVA=2;
    private  final int DEFAULT_MODE=MODE_FRIEND;
    private ImageView playButton;

    public int mode;

    public int level;

    private RadioGroup modeGroup;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        initializeViews();

        updateSettings();

        setListeners();

    }
    public void gotoGameActivity()
    {
        final Intent goToGameActivityIntent = new Intent(this,GameActivity.class);

        goToGameActivityIntent.putExtra(getString(R.string.key_for_saving_mode),mode);
        startActivity(goToGameActivityIntent);


    }
    public void updateSettings()
    {
        mode= PreferencesUtils.getModePreference(this);
        if(mode==1)
        {
            modeGroup.check(R.id.play_with_friend);
            Log.v("present mode"," "+mode);
        }
        else if(mode==2)
        {
            modeGroup.check(R.id.play_with_yuva);
        }
    }
    public void initializeViews()
    {
        // initialize all the views used in this class in this method
        modeGroup=(RadioGroup)findViewById(R.id.mode_group);
        playButton=(ImageView)findViewById(R.id.play_button);
    }

    public void setListeners()
    {
        // set the listeners to buttons and radio groups

        // listener for play button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                saveSettings();
                gotoGameActivity();
            }
        });

        // listener for mode_group
        modeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(checkedId==R.id.play_with_friend)
                {
                    PreferencesUtils.setModePreference(MainActivity.this,MODE_FRIEND);
                }
                else if(checkedId==R.id.play_with_yuva)
                {
                    PreferencesUtils.setModePreference(MainActivity.this,MODE_YUVA);
                }
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if(key==getString(R.string.key_for_saving_mode))
        {
            updateSettings();
        }
    }
}
