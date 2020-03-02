package com.example.tab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//tab kısmı
        toolbar=(Toolbar) findViewById(R.id.myToolbar);
        tabLayout=(TabLayout) findViewById(R.id.tablayout);
        viewPager=(ViewPager)findViewById(R.id.myViewPager);
        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
//tab bitis

    }

    private void setupViewPager(ViewPager viewPager){ //tab icin
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(new ChatFragment(),"CHATS");
        viewPagerAdapter.addFragment(new CameraFragment(),"CAMERA");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
