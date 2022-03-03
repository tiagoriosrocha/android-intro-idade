package com.example.projetoidade.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoidade.R
import com.example.projetoidade.controller.PessoaController
import com.example.projetoidade.model.Pessoa
import com.example.projetoidade.view.adapter.PessoaListAdapter


class HistoricoActivity : AppCompatActivity() {

    lateinit var listaItems : RecyclerView
    lateinit var pessoaListAdapter : PessoaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        inicializaComponentes()

        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                pessoaListAdapter.removeAt(viewHolder.adapterPosition)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition
                // move item in `fromPos` to `toPos` in adapter.
                return true // true if moved, false otherwise
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(listaItems)
    }

    fun inicializaComponentes(){
        listaItems = findViewById(R.id.lista)
        var pessoaController = PessoaController(this)
        var listaPessoas : ArrayList<Pessoa> = pessoaController.buscarRegistros()
        pessoaListAdapter = PessoaListAdapter(listaPessoas,this)
        listaItems.adapter = pessoaListAdapter
    }

    fun acaoVoltar(view: View){
        val intent = Intent(this, CalculaIdadeActivity::class.java)
        startActivity(intent)
        finish()
    }
}