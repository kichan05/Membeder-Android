package com.heechan.membeder.ui.signUp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivitySignUpBinding
import com.heechan.membeder.model.data.auth.GoogleLoginRes
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.util.ExtraKey
import com.heechan.membeder.util.LoginType
import com.heechan.membeder.util.State

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private lateinit var navController: NavController
    val viewModel : SignUpViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SignUpViewModel(application) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_register) as NavHostFragment
        navController = navHostFragment.navController

        // 구글로그인인지 체크, 로그인 타입에 따라서 뷰모델에 type변경
        val googleCallBack = intent.getParcelableExtra<GoogleLoginRes>(ExtraKey.GOOGLE_CALL_BACK.key)
        Log.d("[GoogleLogin]", googleCallBack.toString())
        if(googleCallBack != null) {
            with(viewModel) {
                loginType = LoginType.GOOGLE
                email.value = googleCallBack.email
            }
        }
        else {
            viewModel.loginType = LoginType.EMAIL
        }

        viewModel.state.observe(this){
            when(it){
                State.SUCCESS -> {
                    navController.navigate(R.id.action_signUp9Fragment_to_signUp10Fragment)
                }
                State.LOADING -> {}
                State.FAIL -> {
                    Toast.makeText(this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            GALLERY_IMAGE_REQUEST -> {
                viewModel.profileImage.value = data?.data
            }
        }
    }

    val gotoSelectProfileImage = { v : View ->
        val intent = Intent(Intent.ACTION_PICK).apply {
            setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        }
        startActivityForResult(intent, GALLERY_IMAGE_REQUEST)
    }

    val gotoMain : (View) -> Unit = {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    companion object {
        const val GALLERY_IMAGE_REQUEST = 10101010
    }
}