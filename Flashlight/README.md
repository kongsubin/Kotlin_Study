# 서비스
> 4대 컴포넌트 중 하나로 화면이 없고 백그라운드에서 수행하는 작업을 담당하는 컴포넌트 


### 서비스 사용 전 플래시 기능 제어 
> 액티비티는 단순히 플래시를 켜고 끄는 인터페이스만 제공.

액티비티 -> Torch 클래스(플래시 기능)

### 서비스 사용 후 플래시 기능 제어 
> 플래시를 켜는 기능에는 화면이 필요하지 않으므로 서비스에서 플래시를 켜고 끄도록 하고 엑티비티에서 호출하는게 일반적.

액티비티, 앱 위젯 -> 서비스 -> Torch 클래스 

<br>
<br>
# 서비스의 생명주기 
[서비스 생명주기 공식 문서](https://developer.android.com/guide/components/services#Lifecycle)
[서비스 생명주기 이미지](https://developer.android.com/static/images/service_lifecycle.png)
- onCreate() : 서비스가 생성될 때 호출되는 콜백 메서드. 초기화를 수행함. 
- onStartCommand() : 서비스가 액티비티와 같은 다른 컴포넌트로부터 startService() 메서드로 호출되면 불리는 콜백 메소드. 실행할 작업을 작성하는 부분임. 
- onDestroy() : 서비스 내부에서 stopSelf()를 호출하거나 외부에서 stopService()로 서비스를 종료하면 호출됨. 

<br>
<br>

# 앱 위젯
> 런처에 배치하여 빠르게 앱 기능을 제공하는 컴포넌트 

# 앱 위젯 사용하기
### 앱 위젯 추가  
File -> New -> Widget -> App Widget 

옵션 값
- Placement : 위젯을 어디에 배치하는지 설정하는 옵션.
  - Home-screen : 홈 화면에 배치.
  - Keyguard : 잠금화면에 배치.
  - Both : 홈 화면과 잠금화면에 배치. 

- Resizable : 위젯 크기 변경 옵션.
  - Both : 가로와 세로 크기 변경 가능.
  - Horizontally : 가로로만 크기 변경 가능.
  - Vertically : 세로로만 크기 변경 가능.
  - None : 크기 변경 불가.

- Minimum Width : 가로크기를 1~4 중 설정.
- Minimum Height : 세로 크기를 1~4 중 설정.
- Configuration Screen : 위젯의 환경설정 액티비티를 생성. 

### 생성된 파일
- NewAppWidget.kt : 앱 위젯 클릭시 동작 설정
- res/layout/new_app_widget.xml : 앱 위젯의 레이아웃을 정의한 파일
- res/xml/new_app_widget_info.xml : 앱 위젯 설정 파일
