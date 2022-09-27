package com.kongsub.flashlight

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

        val torch = Torch(this)

        // buttonView - 상태가 변경된 Switch 객체 자신
        // isChecked - On/Off 상태를 Boolean 값으로 알려줌
        binding.flashSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                // on 일 때의 동작
                torch.flashOn()
            } else {
                // off 일 떄의 동작
                torch.flashOff()
            }
        }
    }
}