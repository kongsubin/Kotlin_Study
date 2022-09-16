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
