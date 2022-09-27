# 프래그먼트
> 프래그먼트란 사용자 인터페이스의 모음. 여러개의 프래그먼트를 조합해 액티비티 하나를 구성할 수도 있고, 프래그먼트는 재사용도 가능함. 

[프래그먼트 공식 문서](https://developer.android.com/guide/fragments)

![프래그먼트 전체생명주기](https://developer.android.com/static/images/guide/fragments/fragment-view-lifecycle.png)

- onAttach() : 액티비티에 붙을 때 호출됨. 엑티비티의 참조 사용 가능. 
<br>
- onCreate() : 프래그먼트 생성시 인자가 넘어와 변수에 담기는 부분
<br>
- onCreateView() : 주로 프래그먼트에 표시할 뷰를 레이아웃 파일로부터 읽어오는 부분
<br>
- onViewCreated() : 생명주기에 포함되지는 않지만 뷰가 완성된 이후의 이벤트 처리를 수행하는 부분
<br>
- onStart() : 프래그먼트가 사용자게에 보여질때 호출됨. 
<br>

# 안드로이드 4대 컴포넌트
1. 액티비티 : 화면구성 역할
2. 콘텐츠 프로바이더 : 데이터베이스, 파일, 네트워크의 데이터를 다른 앱에 공유하는 역할.
3. 브로드캐스트 리시버 : 앱이나 기기가 발송하는 방송을 수신함.
4. 서비스 : 화면이 없으며 백그라운드 작업시 용이함. 
