package com.example.projetoidade.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoidade.R
import com.example.projetoidade.controller.PessoaController
import com.example.projetoidade.model.Pessoa

class HistoricoActivity : AppCompatActivity() {

    lateinit var listaItems : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        inicializaComponentes()
    }

    fun inicializaComponentes(){
        listaItems = findViewById(R.id.lista)
        var pessoaController = PessoaController(this)
        var listaPessoas : ArrayList<Pessoa> = pessoaController.buscarRegistros(this)
        listaItems.adapter = PessoaListAdapter(listaPessoas,this)
    }

    fun acaoVoltar(view: View){
        val intent = Intent(this, CalculaIdadeActivity::class.java)
        startActivity(intent)
        finish()
    }
}