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

2. 웹뷰에 웹 페이지 표시하기
3. 키보드의 검색 버튼 동작 정의하기. 
4. 뒤로가기 동작 재정의

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
3. onContextItemSelected() 메서드를 재정의 후 메뉴 분기 처리. 
4. registerForContextMenu(View view) 메서드에 컨텍스트 메뉴에 표시할 뷰 지정. 
