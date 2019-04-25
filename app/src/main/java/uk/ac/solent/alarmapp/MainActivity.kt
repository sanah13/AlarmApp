package uk.ac.solent.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val amgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pi = PendingIntent.getBroadcast(this, 0, Intent("alarmTask"), PendingIntent.FLAG_UPDATE_CURRENT)
        amgr.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + "$et1".toInt(), pi)

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    "sendTime" -> toast("${intent?.getLongExtra("time", 0)}")
                }
            }
        }
        val filter = IntentFilter().apply {
            addAction("sendTime")
        }

    }
}
