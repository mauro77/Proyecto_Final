package com.cazz.proyectofinal;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener  {

    private ViewPager mViewPager;
    private FragmentMap fragMap= new FragmentMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab().setText("Current Location").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Bus Routes").setTabListener(this);
        actionBar.addTab(tab);

    }

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new FragmentMap();
                case 1:
                    return new FragmentRoutes();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        getSupportActionBar().setSelectedNavigationItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    public void bZona(View v) {
        switch (v.getId()) {
            case R.id.zona1:
                Intent i = new Intent(this, Base.class).putExtra("TEXT", 1);
                startActivity(i);
                break;
            case R.id.zona2:
                Intent j = new Intent(this, Base.class).putExtra("TEXT", 2);
                startActivity(j);
                break;
            case R.id.zona3:
                Intent k = new Intent(this, Base.class).putExtra("TEXT", 3);
                startActivity(k);
                break;
            case R.id.zona4:
                Intent l = new Intent(this, Base.class).putExtra("TEXT", 4);
                startActivity(l);
                break;
            case R.id.locationZona:

                    if (fragMap.getMinIndex()==0){
                        Intent i2 = new Intent(this, Base.class).putExtra("TEXT", 1);
                        startActivity(i2);
                    }
                    else if(fragMap.getMinIndex()==1){
                        Intent j2 = new Intent(this, Base.class).putExtra("TEXT", 2);
                        startActivity(j2);
                    }
                    else if(fragMap.getMinIndex()==2) {
                        Intent k2 = new Intent(this, Base.class).putExtra("TEXT", 3);
                        startActivity(k2);
                    }
                    else if(fragMap.getMinIndex()==3){
                        Intent k2 = new Intent(this, Base.class).putExtra("TEXT", 4);
                        startActivity(k2);
                         }

                break;
            default:
                break;

        }
    }


}