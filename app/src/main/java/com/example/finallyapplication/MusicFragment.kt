package com.example.finallyapplication

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.fragment_music.*
import java.io.IOException
import kotlin.concurrent.thread

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MusicFragment : Fragment() {

    val TAG = "MusicActivity"
    val mediaPlayer = MediaPlayer()
    val musicList = mutableListOf<String>()
    val musicNameList = mutableListOf<String>()
    var current = 0
    var isPause = false

    val Channel_ID = "my channel"
    val Notification_ID = 1

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer.setOnPreparedListener {
            it.start()
        }
        mediaPlayer.setOnCompletionListener {
            current++
            if (current >= musicList.size) {
                current = 0
            }
            val notificationManager =
                activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder: Notification.Builder
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    Channel_ID, "test",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(notificationChannel)
                builder = Notification.Builder(activity, Channel_ID)
            } else {
                builder = Notification.Builder(activity)
            }
            val intent = Intent(activity, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                activity,
                1,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            val notification = builder.setSmallIcon(R.drawable.ic_queue_music_black_24dp)
                .setContentTitle("下一首歌")
                .setContentText(musicNameList[current])
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
            notificationManager.notify(Notification_ID, notification)
            play()
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        } else {
            getMusicList()
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        thread {
            while (true) {
                Thread.sleep(1000)
                activity?.runOnUiThread {
                    seekBar.max = mediaPlayer.duration
                    seekBar.progress = mediaPlayer.currentPosition
                }
            }
        }
    }

        fun onPlay(v: View) {
            play()
        }

        fun onStop(v: View) {
            mediaPlayer.stop()
        }

        fun onPrev(v: View) {
            current--
            if (current < 0) {
                current = musicList.size - 1
            }
            play()
        }

        fun onNext(v: View) {
            current++
            if (current >= musicList.size) {
                current = 0
            }
            play()
        }

        fun play() {
            if (musicList.size == 0) return
            val path = musicList[current]
            mediaPlayer.reset()
            try {
                mediaPlayer.setDataSource(path)
                mediaPlayer.prepareAsync()
                textView_count.text = musicNameList[current]
                textView_mn.text = "${current + 1}/${musicList.size}"
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            getMusicList()
        }

         private fun getMusicList() {
            val cursor = activity?.contentResolver?.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
            )
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val musicPath =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    musicList.add(musicPath)
                    val musicName =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    musicNameList.add(musicName)
                    Log.d(TAG, "getMusicList:$musicPath name:$musicName")
                }
                cursor.close()
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            mediaPlayer.release()
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
