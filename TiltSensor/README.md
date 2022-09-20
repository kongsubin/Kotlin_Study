# 엑티비티 생명주기 
> 콜백 메서드 : 특정 타이밍에 호출되는 메서드 ex) onCreate() 
[생명주기 공식 문서](https://developer.android.com/guide/components/activities/activity-lifecycle)

![전체생명주기](https://developer.android.com/guide/components/images/activity_lifecycle.png)

### 엑티비티 시작
엑티비티 시작 -> onCreate() -> onStart() -> onResume() -> 엑티비티 실행 중

- onCreate() : 엑티비티 시작 시 가장 먼저 호출됨. 
- onStart() : onCreate() 다음으로 실행됨. 
- onResume() : onStart() 다음으로 실행됨. 

### 엑티비티 종료 
엑티비티 실행 중 -> (다른 엑티비티 샐행됨) -> onPause() -> (엑티비티가 화면에서 가려짐) -> onStop() -> (정상 종료 또는 강제 종료) -> onDestroy() -> 엑티비티 종료 

- onPause() : 엑티비티가 화면에 보이지 않게 되는 순간 실행됨. 
- onStop() : 엑티비티가 화면에 완전히 보이지 않게 되는 순간 실행됨.
- onDestroy() : 엑티비티가 종료될 때 호출됨. 

### 엑티비티 재개 

- 다른 앱 실행 or 홈키 및 전원버튼을 눌러 화면을 끄는 경우 : onPause(), onStop()까지 호출됨. 
- 다시 실행하는 경우 : onRestart() -> onStart() -> onResume() 순으로 호출됨. 


# 센서 사용하기
1. SensorManager 인스턴스 가져오기.
    ~~~kotlin
    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    ~~~

2. onResume() 메서드에서 registerListener() 메서드로 센서의 감지 등록. 
    ~~~kotlin
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }
    ~~~
    
3. onPause() 메서드에 unregisterListener() 메서드로 센서의 감지를 해제. 
    ~~~kotlin
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
    ~~~
    
4. SensorEventListener 상속 후 메서드 정의  
    ~~~kotlin
    class MainActivity : AppCompatActivity(), SensorEventListener {
    ...

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
        
    ...
    }
    ~~~
    
    
# 커스텀 뷰 만들기
1. View 클래스를 상속하는 클래스 생성.
    ~~~kotlin
    class TiltView(context: Context?) : View(context) {
     ...
    }
    ~~~
    
2. 필요한 메서드 override
example
    ~~~kotlin
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        ...
    }
    ~~~
    
3. Activity에 Custom View 세팅
    ~~~kotlin
    private lateinit var tiltView: TiltView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TiltView 초기화
        tiltView = TiltView(this)
        setContentView(tiltView)
    }
    ~~~
