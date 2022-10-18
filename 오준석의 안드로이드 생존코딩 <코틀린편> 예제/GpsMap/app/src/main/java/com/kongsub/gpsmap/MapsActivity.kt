package com.kongsub.gpsmap

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.kongsub.gpsmap.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    // 1. 위치 정보를 얻기위한 객체 ( 위치 정보를 주기적으로 얻기 위함)
    private val fusedLocationProviderClient by lazy {
        FusedLocationProviderClient(this)
    }

    // 2. 위치 요청 정보 (요청에 대한 세부정보 설정)
    // 10초마다 위치 정보 갱신, 다른 앱에서 위치를 갱신했다면 5초 단위로 값을 가져와 배터리를 절약함.
    private val locationRequest by lazy {
        LocationRequest.create().apply {
            // 정확도를 나타냄 - GPS 우선
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            /* 업데이트 인터벌
            위치 정보가 없는 경우, 업데이트 안함
            상황에 따라 짧아질 수 있고 정확하지 않음
            다른 앱에서 짧은 인터벌로 위치 정보를 요청하면 짧아짐
             */
            interval = 10000 // 위치를 갱신하는데 필요한 시간 (밀리초단위)
            // 정확함. 이것보다 짧은 업데이트는 하지 않음
            fastestInterval = 5000 // 다른앱에서 위치를 갱신했을 때, 그 정보를 밀리초단위로 입력함.
        }
    }

    // 3. 위치 정보를 얻으면 해야할 행동이 정의된 콜백 객체
    private val locationCallback = MyLocationCallBack()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면이 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // 세로 모드로 화면 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    // 위치 정보 요청을 수행
    override fun onResume() {
        super.onResume()
        checkPermission(
            cancel = {
                // 위치 정보가 필요한 이유 다이얼로그 표시
                showPermissionInfoDialog()
            },
            ok = {
                // 현재 위치를 주기적으로 요청 (권한이 필요한 부분)
                addLocationListener()
            }
        )

    }

    @SuppressLint("MissingPermission")
    private fun addLocationListener() {
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    // PolyLine 옵션 - 지도위 그림 그리기
    private val polylineOptions = PolylineOptions().width(5f).color(Color.RED)

    inner class MyLocationCallBack : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            val location = locationResult?.lastLocation

            // 기기의 GPS 가 활성화 되지 않은 경우, location 값이 null 일 수도 있음.
            location?.run {
                // 17 level 로 확대하여 현재 위치로 카메라 이동
                val latLng = LatLng(latitude, longitude)
                mMap.animateCamera((CameraUpdateFactory.newLatLngZoom(latLng, 17f)))

                Log.d("MapsActivity", "위도 : $latitude, 경도 : $longitude")

                // PolyLine에 자표 추가
                polylineOptions.add(latLng)

                // 선 그리기
                mMap.addPolyline(polylineOptions)
            }
        }
    }

    // 권한 요청 처리
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted ->
        if (isGranted){
            // 권한이 허락됨 - 위치 정보 갱신
            addLocationListener()
        } else {
            // 권한 거부
            Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // 실행 중 권한 요청 메서드
    private fun checkPermission(cancel: () -> Unit, ok: () -> Unit) {
        // 위치 권한이 없는지 검사
        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED){
            // 권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )){
                // 이전에 권한을 한 번 거부한 적이 있는 경우
                cancel()
            } else {
                // 권한 요청
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            return
        }
        ok()
    }

    // 권한이 필요한 이유를 설명하는 다이얼로그 표시 메서드
    private fun showPermissionInfoDialog() {
        // 다이얼로그에 권한이 필요한 이유를 설명
        AlertDialog.Builder(this).apply {
            setTitle("권한이 필요한 이유")
            setMessage("지도에 위치를 표시하려면 위치 정보 권한이 필요합니다.")
            setPositiveButton("권한 요청") {_, _ ->
                // 권한 요청
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            setNegativeButton("거부", null)
        }.show()
    }

    // 위치 정보 요청 삭제
    override fun onPause() {
        super.onPause()
        // LocationCallback 객체를 전달하여 주기적인 위치 정보 갱신 요청을 삭제함.
        removeLocationListener()
    }

    private fun removeLocationListener() {
        // 현재 위치 요청을 삭제
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }



}