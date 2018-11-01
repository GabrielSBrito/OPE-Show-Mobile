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
    val dao = DatabaseManager.getKitDAO()

    fun getKit (context: Context): List<Kit> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/kits"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)

            val kits = parseJson<List<Kit>>(json)
            for (kit in kits){
                saveOffilne(kit)
            }
            return kits
        }else{
            val kits = dao.findAll()
            //Toast.makeText(context,"Sem Internet",Toast.LENGTH_SHORT).show() TOAST DO KPT
            return kits
        }

    }

    fun saveOffilne(kit: Kit):Boolean{
        if (! existeKit(kit.id)){
            dao.insert(kit)
        }
        return true
    }

    fun existeKit(id: Long): Boolean{
        var kit = dao.getById(id)
        return kit != null
    }

    fun deleteOffine(kit: Kit): Response {
        if (AndroidUtils.isInternetDisponivel(LMSApplication.getInstance().applicationContext)) {
            val url = "$host/kits/${kit.id}"
            val json = HttpHelper.delete(url)
            return parseJson(json)
        } else {
            val dao = DatabaseManager.getKitDAO()
            dao.delete(kit)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
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