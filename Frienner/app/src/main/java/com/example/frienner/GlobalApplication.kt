package com.example.frienner
import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.sdk.common.util.Utility

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Kakao SDK 초기화

        KakaoMapSdk.init(this, "290707713f1b1a8c4e38949450f84fb3")
        // Kakao SDK 초기화
        KakaoSdk.init(this, "290707713f1b1a8c4e38949450f84fb3")

        //var keyHash = Utility.getKeyHash(this)

        //Log.d("keyHash", "keyHash: $keyHash")
    }
}
