package com.example.projetoidade.model

import android.util.Log
import android.widget.Toast
import java.time.DateTimeException
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class Pessoa(var txtNome : String, var dtNascimento : LocalDate) {

    var id: Int? = null
    lateinit var nome: String
    lateinit var dataNascimento: LocalDate
    lateinit var periodo: Period
    lateinit var dataConsulta : LocalDate

    init{
        this.nome = txtNome
        this.dataNascimento = dtNascimento
        this.dataConsulta = LocalDate.now()
        this.periodo = this.calcularIdade(dtNascimento, this.dataConsulta)
    }

    constructor(txtNome : String, dtNascimento : LocalDate, codigo : Int, dtConsulta : LocalDate) : this(txtNome, dtNascimento){
        this.id = codigo
        this.dataConsulta = dtConsulta
        this.periodo = this.calcularIdade(dtNascimento, dtConsulta)
    }

    private fun calcularIdade(dtNascimento: LocalDate, dtConsulta: LocalDate): Period {
        return Period.between(dtNascimento, dtConsulta)
    }

    private fun validaNome(nome: String) : Boolean{
        if( nome.isEmpty() ) {
            Log.e("REGRADENEGOCIO", "Nome não está preenchido")
            return false
        }

        return true
    }

    override fun toString(): String {
        return "Pessoa(id='$id', nome='$nome', dataNascimento=$dataNascimento, periodo=${periodo.years} anos)"
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