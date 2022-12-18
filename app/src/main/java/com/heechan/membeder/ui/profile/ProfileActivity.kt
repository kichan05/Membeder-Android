package com.heechan.membeder.ui.profile

import android.os.Bundle
import androidx.activity.viewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityProfileBinding
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.util.ExtraKey

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    val viewModel : ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.user.value = intent.getSerializableExtra(ExtraKey.USER_DATA.key) as User
    }
}

