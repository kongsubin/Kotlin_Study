# NavHostFragment
- 다른 프래그먼트들을 표시해 준다. 
- 속성
    - name : androidx.navigation.fragment.NavHostFragment
    - navGraph : @navigation/nav_graph
    
    
# navGraph
- 다른 프래그먼트들이 언제 표시되어야 하는지 정의하는 파일 
- nav_graph 에서 집 모양 아이콘이 프래그먼트의 시작점이 됨. 
- 시작점 변경을 원하는 경우, 다른 프래그먼트 우클릭 > Set as Start Destination 선택 
- 프래그먼트 더블 클릭 시, 해당 프래그먼트 디자인 xml로 넘어감. 

# 코루틴
> 코틀린에서 제공하는 비동기 처리 방식

- 코루틴 코드는 코루틴 스코프라는 개체를 통해 실행이 가능함. 

- suspend 키워드 : 지연 실행 메서드, 해당 키워드가 추가된 메서드는 코루틴 스코프에서 실행을 할 수 있다. 
    - ex
        ~~~kotlin
            // suspend : 비동기 처리를 위한 선언
            @Insert(onConflict = REPLACE)
            suspend fun insert(entity: Todo)

        ~~~

- ViewModel 클래스에서 제공하는 코루틴 스코프는 viewModelScope 프로퍼티를 통해 제공된다. 
    - ex
        ~~~kotlin
            // 할 일 추가
            fun addTodo(text: String) {
                viewModelScope.launch {
                    db.todoDao().insert(Todo(text))
                }
            }
        ~~~

# Room 데이터베이스 사용
### 1. 모듈 수준의 build.gradle
    ~~~kotlin
    ...
    plugins {
        ...
        
        // room db
        id 'kotlin-kapt'
    }

    dependencies {
        ...
        // room db
        def room_version = "2.4.3"
        implementation("androidx.room:room-runtime:$room_version")
        kapt("androidx.room:room-compiler:$room_version")
        implementation("androidx.room:room-ktx:$room_version")
    }
    ~~~

### 2. data class 생성
    ~~~kotlin
        @Entity
        data class Todo (
            var title: String,
            var date: Long = Calendar.getInstance().timeInMillis,
        ) {
            @PrimaryKey(autoGenerate = true)
            var id: Long = 0
        }
    ~~~

### 3. DAO 생성
    ~~~kotlin
        @Dao
        interface TodoDao {
            @Query("SELECT * FROM todo ORDER BY date DESC")
            fun getAll(): Flow<List<Todo>> // Flow : 코틀린의 고급 기능 중 하나 - 데이터를 관찰할 수 있도록 함.

            // suspend : 비동기 처리를 위한 선언
            @Insert(onConflict = REPLACE)
            suspend fun insert(entity: Todo)

            @Update
            suspend fun update(entity: Todo)

            @Delete
            suspend fun delete(entity: Todo)
        }
    ~~~

### 4. Database 생성
    ~~~kotlin
        @Database(entities = [Todo::class], version = 1)
        abstract class TodoDatabase : RoomDatabase() {
            abstract fun todoDao(): TodoDao
        }
    ~~~

### 4. AndroidViewModel 생성
> ViewModel vs AndroidViewModel : application객체를 사용할 수 있냐의 차이. AndroidViewModel에서는 사용이 가능

    ~~~kotlin
        class MainViewModel(application: Application) : AndroidViewModel(application) {
            // Room 데이터베이스
            private val db = Room.databaseBuilder(
                application,
                TodoDatabase::class.java, "todo"
            ).build()
            
            // 할 일 추가
            fun addTodo(text: String) {
                viewModelScope.launch {
                    db.todoDao().insert(Todo(text))
                }
            }
        }
    ~~~


# Calendar 클래스 사용법 
- timeInMillis 메서드 : Calendar 객체를 Long형 값으로 변환 
~~~kotlin
// 오늘 날짜로 캘린더 객체 생성 
val calendar: Calendar.getInstance()

// 특정 날짜로 설정
calendar.set(Calendar.YEAR, year)       // 년도 설정
calendar.set(Calendar.MONTH, month)     // 월 설정 
calendar.set(Calendar.DAY_OF_MONTH, dayOfMoth)  // 일 설정 


// 날짜를 Long형으로 변환
val time : Long = calendar.timeInMillis
~~~
