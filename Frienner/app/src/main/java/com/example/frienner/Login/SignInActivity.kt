//package com.example.frienner.Login
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import com.example.frienner.R
//import com.kakao.sdk.user.UserApiClient
//
//class SignInActivity : AppCompatActivity() {
//
//    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_in)
//
//        val et_id = findViewById<EditText>(R.id.et_id)
//        val et_pw = findViewById<EditText>(R.id.et_pw)
//        val btn_login = findViewById<Button>(R.id.btn_login)
//        val btn_signUp = findViewById<Button>(R.id.btn_signup)
//        val btn_kakao_login = findViewById<Button>(R.id.btn_kakao_login)
//
//        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val user_id = result.data?.getStringExtra("id") ?: ""
//                val user_pw = result.data?.getStringExtra("pw") ?: ""
//                et_id.setText(user_id)
//                et_pw.setText(user_pw)
//            }
//        }
//
//        btn_login.setOnClickListener {
//            if (et_id.text.toString().trim().isEmpty() || et_pw.text.toString().trim().isEmpty()) {
//                Toast.makeText(this, getString(R.string.toast_msg_idpqErr), Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            // 로그인 로직
//        }
//
//        btn_signUp.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            activityResultLauncher.launch(intent)
//        }
//
//        btn_kakao_login.setOnClickListener {
//            // KakaoTalk 로그인
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
//                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
//                    if (error != null) {
//                        Toast.makeText(this, "로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
//                    } else if (token != null) {
//                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
//                        // 로그인 성공 후 처리
//                    }
//                }
//            } else {
//                UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
//                    if (error != null) {
//                        Toast.makeText(this, "로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
//                    } else if (token != null) {
//                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
//                        // 로그인 성공 후 처리
//                    }
//                }
//            }
//        }
//    }
//}
