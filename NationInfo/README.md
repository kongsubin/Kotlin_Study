# Extensions 사용하기
extentions이란 xml에 정의된 뷰를 findViewById 함수를 사용하지 않고 id를 사용하여 바로 접근하도록 하는 것.
### 1. 모듈 수준의 build.gradle에 플러그인 추가
~~~kotlin
plugins {
    ...
    id 'kotlin-android-extensions' 
}
~~~

### 2. Extentions을 사용하려는 곳에 import 추가
~~~kotlin
...
import kotlinx.android.synthetic.main.activity_main.*
~~~


# 코틀린의 Static
코틀린에서 static으로 동작하는 변수는 companion object에 선언하면 어디에서든지 사용이 가능하다. 
~~~kotlin
    companion object {
        val EXTRA_NATION_NAME = "nation_name"
    }
~~~
