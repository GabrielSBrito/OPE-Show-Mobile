package br.com.cacaushow.controledeestoquecacaushow

import com.google.gson.GsonBuilder
import java.io.Serializable

class Kit : Serializable {
    var id:Long = 0
    var nome = ""
    var lote = ""
    var foto = ""
    var itens = ""

    override fun toString(): String {
        return "Kit(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

}