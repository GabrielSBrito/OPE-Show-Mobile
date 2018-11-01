package br.com.cacaushow.controledeestoquecacaushow

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.com.cacaushow.controledeestoquecacaushow.DebugActivity
import br.com.cacaushow.controledeestoquecacaushow.Kit
import br.com.cacaushow.controledeestoquecacaushow.KitService
import br.com.cacaushow.controledeestoquecacaushow.R
import com.squareup.picasso.Picasso

class KitActivity : DebugActivity() {

    private val context: Context get() = this
    var kit: Kit? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kit)

        // recuperar onjeto de Disciplina da Intent
        if (intent.getSerializableExtra("kit") is Kit)
            kit = intent.getSerializableExtra("kit") as Kit

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = kit?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeKit)
        texto.text = kit?.nome
        var imagem = findViewById<ImageView>(R.id.imagemKit)
        Picasso.with(this).load(kit?.foto).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_kit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado
        // remover a disciplina no WS
        if  (id == R.id.action_remover) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Deseja excluir o Kit")
                    .setPositiveButton("Sim") {
                        dialog, which ->
                            dialog.dismiss()
                            taskExcluir()
                    }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                    }.create().show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.kit != null && this.kit is Kit) {
            // Thread para remover a disciplina
            Thread {
                KitService.delete(this.kit as Kit)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }

}
