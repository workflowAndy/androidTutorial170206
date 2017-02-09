package com.example.a.a14_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        SmsMessage[] smsMessages = null; //배열로 선언
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");   //getExtras() 는 bundle 이 return됨 -> 값들이 bundle에 담김

        smsMessages = new SmsMessage[pdus.length];

        String str = "";
        for(int i=0; i < smsMessages.length; i++){
            smsMessages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);

            str += "SMS from: " + smsMessages[i].getOriginatingAddress(); //보낸사람
            str += " Body: " + smsMessages[i].getMessageBody() + "\n";
        }

        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
