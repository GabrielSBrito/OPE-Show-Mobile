package br.com.cacaushow.controledeestoquecacaushow

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat


/**
 * Created by Android on 25/10/2018.
 */
object NotificationUtil {
    fun createChannel(contexto: Context){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){  //Verifica a versao do android
            val manager = contexto.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val appName = contexto.getString(R.string.app_name) //Qual aplicativo
            val c = NotificationChannel("1",appName,NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(c)
        }
    }

    fun create(contexto: Context,id:Int,intent:Intent, titulo:String, texto:String){
        val p = PendingIntent.getActivity(contexto,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(contexto,"1").setContentIntent(p).setContentTitle(titulo)
                .setContentText(texto)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        with(NotificationManagerCompat.from(LMSApplication.getInstance())){
            val n = builder.build()
            notify(id,n)
        }


    }
}