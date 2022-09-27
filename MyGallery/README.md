# 안드로이드 저장소 
### 내부 저장소
OS가 설치된 영역으로, 유저가 접근할 수 없는 시스템 영역이다. 
앱이 사용하는 정보와 데이터베이스가 저장된다. 

# 안드로이드 권한 
### 정상 권한
> 안드로이드 매니페스트 파일에 권한을 추가하면 됨.
> ex) 인터넷 엑세스 권한 

### 위험 권한
> 안드로이드 매니페스트 뿐만 아니라, 실행 중에 사용자에게 권한을 요청해야함. 
> ex) 외부 저장소 읽기 권한 (갤러리, 카메라, 위치 등)

### 외부 저장소
컴퓨터에 기기를 연결하면 저장소로 인식되며 유저가 사용하는 영역이다. 
사진과 동영상은 외부 저장소에 저장된다. 

# 프로바이더 사용
### 콘텐츠 프로바이더란? 
> 앱의 데이터 접근을 다른 앱에 허용하는 컴포넌트 

### 프로바이더를 이용하여 사진 가져오기
1. 사진 데이터는 외부에 있으므로, 외부 저장소 읽기 권한을 앱에 부여함.
    ~~~kotlin
    <manifest 
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        package="com.kongsub.mygallery">
            
        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

        <application
        ...
        </application>
    </manifest>
    ~~~
    
2. 외부 저장소 읽기 권한은 위험 권한으로 사용자에게 권한을 허용하도록 해야함.
    > ContextCompat.checkSelfPermission() 메서드를 사용하여 권한 여부를 확인함. 
    > 권한이 있는경우, PERMISSION_GRANTED 반환. 없는 경우, PERMISSION_DENIED가 반환됨.
    ~~~kotlin
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
        
        ...

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
        // 권한이 이미 허용됨 - 외부 저장소 사진을 URI로 가져오는 메소드 
        getAllPhotos()
        
        ...
    }
    ~~~

3. contentResolver 객체를 이용하여 데이터를 Cursor 객체로 가져옴.
    ~~~kotlin
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
    ~~~

#### 참고 
Cursor : Cursor객체의 사용이 끝나면, close()로 반드시 닫아야함. 닫지 않을 경우 메모리 누수가 일어남. 
<br>
URI : 안드로이드 기기 내부의 데이터를 표현하는 방법 



# Coil 라이브러리
> 이미지를 표시하는 100% 코틀린 코드로 작성된 라이브러리.

1. Coil 라이브러리 의존성 추가
    ~~~kotlin
    dependencies {
        //Coil
        implementation 'io.coil-kt:coil:1.3.2'
    }
    ~~~


2. 사용
    ~~~kotlin
    import coil.load

    ...

        imageView.load(bitmap)
    ~~~
