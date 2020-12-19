package com.example.finallyapplication

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.finallyapplication.fragment.MusicFragment
import com.example.finallyapplication.fragment.MusicListFragment
import com.example.finallyapplication.fragment.SettingFragment
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
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
                R.id.my ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,settingFragment)
                        .commit()
            }
            true
        }
    }
}
