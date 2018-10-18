package br.com.cacaushow.controledeestoquecacaushow

import android.util.Log
import com.google.gson.Gson


object LoginService {
    class Usuario(nome: String, senha:String){

        var nome = ""
        var senha = ""

        init{
            this.nome = nome
            this.senha = senha
        }
    }
    val host = "http://gabrielsbrito.pythonanywhere.com"
    fun login(usuario: String, senha:String): Boolean{
        Log.d("WS_LMS", "Logando")
        val url = "${LoginService.host}/login"

        var U = Usuario(usuario,senha)
        var dados = Gson().toJson(U)

        val json = HttpHelper.post(url,dados)
        Log.d("WS_LMS", "JSON"+json.trim().toBoolean().toString())

        return json.trim().toBoolean()



    }


}