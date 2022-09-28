# UI Chains 사용하기
1. Chain을 만들 Component를 선택한다. 
2. 오른쪽 마우스 우클릭.
3. Chains -> Create Horizontal Chain 클릭.
4. 상황에 맞는 체인모드 선택. 

# 사운드 리소스 사용하기
1. raw 디렉터리 만들기 : File -> New -> Android Resource Directory : raw 선택 후 저장
    > 사운드 파일은 raw 디렉터리에 넣어서 사용해야함.

2. raw 디렉터리에 .wav or .mp4 파일 넣기.


# 안드로이드에서 소리를 사용하는 방법
### 1. MediaPlayer 클래스 
- 일반적인 소리 파일 (음악 파일 또는 비디오 파일을 실행할 수 있음)
- 소리를 한번만 재생하는 경우
- 노래나 배경음과 같은 경우에 유용 
    ~~~kotlin
        // raw 디렉터리의 play_music 파일을 재생하는 예 
        val mediaPlayer = MediaPlayer.create(this, R.raw.play_music)
        button.setOnClickListener{ mediaPlayer.start() }
        
        //사용이 끝난 경우 반드시 릴리즈해야함.
        mediaPlayer.release()
    ~~~
### 2. SoundPoll 클래스 
- 실로폰과 같이 연타를 해서 연속으로 소리를 재생하는 경우 
    ~~~kotlin
        override fun onCreate(savedInstanceState: Bundle?) {
            ...
            // SoundPool 객체 생성 
            val soundPool = SoundPool.Builder().build()
            // load() 메서드로 소리 파일 로드 
            val soundId = soundPool.load(this, R.raw.play_music, 1)
            button.setOnClickListener{ soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f}
            ...
        }
        ...
        
        // release 해주기
        override fun onDestroy() {
            super.onDestroy()
            soundPool.release()
        }
    ~~~
    
    - load() 메서드 : 음원을 준비하여 id를 반환함. 
        ~~~kotlin
            load(context: Context, resId: Int, priority: Int) :
        ~~~
        - Context : 컨텍스트 또는 액티비티를 지정
        - resId : 재생할 raw 디렉터리의 소리 파일 리소스를 지정
        - priority : 우선순위를 지정함. 숫자가 높으면 우선순위가 높음. 
        
    - play() 메서드 : 음원을 재생함. 
        ~~~kotlin
            play(soundId: Int, leftVolume: Float, rightVolume: Float, priority: Int, loop: Int, rate: Float)
        ~~~
        - soundId : load() 메서드에서 반환된 음원의 id를 지정함
        - leftVolume : 왼쪽 볼륨을 0.0 ~ 1.0 사이에서 지정함
        - rightVolume : 오른쪽 볼륨을 0.0 ~ 1.0 사이에서 지정함
        - priority : 우선순위를 지정함. (0이 가장 낮은 순위)
        - loop : 반복을 지정함. (0 : 반복x, -1 : 반복 o)
        - rate : 재생속도를 지정함. (1.0 : 보통, 0.5 : 0.5배속, 2.0 : 2배속 )
        
