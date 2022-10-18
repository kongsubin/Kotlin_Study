package com.kongsub.xylophone

import android.content.pm.ActivityInfo
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kongsub.xylophone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    // 8 : 8개를 동시에 재생할 수 있음.
    private val soundPool = SoundPool.Builder().setMaxStreams(8).build()

    // Pair 클래스 : 두 개의 연관된 객체를 저장
    private val sounds by lazy {
        listOf(
            Pair(binding.do1, R.raw.do1),
            Pair(binding.re, R.raw.re),
            Pair(binding.mi, R.raw.mi),
            Pair(binding.fa, R.raw.fa),
            Pair(binding.sol, R.raw.sol),
            Pair(binding.la, R.raw.la),
            Pair(binding.si, R.raw.si),
            Pair(binding.do2, R.raw.do2)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 화면이 가로모드로 고정되게 하기
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 동적으로 클릭이벤트 구현 - 요소를 하나씩 꺼내어 tune 메서드 호출
        sounds.forEach{ tune(it) }
    }

    private fun tune(pitch: Pair<TextView, Int>){
        // 1. 음원의 ID값 얻기
        val soundId = soundPool.load(this, pitch.second, 1)
        // 2. 전달받은 객체의 첫번째 프로퍼티인텍스트 뷰를 얻어 클릭했을 때 음원 재생
        pitch.first.setOnClickListener {
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }

    // soundPool 해제 처리
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}