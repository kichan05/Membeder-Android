package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.auth.User
import com.heechan.membeder.model.data.team.Applicant
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.ui.team.detail.TeamDetailActivity
import com.heechan.membeder.ui.view.snack.GoodSnackBar
import com.heechan.membeder.util.ExtraKey

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.fragment_main)
        binding.bnvMain.setupWithNavController(navController)

        GoodSnackBar.make(
            binding.root,
            "${SingletonObject.userData!!.nickname}님 환영합니다.",
            "멤비더에 어서와요.\n함께 일을 해봐요",
            700,
        ).show()


//        binding.btn.setOnClickListener {
//            val intent = Intent(this, TeamDetailActivity::class.java)
//            intent.putExtra(
//                ExtraKey.TEAM_DATA.key,
//                Team(
//                    name = "몰라",
//                    description = "ㅎㅇ",
//                    private = false,
//                    image = "",
//                    applicant = Applicant(
//                        developer = 0,
//                        designer = 0,
//                        director = 0
//                    ),
//                    id = "ㅁㄴㅇㅁㄴㅇ",
//                    created = "",
//                    updated = "",
//                    member = listOf(),
//                    owner = SingletonObject.userData!!,
//                    permission = listOf(),
//                    schedule = listOf()
//                )
//            )
//
//            startActivity(intent)
//        }
    }

    fun gotoTeamBuilding() {
        Log.e("btnHomeNoTeamGotoTeamBuildingEvent", "실행")
        navController.navigate(R.id.action_menu_main_home_to_menu_main_teamBuilding)
    }
}