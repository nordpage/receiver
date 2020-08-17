package xyz.mobcoder.child

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (broadcastListener != null) {
            broadcastListener!!.onBytesReceive(intent.getByteArrayExtra("KeyName"))
            broadcastListener!!.onTimeReceive(intent.getLongExtra("Time",0L))
        }
    }

    interface BroadcastListener {
        fun onTimeReceive(data: Long)
        fun onBytesReceive(data: ByteArray)
    }

    companion object {
        var broadcastListener: BroadcastListener? = null
    }

}
