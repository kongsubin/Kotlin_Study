package com.kongsub.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

// Service Class를 상속 받음.
class TorchService : Service() {

    /* Torch instance 를 얻는 방법
        1. onCreate() 콜백 메서드를 사용하는 방법.
        2. by lazy 를 사용하는 방법.
    */
    private val torch: Torch by lazy {
        Torch(this)
    }

    /* onStartCommand 반환값 3가지
        1. START_STICKY : null 인텐트로 다시 시작함. 명령을 실행하지는 않지만 무기한으로 실행 중이고, 작업을 기다리고 있는 미디어 플레이어와 비슷한 경우에 적합함.
        2. START_NOT_STICKY : 다시 시작하지 않음.
        3. START_REDELIVER_INTENT : 마지막 인텐트로 다시 시작. 능동적으로 수행중인 파일 다운로드와 같은 서비스와 적합함.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            // 앱에서 실행할 경우
            "on" -> {
                torch.flashOn()
            }
            "off" -> {
                torch.flashOff()
            }
        }
        //super.onStartCommand 호출시, 내부적으로 START_STICKY 를 반환함.
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

}