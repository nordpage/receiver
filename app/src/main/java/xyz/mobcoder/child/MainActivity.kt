package xyz.mobcoder.child

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.joda.time.DateTime


class MainActivity : AppCompatActivity(), MyBroadcastReceiver.BroadcastListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyBroadcastReceiver.broadcastListener = this

        val mReceiver = MyBroadcastReceiver()
        registerReceiver(mReceiver,
                IntentFilter("xyz.mobcoder.receiver"))

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onTimeReceive(data: Long) {
        val d1 = DateTime(System.currentTimeMillis())
        val d2 = DateTime(data)
        val diffInMillis = (d1.millis - d2.millis)
        Toast.makeText(this,"$diffInMillis миллисекунд",Toast.LENGTH_SHORT).show()
    }

    override fun onBytesReceive(data: ByteArray) {


        val intent = Intent()
        intent.setClassName("xyz.mobcoder.receiver", "xyz.mobcoder.receiver.DataReceiver")
        intent.action = "xyz.mobcoder.receiver.DataReceiver"
        intent.putExtra("Time", System.currentTimeMillis())
        intent.putExtra("KeyName",  "Returning")
        intent.putExtra("Array",  data)
        sendBroadcast(intent)

    }


}