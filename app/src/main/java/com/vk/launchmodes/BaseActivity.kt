package com.vk.launchmodes

import android.annotation.TargetApi
import android.app.ActivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
data class ActivityData(
    var baseActivity: String? = "",
    var topActivity: String? = "",
    var taskName: Int? = 1
)
open class BaseActivity: AppCompatActivity() {
    companion object{
        var initialTaskId =-1
        var taskInfo: MutableList<ActivityManager. RunningTaskInfo>? =null
        @TargetApi(35)
        fun getActivityData():ActivityData{
            var k = taskInfo?.last
            if(initialTaskId==-1){
                if (k != null) {
                    initialTaskId = k.taskId
                }
            }
            return ActivityData(k?.baseActivity?.shortClassName,k?.topActivity?.shortClassName,
                k?.taskId?.minus(initialTaskId)?.plus(1)
            )
        }
    }
    private lateinit var activityManager: ActivityManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val totalNumberOfTasks = activityManager.getRunningTasks(10).size
         taskInfo = activityManager.getRunningTasks(10)

    }
}