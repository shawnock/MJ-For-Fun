package com.example.shawnocked.michaeljacksonlibrary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
 * Plain Main Activity only contains one fragment-startFragment
 */
public class MainActivity extends AppCompatActivity {

    private static final String START_FRAGMENT = "task_fragment";
    private StartFragment mStartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        mStartFragment = (StartFragment) fm.findFragmentByTag(START_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change
        if(mStartFragment == null){
            mStartFragment = new StartFragment();
            fm.beginTransaction().add(mStartFragment,START_FRAGMENT).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
