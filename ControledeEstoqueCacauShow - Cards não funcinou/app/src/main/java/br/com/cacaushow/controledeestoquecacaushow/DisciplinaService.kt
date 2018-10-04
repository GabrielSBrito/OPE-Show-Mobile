package br.com.cacaushow.controledeestoquecacaushow

import android.content.Context

object DisciplinaService {
    fun getDisciplinas(contexto: Context): List<Disciplina> {
        val disciplinas = mutableListOf<Disciplina>()
        for (i in 1..10) {
            val d = Disciplina()
            d.nome = "Disciplina $i"
            d.ementa = "Ementa da Disciplina $i"
            d.professor = "Professor da disciplina $i"
            d.foto = "https://www.google.com.br/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwiez9SXme3dAhXGDJAKHeOrDJ0QjRx6BAgBEAU&url=https%3A%2F%2Fexame.abril.com.br%2Fnegocios%2Fcacau-show-vai-comecar-a-vender-bombons-porta-a-porta%2F&psig=AOvVaw0nVYQag72Ofhvo4vE1XuKN&ust=1538756593883051"
            disciplinas.add(d)

        }
        return disciplinas
    }


}