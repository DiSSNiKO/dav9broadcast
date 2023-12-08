package com.example.dav8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tablay : TabLayout
    private lateinit var viuPager : ViewPager2
    private lateinit var fragPageAdap : FragmentPageAdapter
    private lateinit var recycleview : RecyclerView
    private lateinit var addables : ArrayList<AddableItemModel>
    lateinit var names : ArrayList<String>
    lateinit var mails : ArrayList<String>
    lateinit var nums : ArrayList<String>
    lateinit var locs : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tablay = findViewById(R.id.tablay)
        viuPager = findViewById(R.id.viupager)
        fragPageAdap = FragmentPageAdapter(supportFragmentManager,lifecycle)
        viuPager.adapter = fragPageAdap

        names = arrayListOf(
            "Johhny",
            "Donny",
            "Bonnie",
            "Goonie",
            "Nazibrola"
        )

        mails = arrayListOf(
            "john@dot.ge",
            "donquihote@gmail.com",
            "bonbon123@gmail.com",
            "degoon@yahoo.com",
            "kamazi@gmail.com"
        )
        nums = arrayListOf(
            "591150093",
            "591150093",
            "591150093",
            "591150093",
            "591150093"
        )
        locs = arrayListOf(
            "plato",
            "vake",
            "vake",
            "abasha",
            "chachubeti"
        )


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