package com.example.finallyapplication.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.finallyapplication.R

const val MyReceiverAction = "com.example.finallyapplication.musicplayer.newmusic"

class MusicFragment : Fragment() {

    val TAG = "MusicActivity"

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun onPlay(v: View) {

    }

    fun onStop(v: View) {

    }

    fun onPrev(v: View) {

    }

    fun onNext(v: View) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MusicFragment()
    }
}
