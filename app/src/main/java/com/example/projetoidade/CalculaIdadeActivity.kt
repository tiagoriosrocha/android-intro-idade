package com.example.projetoidade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class CalculaIdadeActivity : AppCompatActivity() {

    lateinit var inputNome: EditText
    lateinit var inputData: EditText
    lateinit var btnCalcular: Button
    lateinit var txtResultado: TextView
    lateinit var btnSair: Button
    lateinit var dataNascimento: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calcula_idade_activity)
        inicializaComponentes()
    }

    private fun inicializaComponentes() {
        inputNome = findViewById(R.id.inputNome)
        inputData = findViewById(R.id.inputData)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnSair = findViewById(R.id.btnSair)
        txtResultado = findViewById(R.id.txtResultado)
    }

    fun validaData() : Boolean{
        try{
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            dataNascimento = LocalDate.parse(inputData.text.toString(), formatter)
        }catch(e: DateTimeParseException){
            return false
        }
        return true
    }

    fun validaNome() : Boolean{
        if( inputNome.text.toString() == "" ) return false
        return true
    }

    fun calcularIdade(): Period{
        var dataAtual = LocalDate.now()
        var periodo = Period.between(dataNascimento, dataAtual)
        return periodo
    }

    fun acaoCalcular(view: View){
        if( validaData() || validaNome() ){
            var periodo = calcularIdade()
            txtResultado.setText("Resultado:\nA idade do " + inputNome.text.toString() + " é: " + periodo.years + " anos, " + periodo.months + " meses e " + periodo.days + " dias.")
        }else{
            txtResultado.setText("ERRO: Preencha todos os dados")
        }
    }

    fun acaoSair(view: View){
        finish()
    }
}