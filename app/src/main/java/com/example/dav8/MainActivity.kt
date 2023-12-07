package com.example.dav8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tablay : TabLayout
    private lateinit var viuPager : ViewPager2
    private lateinit var fragPageAdap : FragmentPageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tablay = findViewById(R.id.tablay)
        viuPager = findViewById(R.id.viupager)
        fragPageAdap = FragmentPageAdapter(supportFragmentManager,lifecycle)
        viuPager.adapter = fragPageAdap

        tablay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!=null){
                    viuPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        viuPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                tablay.selectTab(tablay.getTabAt(position))
            }
        })
    }
}