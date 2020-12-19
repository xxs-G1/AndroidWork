package com.example.finallyapplication.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finallyapplication.R.layout.fragment_setting
import com.example.finallyapplication.Setting.HelpActivity
import com.example.finallyapplication.Setting.SettingActivity
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView_help.setOnClickListener {
            val intent = Intent(activity,HelpActivity::class.java)
            startActivity(intent)
        }
        textView_setting.setOnClickListener {
            val intent = Intent(activity,SettingActivity::class.java)
            startActivity(intent)
        }
    }


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_setting, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingFragment()
    }
}

