package com.kongsub.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

// 4-1. SensorEventListener : 센서 감지 클래스 상속
class MainActivity : AppCompatActivity(), SensorEventListener {

    // 1. sensorManager lazy 초기화
    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    // TiltView lazy 초기화
    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        // 화면이 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // 화면이 가로 모드로 고정되게끔 하기.
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)

        // TiltView 초기화
        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    // 2. 센서 사용 등록 (주로 센서는 onResume()메소드에서 등록한다.)
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, // 센서값을 받을 곳을 지정
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), // getDefaultSensor : 센서의 종류를 지정.
            SensorManager.SENSOR_DELAY_NORMAL) // 센서 값을 얼마나 자주 받을지.
    }

    // 4-2. 메소드 재정의 - 센서값이 변경되면 호출됨.
    override fun onSensorChanged(event: SensorEvent?) {
        // value[0] : x축 값 : 위로 기울이면 -10~0, 아래로 기울이면 0~10
        // value[1] : y축 값 : 위로 기울이면 -10~0, 오른쪽으로 기울이면 0~10
        // value[2] : z축 값 : 미사용
        event?.let {
            Log.d("MainActivity", "onSensorChanged: x : " + "${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")
        }

        if (event != null) {
            tiltView.onSensorEvent(event)
        }
    }
    // 4-3. 메소드 재정의 - 센서 정밀도가 변경되면 호출됨.
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    // 3. 센서 사용 해제
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}