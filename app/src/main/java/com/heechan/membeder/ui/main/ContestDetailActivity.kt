package com.heechan.membeder.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityContestDetailBinding
import com.heechan.membeder.util.ExtraKey

class ContestDetailActivity : BaseActivity<ActivityContestDetailBinding>(R.layout.activity_contest_detail) {
    val viewModel : ContestDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel.contestName.value = intent.getStringExtra(ExtraKey.CONTEST_DATA_Name.key)
        viewModel.contestPoster.value = intent.getStringExtra(ExtraKey.CONTEST_DATA_Poster.key)
        viewModel.contestUrl.value = intent.getStringExtra(ExtraKey.CONTEST_DATA_Url.key)
        viewModel.contestHost.value = intent.getStringExtra(ExtraKey.CONTEST_DATA_Host.key)
        viewModel.contestPrize.value = intent.getStringExtra(ExtraKey.CONTEST_DATA_Prize.key)
        viewModel.contestTarget.value = intent.getStringExtra(ExtraKey.CONTEST_DATA_Target.key)
        Log.d("contestName", viewModel.contestName.value.toString())
        Log.d("contestPoster", viewModel.contestPoster.value.toString())
        Log.d("contestUrl", viewModel.contestUrl.value.toString())
        Log.d("contestHost", viewModel.contestHost.value.toString())
        Log.d("contestPrize", viewModel.contestPrize.value.toString())
        Log.d("contestTarget", viewModel.contestTarget.value.toString())
        val imageView = findViewById<ImageView>(R.id.iv_contestdetail_poster)
        Glide.with(this)
            .load(viewModel.contestPoster.value)
            .into(imageView)

        binding.btnContestdetailGotopage.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.contestUrl.value)).apply {
                startActivity(this)
            }
        }
        binding.hdContestDetail.setNavigationClickListener {
            finish()
        }

    }
}