package com.levojuk.stopwatch

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.levojuk.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var runnable : Runnable = Runnable{}
    var handler : Handler = Handler(Looper.getMainLooper())
    var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mediaPlayer = MediaPlayer.create(this, R.raw.tick)
    }
    fun start (view: View){

        number = 0
        runnable = object :Runnable{
            override fun run() {
                mediaPlayer.setVolume(1f,1f)
                mediaPlayer.start()
                number ++
                binding.textView.text = "Time : $number"
                handler.postDelayed(this,1000)
            }
        }
        handler.post(runnable)
        binding.imageView5.isEnabled = false

    }
    fun stop (view: View){
        mediaPlayer.pause()
        binding.imageView5.isEnabled = true
        number = 0
        binding.textView.text="Time : 0"
        handler.removeCallbacks(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}