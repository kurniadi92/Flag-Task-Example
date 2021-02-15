package com.example.taskexample

import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val singleTaskAffinityButton = findViewById<Button>(R.id.singleTaskWithAffinityButton)
        val singleTaskButton = findViewById<Button>(R.id.singleTaskWithoutAffinityButton)
        val aButton = findViewById<Button>(R.id.AActvityButton)
        val bButton = findViewById<Button>(R.id.BActivityButton)
        val cButton = findViewById<Button>(R.id.CActivityButton)
        val dButton = findViewById<Button>(R.id.DActivityButton)
        val mainButton = findViewById<Button>(R.id.MainButton)

        singleTaskAffinityButton.setOnClickListener {
            val intent = Intent(this, AffinitySingleTaskActivity::class.java)
            startActivity(intent)
        }

        singleTaskButton.setOnClickListener {
            val intent = Intent(this, SingleTaskActivity::class.java)
            startActivity(intent)
        }

        aButton.setOnClickListener {
            val intent = Intent(this, AActivity::class.java)
            startActivity(intent)
        }

        bButton.setOnClickListener {
            val intent = Intent(this, BActivity::class.java)
            startActivity(intent)
        }

        cButton.setOnClickListener {
            val intent = Intent(this, CActivity::class.java)
            startActivity(intent)
        }

        dButton.setOnClickListener {
            val intent = Intent(this, DActivity::class.java)
            startActivity(intent)
        }

        mainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager

        getAppTaskState()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getAppTaskState()
    }

    protected var activityManager: ActivityManager? = null

    fun getNumberOfTasks(): Int {
        return activityManager!!.getAppTasks().size
    }

    fun getAppTaskState() {
        println(">>>>>\n>>>>>\n")
        println(">>>>> number of task ${getNumberOfTasks()}")
        val totalNumberOfTasks: Int = activityManager!!.getRunningTasks(10).size //Returns total number of tasks - stacks
        println("Total Number of Tasks: $totalNumberOfTasks")
        val taskInfo: List<ActivityManager.RunningTaskInfo> =  activityManager!!.getRunningTasks(10) //returns List of RunningTaskInfo - corresponding to tasks - stacks
        for (info in taskInfo) {
            println(">>>>>>> ##########")
            println(">>>>>>> Number of Activities \${info.id} " + info.numActivities)
            println(">>>>>>> TOP Activity ${info.topActivity!!.className}")
            println(">>>>>>> Base Activity ${info.baseActivity!!.className}")
        }
    }

}