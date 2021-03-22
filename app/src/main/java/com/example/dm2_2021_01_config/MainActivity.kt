package com.example.dm2_2021_01_config

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = getSharedPreferences("login", Context.MODE_PRIVATE)

        atualizaUltimoLogin(sp)
        atualizaQuantidadeLogins(sp)

        btnLogar.setOnClickListener {
            if(edtLogin.text.toString().equals("joao")
                    &&
                    edtSenha.text.toString().equals("123")) {

                val d = Date()

                sp.edit().putString("ultimo_login", d.toString()).apply()

                val qtd = sp.getInt("quantidade_logins", 0)

                sp.edit().putInt("quantidade_logins", qtd+1).apply()

                atualizaUltimoLogin(sp)
                atualizaQuantidadeLogins(sp)

            } else {
                Toast.makeText(this, "Login e senha não conferem.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun atualizaUltimoLogin(sp: SharedPreferences) {
        val res = sp.getString("ultimo_login", "no")

        if (res.equals("no")) {
            txtUltimoLogin.text = "Não foi efetuado login até o momento"
        } else {
            txtUltimoLogin.text = res
        }
    }

    private fun atualizaQuantidadeLogins(sp: SharedPreferences) {
        val qtd = sp.getInt("quantidade_logins", 0)

        if (qtd == 0) {
            txtQuantidade.text = "Quantidade: 0"
        } else {
            txtQuantidade.text = "Quantidade: " + qtd
        }
    }
}
