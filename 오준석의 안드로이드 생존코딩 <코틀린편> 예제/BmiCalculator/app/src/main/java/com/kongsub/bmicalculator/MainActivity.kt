package com.kongsub.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.kongsub.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //view binding 객체 얻기
    private val binding by lazy {
        /*ActivityMainBinding : activity_main.xml 이름을 참고하여 뷰 바인딩 설정에 의해 자동으로 만들어진 객체
        ActivityMainBinding 을 통해 activity_main.xml 파일에 정의한 뷰에 접근 할 수 있음.
         */
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()

        binding.resultButton.setOnClickListener{
            if(binding.weightEditText.text.isNotBlank() && binding.heightEditText.text.isNotBlank()){
                saveData(
                    binding.heightEditText.text.toString().toFloat(),
                    binding.weightEditText.text.toString().toFloat(),
                )
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("weight", binding.weightEditText.text.toString().toFloat())
                    putExtra("height", binding.heightEditText.text.toString().toFloat())
                }
                startActivity(intent)
            }
        }
    }

    //데이터 저장 메소드
    private fun saveData(height: Float, weight: Float){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putFloat("KEY_HEIGHT", height)
            .putFloat("KEY_WEIGHT", weight)
            .apply()
    }

    //데이터 불러오기 메소드
    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getFloat("KEY_HEIGHT", 0f)
        val weight = pref.getFloat("KEY_WEIGHT", 0f)

        if(height != 0f && weight != 0f) {
            binding.heightEditText.setText(height.toString())
            binding.weightEditText.setText(weight.toString())
        }
    }
}