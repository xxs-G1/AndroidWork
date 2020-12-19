package com.example.finallyapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import com.example.finallyapplication.R
import com.example.finallyapplication.entity.Music

class MusicAdapter(private val musicList: List<Music>, private val context: Context) :
    BaseAdapter() {
    override fun getCount(): Int {
        return musicList.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view1: View?, viewGroup: ViewGroup): View {
        var view: View? = null
        var viewHolder: ViewHolder? = null
        if (view1 == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_music, null)
            viewHolder = ViewHolder()
            viewHolder.titletv = view!!.findViewById(R.id.textView_mn)
            viewHolder.artisttv = view.findViewById(R.id.textView_count)
            viewHolder.albumImageView = view.findViewById(R.id.imageView)

            view.tag = viewHolder
        } else {
            view = view1
            viewHolder = view.tag as ViewHolder
        }
        val music = musicList[i]
        viewHolder.titletv!!.text = music.title
        viewHolder.artisttv!!.text = music.artist + " - " + music.album
        viewHolder.albumImageView!!.setImageBitmap(music.albumbmp)
        return view
    }

    internal inner class ViewHolder {
        var titletv: TextView? = null
        var artisttv: TextView? = null
        var albumImageView: ImageView? = null
    }
}
