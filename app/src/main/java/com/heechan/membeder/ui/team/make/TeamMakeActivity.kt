package com.heechan.membeder.ui.team.make

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTeamMakeBinding
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import com.heechan.membeder.util.State

class TeamMakeActivity : BaseActivity<ActivityTeamMakeBinding>(R.layout.activity_team_make) {
    lateinit var navController: NavController
    val viewModel: TeamMakeViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TeamMakeViewModel(application) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_teamMake) as NavHostFragment
        navController = navHostFragment.navController

        viewModel.state.observe(this) {
            when (it) {
                State.SUCCESS -> {
                    GoodSnackBar.make(binding.root, "팀을 만들었어요", "앞으로 열심히 일해봐요", 700).show()
                    finish()
                }
                State.LOADING -> {
                }
                State.FAIL -> {
                    BadSnackBar.make(binding.root, "팀 생성에 실패했어요", "다시 시도해주세요.", 700).show()
                }
            }
        }
    }

    fun gotoNext(currentPage: Int) {
        when (currentPage) {
            1 -> {
                navController.navigate(R.id.action_teamNameFragment_to_teamDescriptionFragment)
            }
            2 -> {
                navController.navigate(R.id.action_teamDescriptionFragment_to_teamApplicantFragment)
            }
            3 -> {
                navController.navigate(R.id.action_teamApplicantFragment_to_teamLogoFragment)
            }
            4 -> {
                viewModel.makeTeam()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            GALLERY_IMAGE_REQUEST -> {
                val imgUri = data?.data ?: return
                viewModel.teamLogo.value = imgUri
                Log.d("[TeamLogo]", imgUri.toString())
            }
        }
    }

    val gotoSelectLogoImage = { v : View ->
        val intent = Intent(Intent.ACTION_PICK).apply {
            setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        }
        startActivityForResult(intent, GALLERY_IMAGE_REQUEST)
    }

    fun gotoPrev(currentPage: Int) {
        when (currentPage) {
            1 -> {
                finish()
            }
            2 -> {
                navController.navigate(R.id.action_teamDescriptionFragment_to_teamNameFragment)
            }
            3 -> {
                navController.navigate(R.id.action_teamApplicantFragment_to_teamDescriptionFragment)
            }
        }
    }

    companion object {
        const val GALLERY_IMAGE_REQUEST = 1
    }
}