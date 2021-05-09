package com.example.e_study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
    // To check next time when the next user run the app
public class Introduction extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPageAdapter introViewPageAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // when this activity is about to be launch  check if its opened before or not
        if (restoPrefData()){
            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
        setContentView(R.layout.activity_introduction);

        // hide the action bar
        getSupportActionBar().hide();

        //inf view
        btnNext = findViewById(R.id.btn_Next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button);

        // fill list screen
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("SLIIT e-Study ","This is only for the SLIIT Student and academic staff By using this \n" +
                "application students can download newest past papers directly and lectures can upload and check students papers",R.drawable.img2));
        mList.add(new ScreenItem("Teams & Condition","1.Student Should take all the responsibility for thr answers they have \n" +
                "2.Lectures Should review every paper within 2 weeks from the submission \n" +
                "3.Lectures can reject any paper due to any guideline limitation \n" +
                "4.Student Can make a appeal to each reviewed paper within 2 days",R.drawable.img4));
        mList.add(new ScreenItem("Easy Payment","you can pay by online",R.drawable.img3));

        // setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPageAdapter = new IntroViewPageAdapter(this,mList);
        screenPager.setAdapter(introViewPageAdapter);

        // setup tab layout with viewPager
        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == mList.size() -1){ // when we reach to the last screen
                    // TODO : show the GetStarted button and hide the indicator and the next button
                    loaddLastScreen();
                }

            }
        });

        // tab layout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    // Get started button click listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open main activity
                Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainActivity);

                savePrefsData();
                finish();

            }
        });

    }

        private boolean restoPrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityopnendBefore = pref.getBoolean("isIntroOpnend",false);
        return isIntroActivityopnendBefore;
        }

        private void savePrefsData() {

            SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isIntroOpnend",true);
            editor.commit();
        }

        // show the GetStarted button and hide the indicator and the next button
    private void loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        //TODO : ADD An Animation the Get Started button

        //setup animation
        btnGetStarted.setAnimation(btnAnim);


    }
}