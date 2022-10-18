package com.kongsub.flashlight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kongsub.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* val torch = Torch(this) */

        // buttonView - 상태가 변경된 Switch 객체 자신
        // isChecked - On/Off 상태를 Boolean 값으로 알려줌
        binding.flashSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                /* torch.flashOn() */
                // torch 인스턴스를 직접 접근하기보단, 서비스를 통해 접근.
                startService(Intent(this, TorchService::class.java).apply{
                    action = "on"
                })
            } else {
                /* torch.flashOff() */
                startService(Intent(this, TorchService::class.java).apply{
                    action = "off"
                })
            }
        }
    }
}