package com.heechan.membeder.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityProfileBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.util.ExtraKey

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    val viewModel : ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.userId.value = intent.getStringExtra(ExtraKey.USER_DATA.key)
        viewModel.getUserData()

        viewModel.userData.observe(this){
            if (it == null) return@observe

            val tran = supportFragmentManager.beginTransaction();
            val fragment = ProfileFragment(it)

            tran.replace(R.id.fl_profile, fragment)
            tran.addToBackStack(null)
            tran.commit()
        }
    }
}

