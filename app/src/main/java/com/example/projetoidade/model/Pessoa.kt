package com.example.projetoidade.model

import android.util.Log
import android.widget.Toast
import java.time.DateTimeException
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class Pessoa {

    lateinit var nome: String
    lateinit var dataNascimento: LocalDate
    lateinit var periodo: Period

    fun Pessoa(nome: String, nascimento: String){
        if( !validaNome(nome) ){
            Log.e("REGRADENEGOCIO", "Nome inválido")
            throw Exception("Nome da pessoa inválido")
        }else{
            this.nome = nome
        }

        if( !validaData(nascimento) ){
            Log.e("REGRADENEGOCIO", "Data Inválida")
            throw Exception("Data de nascimento inválida")
        }else{
            this.periodo = calcularIdade(nascimento)
        }
    }

    private fun calcularIdade(nascimento: String): Period {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var dataNascimento = LocalDate.parse(nascimento, formatter)
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

    private fun validaData(nascimento: String) : Boolean{
        try{
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            var dataNascimento = LocalDate.parse(nascimento, formatter)
            var dataAtual = LocalDate.now()

            if( dataNascimento.isEqual(dataAtual) || dataNascimento.isAfter(dataAtual) )
                throw DateTimeException("Nascimento é maior que data atual")

        }catch(e: Exception){
            Log.e("REGRADENEGOCIO", e.message.toString())
            return false
        }
        return true
    }
}