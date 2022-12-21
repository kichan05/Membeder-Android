package com.heechan.membeder.ui.team.caleander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityTodoEditBinding
import com.heechan.membeder.databinding.ActivityTodoFinalEditBinding
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.schedule.ScheduleAddReq
import com.heechan.membeder.model.data.schedule.TodoEditReq
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.schedule.ScheduleAddActivity
import com.heechan.membeder.ui.schedule.ScheduleAddViewModel
import com.heechan.membeder.ui.view.snack.BadSnackBar
import com.heechan.membeder.util.ExtraKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.notifyAll

class TodoFinalEditActivity : BaseActivity<ActivityTodoFinalEditBinding>(R.layout.activity_todo_final_edit) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.singleton = SingletonObject
        val schedulename = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_NAME.key)!!
        val scheduleDescribtion = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_DESCRIBTION.key)!!
        val scheduledeadline = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_DEADLINE.key)!!
        val scheduleid = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_TEAMID.key)!!
        val schedulecompleate = intent.getStringExtra(ExtraKey.SCHEDULE_DATA_COMPLETE.key)!!
        val repository = TeamRepositoryImpl()
        val teamId = SingletonObject.selectTeam.value!!.id


        binding.edtScheduleNameName.setText(schedulename)
        binding.edtScheduleNameDecription.setText(scheduleDescribtion)
        binding.edtScheduleNameDate.setText(scheduledeadline)

        binding.btnTodofinalTodoEdit.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    val request = TodoEditReq(
                        name = binding.edtScheduleNameName.text.toString(),
                        description = binding.edtScheduleNameDecription.text.toString(),
                        complete = schedulecompleate.toBoolean(),
                        deadLine = binding.edtScheduleNameDate.text.toString()
                    )
                    Log.d("ddddd",teamId)
                    Log.d("ddddd",scheduleid)
                    repository.EditSchedule(
                        team_id = teamId, schedule_id = scheduleid,scheduleData = request
                    )
                }
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(binding.root.context, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                    (binding.root.context as TodoFinalEditActivity).finish()
                } else {
                    BadSnackBar.make(
                        binding.root,
                        "수정 실패했어요",
                        "일정을 수정하는데 실패했어요. 잠시 뒤에 다시 시도해주세요",
                        700
                    ).show()
                }
            }
            binding.edtScheduleNameName.text.toString()


        }

    }

}