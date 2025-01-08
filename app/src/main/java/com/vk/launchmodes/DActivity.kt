package com.vk.launchmodes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vk.launchmodes.databinding.ActivityAactivityBinding

class DActivity : BaseActivity() {
    lateinit var activityAactivityBinding: ActivityAactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        activityAactivityBinding = ActivityAactivityBinding.inflate(layoutInflater)
        setContentView(activityAactivityBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        activityAactivityBinding.apply {
            // Get layout parameters and set the top margin
            val layoutParams =
                activityAactivityBinding.llTop.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(
                layoutParams.leftMargin,
                Constants.getMarginTop(),
                layoutParams.rightMargin,
                layoutParams.bottomMargin
            )

            // Apply the updated layout parameters
            activityAactivityBinding.llTop.layoutParams = layoutParams

            tvTop.text = this@DActivity::class.java.simpleName.substring(
                0,
                1
            ) + " " + this@DActivity::class.java.simpleName.substring(1)
            activityAactivityBinding.apply {
                val data = getActivityData()
                topActivity.text = data.topActivity
                bottomActivity.text = data.baseActivity
                taskNo.text = data.taskName.toString()
            }


            firstBtn.setOnClickListener {
                if (checkBoxA.isChecked) {
                    startActivity(Intent(this@DActivity, AActivity::class.java))
                }
                if (checkBoxB.isChecked) {
                    startActivity(Intent(this@DActivity, BActivity::class.java))
                }
            }
            secondBtn.setOnClickListener {
                if (checkBoxA.isChecked) {
                    val intent = Intent(this@DActivity, AActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    startActivity(intent)
                }
                if (checkBoxB.isChecked) {
                    val intent = Intent(this@DActivity, BActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    startActivity(intent)
                }
            }
            thirdBtn.setOnClickListener {
                startActivity(Intent(this@DActivity, CActivity::class.java))
            }
            fourthBtn.setOnClickListener {
                startActivity(Intent(this@DActivity, DActivity::class.java))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constants.setMarginTop()
    }
}