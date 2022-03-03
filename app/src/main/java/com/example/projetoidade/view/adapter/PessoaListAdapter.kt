package com.example.projetoidade.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.projetoidade.R
import com.example.projetoidade.model.Pessoa
import java.time.format.DateTimeFormatter

class PessoaListAdapter(private val listaPessoas: List<Pessoa>,
                        private val context: Context) : Adapter<PessoaListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pessoa_item_v2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pessoa = listaPessoas[position]
        holder?.let {
            it.bindView(pessoa)
        }
    }

    override fun getItemCount(): Int {
        return listaPessoas.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        lateinit var nome : TextView
        lateinit var nascimento : TextView
        lateinit var idade : TextView

        init {
            nome = itemView.findViewById(R.id.nome_item_v2)
            nascimento = itemView.findViewById(R.id.nascimento_item_v2)
            idade = itemView.findViewById(R.id.idade_item_v2)
        }

        fun bindView(pessoa: Pessoa) {
            nome.text = pessoa.nome
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            nascimento.text = pessoa.dataNascimento.format(formatter)
            idade.text = "" + pessoa.periodo.years + " anos"
        }
    }
}