package br.com.cacaushow.controledeestoquecacaushow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.kit_cadastro_activity.*

class DisciplinaCadastroActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kit_cadastro_activity)
        setTitle("Novo KIT")
        salvarKit.setOnClickListener {
            val kit = Kit()
            kit.nome = nomeKit.text.toString()
            kit.itens = itensKit.text.toString()
            kit.lote = loteKit.text.toString()
            kit.foto = urlFoto.text.toString()
            taskAtualizar(kit)
        }
    }
    private fun taskAtualizar(kit: Kit) {
// Thread para salvar a disciplina
        Thread {
            KitService.save(kit)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}