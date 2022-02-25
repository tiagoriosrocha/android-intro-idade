package com.example.projetoidade.controller

import android.content.Context
import android.util.Log
import com.example.projetoidade.database.DBHelperTablePessoa
import com.example.projetoidade.model.Pessoa
import java.time.LocalDate
import java.util.ArrayList

class PessoaController(var context : Context){

    lateinit var banco : DBHelperTablePessoa
    lateinit var contexto : Context

    init{
        this.contexto = context
        this.banco = DBHelperTablePessoa(this.contexto, null)
    }

    fun salvarRegistro(pessoa : Pessoa) : Boolean{

        var dataAtual = LocalDate.now()

        banco.addPessoa(pessoa.nome,
                       pessoa.dataNascimento.toString(),
                       pessoa.periodo.toString(),
                       dataAtual.toString())

        Log.i("INFORMACAO","Registro salvo no banco de dados")

        return true
    }

    fun buscarRegistros(contexto: Context) : ArrayList<Pessoa>{
        var listaPessoa: ArrayList<Pessoa> = this.banco.getPessoas()

        for(pessoa in listaPessoa){
            Log.i("INFORMACAO", pessoa.toString())
        }
        return listaPessoa
    }

}