package br.com.cacaushow.controledeestoquecacaushow

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object KitService {
    val host = "http://gabrielsbrito.pythonanywhere.com"
    val TAG = "WS_LMS"
    fun getKit (context: Context): List<Kit> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/kits"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)

            return parseJson<List<Kit>>(json)
        }
        Toast.makeText(context,"Sem Internet",Toast.LENGTH_SHORT).show()
        return ArrayList<Kit>()
    }

    fun save(kit: Kit): Response{
        val json = HttpHelper.post("$host/kits",kit.toJson())
        return parseJson<Response>(json)
    }

    fun delete(kit: Kit): Response{
        val url = "$host/kits/${kit.id}"
        val json = HttpHelper.delete(url)
        return parseJson<Response>(json)
    }

    inline fun <reified T> parseJson(json: String): T{
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json,type)
    }
}