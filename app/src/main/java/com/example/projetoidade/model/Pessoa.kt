package com.example.projetoidade.model

import android.util.Log
import android.widget.Toast
import java.time.DateTimeException
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class Pessoa(var txtNome : String, var txtNascimento : String) {

    lateinit var nome: String
    lateinit var dataNascimento: LocalDate
    lateinit var periodo: Period

    init{
        if( !validaNome(txtNome) ){
            Log.e("REGRADENEGOCIO", "Nome inválido")
            throw IllegalArgumentException("Nome da pessoa inválido")
        }else{
            this.nome = txtNome
        }

        if( !validaData(txtNascimento) ){
            Log.e("REGRADENEGOCIO", "Data Inválida")
            throw IllegalArgumentException("Data de nascimento inválida")
        }else{
            this.dataNascimento = parseDataNascimento(txtNascimento)
            this.periodo = calcularIdade(txtNascimento)
        }
    }

    private fun calcularIdade(txtNascimento: String): Period {
        var dataNascimento = parseDataNascimento(txtNascimento)
        var dataAtual = LocalDate.now()
        return Period.between(dataNascimento, dataAtual)
    }

    private fun validaNome(nome: String) : Boolean{
        if( nome.isEmpty() ) {
            Log.e("REGRADENEGOCIO", "Nome não está preenchido")
            return false
        }

        return true
    }

    companion object{
        fun validaData(txtNascimento: String) : Boolean{
            try{
                var dataNascimento = parseDataNascimento(txtNascimento)
                var dataAtual = LocalDate.now()

                if( dataNascimento.isEqual(dataAtual) || dataNascimento.isAfter(dataAtual) )
                    throw DateTimeException("Nascimento é maior que data atual")

            }catch(e: Exception){
                Log.e("REGRADENEGOCIO", e.message.toString())
                return false
            }
            return true
        }

        fun parseDataNascimento(txtNascimento: String) : LocalDate{
            var dtNascimento = LocalDate.now()
            try{
                var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                dtNascimento = LocalDate.parse(txtNascimento, formatter)
            }catch(e: Exception){
                Log.e("REGRADENEGOCIO", e.message.toString())
            }

            return dtNascimento
        }
    }


}