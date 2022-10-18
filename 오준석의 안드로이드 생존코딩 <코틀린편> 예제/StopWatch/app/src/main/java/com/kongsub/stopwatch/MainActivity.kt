package com.kongsub.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kongsub.stopwatch.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0;
    private var timerTask: Timer? = null
    private var isRunning = false
    private var lap = 1

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            isRunning = !isRunning

            if(isRunning){
                start()
            } else {
                pause()
            }
        }

        binding.lapButton.setOnClickListener {
            recordLapTime()
        }

        binding.resetFab.setOnClickListener {
            reset()
        }
    }

    private fun start() {
        binding.fab.setImageResource(R.drawable.ic_baseline_pause_24)
        timerTask = timer(period = 10){
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                binding.secTextView.text = "$sec"
                binding.milliTextView.text = "$milli"
            }
        }
    }

    private fun pause(){
        binding.fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel()
    }

    private fun recordLapTime(){
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAP : ${lapTime / 100}.${lapTime % 100}"

        // 동적으로 UI 추가
        binding.lapLayout.addView(textView, 0) // index-0 : 맨 위에 추가
        lap++
    }

    private fun reset() {
        timerTask?.cancel()

        // 모든 변수 초기화
        time = 0
        isRunning = false

        binding.fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        binding.secTextView.text = "0"
        binding.milliTextView.text = "00"

        //모든 랩타임 제거
        binding.lapLayout.removeAllViews()
        lap = 1
    }


}