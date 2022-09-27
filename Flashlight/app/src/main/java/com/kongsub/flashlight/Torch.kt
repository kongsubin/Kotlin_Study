package com.kongsub.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

// 생성자로 context 를 받음.
class Torch(context: Context) {
    // 카메라를 on off 시, 필요한 카메라 ID - 기기마다 고유한 ID가 부여됨.
    private var cameraId: String? = null

    // CameraManager 객체를 얻기 위해서는 context 가 필요함.
    // context.getSystemService() 메서드는 안드로이드 시스템에서 제공하는 각종 서비스를 관리하는 매니저 클래스를 생성함.
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    // 카메라 ID 를 클래스 초기화 때 받음.
    init {
        cameraId = getCameraId()
    }

    fun flashOn() {
        cameraId?.let {
            cameraManager.setTorchMode(it, true)
        }
    }

    // 카메라 ID 를 얻어오는 메소드. 카메라가 없는 경우 null 이므로 반환값을 String?으로 지정.
    private fun getCameraId(): String? {
        // 기기가 가지고 있는 모든 카메라 정보 가져옴.
        val cameraIds = cameraManager.cameraIdList

        // 목록을 순회하며 필요한 객체를 얻어와 사용함.
        for (id in cameraIds){
            val info = cameraManager.getCameraCharacteristics(id)
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)

            // 플래시가 사용이 가능하고 카메라가 기기 뒷면을 향하고 있는 카메라의 ID 를 얻어옴.
            if(flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id
            }
        }
        return null
    }
}