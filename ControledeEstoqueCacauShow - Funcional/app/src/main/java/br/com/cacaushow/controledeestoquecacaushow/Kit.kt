package br.com.cacaushow.controledeestoquecacaushow

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "kits")
class Kit : Serializable {

    @PrimaryKey
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