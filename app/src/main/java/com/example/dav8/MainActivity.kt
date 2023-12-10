package com.example.dav8

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tablay : TabLayout
    private lateinit var viuPager : ViewPager2
    private lateinit var fragPageAdap : FragmentPageAdapter
    lateinit var addables : ArrayList<AddableItemModel>
    lateinit var broadlogs : ArrayList<LogStr>
    lateinit var db : SQLiteHelper
    lateinit var cursor : Cursor
    val chargeReciever: ChargingReciever = ChargingReciever()


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = SQLiteHelper(this, null)
        registerReceiver(
            chargeReciever,
            IntentFilter(Intent.ACTION_POWER_CONNECTED),
        )
        registerReceiver(
            chargeReciever,
            IntentFilter(Intent.ACTION_POWER_DISCONNECTED),
        )
        registerReceiver(
            chargeReciever,
            IntentFilter(Intent.ACTION_SCREEN_ON),
        )
        registerReceiver(
            chargeReciever,
            IntentFilter(Intent.ACTION_SCREEN_OFF),
        )
        setContentView(R.layout.activity_main)
        tablay = findViewById(R.id.tablay)
        viuPager = findViewById(R.id.viupager)
        fragPageAdap = FragmentPageAdapter(supportFragmentManager,lifecycle)
        viuPager.adapter = fragPageAdap

        //get database data
        cursor = db.getData()!!
        addables = ArrayList()
        while(cursor.moveToNext()){
            addables.add(AddableItemModel(cursor.getString(cursor.getColumnIndex(SQLiteHelper.NAME_COl)),cursor.getString(cursor.getColumnIndex(SQLiteHelper.MAIL_COL)),cursor.getString(cursor.getColumnIndex(SQLiteHelper.NUM_COL)),cursor.getString(cursor.getColumnIndex(SQLiteHelper.LOC_COL))))
        }
        cursor.close()

        broadlogs = ArrayList()
        cursor = db.getBroadcastData()!!

        while(cursor.moveToNext()){
            broadlogs.add(LogStr(cursor.getString(cursor.getColumnIndex(SQLiteHelper.LOG_COL))))
        }
        cursor.close()
        // ^^

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

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(chargeReciever)
    }
}