## Thread

- Main Thread : UI를 조작하는 thread
- Worker Thread : 오래 걸리는 작업을 보이지 않는 곳에서 처리하는 thread

> timer는 워커스레드에서 동작하는 코드이므로, runOnUiThread() 메서드를 사용하여 UI를 조작한다. 
~~~kotlin
timer(period = 1000){
    // 오래 걸리는 작업 
    runOnUiThread {
        // UI 조작 
    }
}
~~~
