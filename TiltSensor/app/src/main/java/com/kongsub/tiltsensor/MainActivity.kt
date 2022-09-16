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

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 화면이 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // 화면이 가로 모드로 고정되게끔 하기.
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 센서 사용 등록 (주로 센서는 onResume()메소드에서 등록한다.)
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    // 센서값이 변경되면 호출됨.
    override fun onSensorChanged(event: SensorEvent?) {
        // value[0] : x축 값 : 위로 기울이면 -10~0, 아래로 기울이면 0~10
        // value[1] : y축 값 : 위로 기울이면 -10~0, 오른쪽으로 기울이면 0~10
        // value[2] : z축 값 : 미사용
        event?.let {
            Log.d("MainActivity", "onSensorChanged: x : " + "${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")
        }

    }
    // 센서 정밀도가 변경되면 호출됨.
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    // 센서 사용 해제
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}