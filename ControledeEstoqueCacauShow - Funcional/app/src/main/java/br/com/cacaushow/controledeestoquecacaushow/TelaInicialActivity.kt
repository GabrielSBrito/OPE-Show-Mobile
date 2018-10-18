package br.com.cacaushow.controledeestoquecacaushow

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_tela_inicial)

        var nome = intent.getStringExtra("nome")

        mensagem_inicial.text = "Bem vindo, $nome!"

        setSupportActionBar(toolbar)
        congiguraMenuLateral()


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem
        //Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if (id == R.id.nav_sair) {
            Toast.makeText(this, "Saindo",
                    Toast.LENGTH_LONG).show()
            val returnIntent = Intent()
            returnIntent.putExtra("Result", "Saída do App")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun congiguraMenuLateral(){
        var toolbar = toolbar
        var menuLateral = layoutMenuLateral

        var toogle = ActionBarDrawerToggle(
                this,
                menuLateral,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()


        var navigationView = menu_lateral
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Clicou no Home", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaInicialActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_controle_de_estoque -> {
                Toast.makeText(this, "Controle de Estoque", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ControleDeValidade::class.java)
                startActivity(intent)

            }
            R.id.nav_kits -> {
                Toast.makeText(this, "Kits", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Kits::class.java)
                startActivity(intent)
            }
            R.id.nav_sair -> {
                Toast.makeText(this, "Saindo...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finishAndRemoveTask()
            }
            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }
// fecha menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.layoutMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
