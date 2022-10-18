# View Binding
> xml 레이아웃의 id를 쉽게 접근하기 위해 사용하는 기능 

## 사용법
### 1. 모듈 수준의 build.gradle에 다음을 추가한다.
~~~kotlin
    android {
        // ...
        buildFeatures {
            viewBinding true
        }
    }
~~~

### 2. Activity에 다음과 같이 view binding 객체를 가져온다. 
~~~kotlin
    //view binding 객체 얻기
    private val binding by lazy {
        /*ActivityMainBinding : activity_main.xml 이름을 참고하여 뷰 바인딩 설정에 의해 자동으로 만들어진 객체
        ActivityMainBinding 을 통해 activity_main.xml 파일에 정의한 뷰에 접근 할 수 있음.
         */
        ActivityMainBinding.inflate(layoutInflater)
    }
~~~

### 3. Activity의 onCreate 함수의 setContentView를 다음과 같이 수정한다. 
~~~kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)
            //...
    }
~~~

### 4. 사용하기
예제와 같이 같이 binding.view_id로 view에 접근이 가능하다.
~~~kotlin
    binding.resultButton.setOnClickListener{
           //...
    }
~~~

### binding 사용 전
~~~kotlin
    val resultButton = findViewById<Button>(R.id.resultButton)
    resultButton.setOnClickListener{
           //...
    }
~~~
