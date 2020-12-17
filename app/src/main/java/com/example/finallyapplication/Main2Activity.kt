package com.example.finallyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main2Activity : AppCompatActivity() {

    val musicListFragment = MusicListFragment.newInstance()
    val musicFragment = MusicFragment.newInstance()
    val settingFragment = SettingFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,musicFragment)
                .commit()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNV)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.musicList ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,musicListFragment)
                        .commit()
                R.id.music ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,musicListFragment)
                        .commit()
                R.id.setting ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,settingFragment)
                        .commit()
            }
            true
        }
    }
}
