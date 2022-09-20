package com.kongsub.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val bluePaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    init {
        // 녹색 페인트
        bluePaint.color = Color.BLUE

        // 검은색 테두리 페인트
        blackPaint.style = Paint.Style.STROKE
    }

    // 중점의 좌표 구하기
    private var cX: Float = 0f
    private var cY: Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cX = w / 2f
        cY = h / 2f
    }
    // 중점의 좌표 구하기

    // 센서 값을 뷰에 반영
    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    fun onSensorEvent(event: SensorEvent) {
        // 화면이 가로이므로, x축과 y축 바꾸기.
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20

        // onDraw를 다시 부르는 메서드
        invalidate()
    }
    // 센서 값을 뷰에 반영

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 원 그리기
        canvas?.drawCircle(cX, cY, 100f, blackPaint)    // 바깥 원
        canvas?.drawCircle(xCoord + cX, yCoord + cY, 100f, bluePaint)     // 안쪽 원

        // 선 그리기 - 가운데 십자가
        canvas?.drawLine(cX - 20f, cY, cX + 20f, cY, blackPaint)
        canvas?.drawLine(cX, cY - 20f, cX, cY + 20f, blackPaint)
    }


}