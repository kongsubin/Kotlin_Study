package com.kongsub.mywebbrowser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import com.kongsub.mywebbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // -----------------------------------------------------------//
        //                1-2. 웹뷰에 웹 페이지 표시하기.                   //
        // -----------------------------------------------------------//
        // 웹 뷰 기본 설정
        binding.webView.apply {
            // 자바스크립트 동작을 위함
            settings.javaScriptEnabled = true
            // 웹 브라우저가 아닌 웹뷰에서 동작하도록 설정
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String){
                    binding.urlEditText.setText(url)
                }
            }
        }

        binding.webView.loadUrl("https://www.google.com")

        // -----------------------------------------------------------//
        //             1-3. 키보드의 검색 버튼 동작 정의하기.                 //
        // -----------------------------------------------------------//
        binding.urlEditText.setOnEditorActionListener{ _, actionId, _ ->   // 반응한뷰, 액션 ID, 이벤트 (뷰와 이벤트는 사용하지 않으므로 "_"로 대체
            if(actionId == EditorInfo.IME_ACTION_SEARCH) { // actionId 값은 EditorInfo 클래스의 검색 버튼이 눌렀는지 확인하는 여부.
                binding.webView.loadUrl(binding.urlEditText.text.toString()) // 입력한 url 웹뷰에 전달 후 로딩
                true
            }else {
                false
            }
        }
    }

    // -----------------------------------------------------------//
    //                   1-4. 뒤로가기 동작 재정의.                    //
    // -----------------------------------------------------------//
    // 웹뷰 내에서 뒤로가기 기능 적용
    override fun onBackPressed() {
        if(binding.webView.canGoBack()) { // 웹뷰가 이전페이지로 이동할 수 있는 경우 작동.
            binding.webView.goBack()
        }else {
            super.onBackPressed()
        }
    }

    // -----------------------------------------------------------//
    //                 2-2. 옵션 메뉴를 액티비티에 표시.                 //
    // -----------------------------------------------------------//
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu) // 메뉴 리소스 지정
        return true // true 반환시, 엑티비티에 메뉴가 있음을 인식
    }

    // -----------------------------------------------------------//
    //                 2-3. 옵션 메뉴 클릭 이벤트 처리.                 //
    // -----------------------------------------------------------//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_google, R.id.action_home -> {
                binding.webView.loadUrl("")
                return true
            }
            R.id.action_naver -> {
                binding.webView.loadUrl("")
                return true
            }
            R.id.action_kong -> {
                binding.webView.loadUrl("")
                return true
            }
            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
            R.id.action_send_text -> {
                binding.webView.url?.let { url ->
                    //
                }
                return true
            }
            R.id.action_email -> {
                binding.webView.url?.let { url ->
                    //
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}