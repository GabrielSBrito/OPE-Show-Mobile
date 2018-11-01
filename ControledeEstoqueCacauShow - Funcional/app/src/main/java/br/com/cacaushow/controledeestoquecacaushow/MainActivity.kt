package br.com.cacaushow.controledeestoquecacaushow

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {

    private val contexo:Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        Prefs.setString("","")
        val str = Prefs.getString("")




        botao_login.setOnClickListener {onClickLogin()}

        var lembrar = Prefs.getBoolean("lembrar")
        var usuario = Prefs.getString("usuario")
        var senha = Prefs.getString("senha")
        campo_usuario.setText(usuario)
        campo_senha.setText(senha)
        lembrarLogin.isChecked = lembrar

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
        val lembrar = findViewById<CheckBox>(R.id.lembrarLogin).isChecked

        Prefs.setBoolean("lembrar",lembrar)
        if (lembrar){
            Prefs.setString("usuario",valorUsuario)
            Prefs.setString("senha",valorSenha)
        }else{
            Prefs.setString("usuario","")
            Prefs.setString("senha","")
        }

        Thread{
            var verificaLogin = LoginService.login(valorUsuario,valorSenha)
            Log.d("WS_LMS", verificaLogin.toString())
            runOnUiThread {
                Log.d("WS_LMS", "Retorno"+verificaLogin.toString())
                if (verificaLogin){
                    val intent = Intent(contexo, TelaInicialActivity::class.java)
                    intent.putExtra("nome", valorUsuario)
                    startActivityForResult(intent, 1)

                }else if (valorUsuario == "aluno"){
                    Toast.makeText(contexo, "Senha incorreta", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(contexo, "Usuário incorreto", Toast.LENGTH_LONG).show()
                }
            }
        }.start()

        /*if (valorUsuario == "aluno" && valorSenha == "impacta") {
            val intent = Intent(contexo, TelaInicialActivity::class.java)
            intent.putExtra("nome", valorUsuario)
            startActivityForResult(intent, 1)
        }
        else if (valorUsuario == "aluno"){
            Toast.makeText(contexo, "Senha incorreta", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(contexo, "Usuário incorreto", Toast.LENGTH_LONG).show()
        }*/
    }
}
