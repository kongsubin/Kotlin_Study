package com.kongsub.mygallery

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.kongsub.mygallery.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // 권한 요청에 대한 결과 처리
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if(isGranted){
            // 권한 허용됨
            getAllPhotos()
        } else {
            // 권한 거부
            Toast.makeText(this, "권한 거부 됨", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 권한이 부여되었는지 확인.
        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
        ){
            // 이전에 권한이 부여되지 않았음. shouldShowRequestPermissionRationale : 사용자가 이전에 권한 요청을 거부했는지의 여부
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            )){
                AlertDialog.Builder(this).apply {
                    setTitle("권한이 필요한 이유")
                    setMessage("사진 정보를 얻으려면 외부 저장소 권한이 필요합니다.")
                    setPositiveButton("권한 요청") {_, _ ->
                        // 권한 요청
                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                    setNegativeButton("거부", null)
                }.show()
            } else {
                // 권한 요청
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            return
        }
        // 권한이 이미 허용됨
        getAllPhotos()
    }

    private fun getAllPhotos() {
        val uris = mutableListOf<Uri>()     // mutableListOf : 수정가능한 리스트

        //모든 사진 정보 가져오기
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,    // 어떤 데이터를 가져오는지를 URL 형태로 지정함. EXTERNAL_CONTENT_URI: 외부 저장소를 가르키는 URI
            null,       // 어떤 항목의 데이터를 가져올지 정함. null : 모든 항목을 가져온다.
            null,        // 데이터를 가져올 조건을 지정할 수 있음. null : 전체 데이터를 가져온다.
            null,     // 3번째 인자와 조건을 지정할 때 사용함. 사용하지 않을 경우 null
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"    // 정렬 방법을 지정함. (사진이 찍힌 날짜의 내림차순 정렬)
        )?.use{ cursor ->   // use 확장 함수 사용시, 자동으로 close() 메서드를 호출함.
            while(cursor.moveToNext()){     // moveToNext() : 다음 결과를 true 로 반환한다.

                // 사진은 고유한 URI 를 가지고 있으므로 URI를 통해 사진을 불러올 수 있음.
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))

                //Media 를 가리키는 URI
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                // 사진의 Uri 들 리스트에 담기.
                uris.add(contentUri)
            }
        }
        Log.d("MainActivity", "getAllPhotos: $uris")
    }
}