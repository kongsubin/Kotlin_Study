# 1. WebView 설정하기
1. 인터넷 권한 설정
    - Manifest 파일에 인터넷 사용 권한 추가. 
        ~~~xml
        <uses-permission android:name="android.permission.INTERNET"/>
        <application
                ...
                android:usesCleartextTraffic="true">
                ...
        ~~~

2. 웹뷰에 웹 페이지 표시하기.
3. 키보드의 검색 버튼 동작 정의하기. 
4. 뒤로가기 동작 재정의.

# 2. 옵션 메뉴 사용하기 
> 옵션 메뉴 : 상단 툴바에 표시되는 메뉴를 뜻함.

1. 메뉴 리소스 준비
2. onCreateOptionsMenu() 메서드 재정의 후, true 반환
3. onOptionsSelected() 메서드를 재정의하여 메뉴 아이템 선택시, 분기 처리.  

#### 메뉴 아이템을 툴바 밖으로 노출시키는 방법
##### showAsAction 옵션 추가 
- never : 밖으로 절대 노출 x
- ifRoom : 툴바에 여유가 있는 경우 노출
- always : 항상 노축
- withText : 글자와 아이콘을 함께 출력
- collapseActionView : 액션 뷰와 결합하여 축소되는 메뉴 만들기



# 3. 컨텍스트 메뉴 사용하기
> 컨텍스트 메뉴 : 특정 뷰를 길게 눌렀을 때 보여지는 메뉴. 
1. 메뉴 리소스 생성
2. onCreateContextMenu() 메서드를 재정의 후 메뉴를 붙임. 
3. onContextItemSelected() 메서드를 재정의 후 메뉴 이벤트 처리. 
4. registerForContextMenu(View view) 메서드에 컨텍스트 메뉴에 표시할 뷰 지정. 



# 4. Intent 사용하기
## Intent 종류.
1. 문자열 데이터 보내기
    ~~~kotlin
    val intent = Intent(Intent.ACTION_SEND)
    intent.apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "Hello Kong")
        var chooser = Intent.createChooser(intent, null)
        if(intent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        }

    }
    ~~~
2. 웹 브라우저 띄우기
    ~~~kotlin
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("https://kongsubin.github.io/")
    if(intent.resolveActivity(packageManager) != null){
        startActivity(intent)
    }
    ~~~
3. 전화걸기 
    ~~~kotlin
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:010-1234-5678")
    if(intent.resolveActivity(packageManager) != null){
        startActivity(intent)
    }
    ~~~
4. [공식문서](https://developer.android.com/guide/components/intents-common)

> resolveActivity : 실행할 엑티비티가 존재하는지 여부를 확인 


# 5. 패키지 가시성
> 패키지 가시성 : 안드로이드 11 이상을 타겟팅하는 앱에서, 개인정보에 민감한 인텐트의 사용이 제한됨.  

#### example
> 전화 및 이베일 기능이 동작하기 위해서는 앱에서 해당 인텐트를 사용한다는 가시성 설정을 해야함. 

    ~~~xml
    <queries>
        <intent>
            <action android:name="android.intent.action.DIAL"></action>
        </intent>
        <intent>
            <action android:name="android.intent.action.SENDTO"></action>
            <data android:scheme="*"></data>
        </intent>
    </queries>
    ~~~
