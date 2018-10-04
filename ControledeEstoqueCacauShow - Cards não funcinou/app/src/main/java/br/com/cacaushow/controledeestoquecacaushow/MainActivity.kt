package br.com.cacaushow.controledeestoquecacaushow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val contexo:Context get() = this

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.login)

        botao_login.setOnClickListener {onClickLogin()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1){
            val result = data?.getSerializableExtra("result")
            Toast.makeText(contexo, "$result", Toast.LENGTH_LONG).show()
        }
    }

    fun onClickLogin() {
        val campoUsuario = campo_usuario
        val campoSenha = campo_senha
        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()
        if (valorUsuario == "aluno" && valorSenha == "impacta") {
            val intent = Intent(contexo, TelaInicialActivity::class.java)
            intent.putExtra("nome", valorUsuario)
            startActivityForResult(intent, 1)
        }
        else if (valorUsuario == "aluno"){
            Toast.makeText(contexo, "Senha incorreta", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(contexo, "Usuário incorreto", Toast.LENGTH_LONG).show()
        }
    }
}
