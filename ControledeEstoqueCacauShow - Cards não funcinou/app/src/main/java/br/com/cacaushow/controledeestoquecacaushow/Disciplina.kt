package br.com.cacaushow.controledeestoquecacaushow

import android.os.Parcel
import android.os.Parcelable

class Disciplina() : Parcelable {
    var id:Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    var professor = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        nome = parcel.readString()
        ementa = parcel.readString()
        foto = parcel.readString()
        professor = parcel.readString()
    }

    override fun toString(): String {
        return "Disciplina(nome='$nome')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(nome)
        parcel.writeString(ementa)
        parcel.writeString(foto)
        parcel.writeString(professor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Disciplina> {
        override fun createFromParcel(parcel: Parcel): Disciplina {
            return Disciplina(parcel)
        }

        override fun newArray(size: Int): Array<Disciplina?> {
            return arrayOfNulls(size)
        }
    }

}