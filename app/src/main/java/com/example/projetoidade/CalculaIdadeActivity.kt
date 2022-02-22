package com.example.projetoidade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.time.DateTimeException
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
            var dataAtual = LocalDate.now()

            if( dataNascimento.isEqual(dataAtual) || dataNascimento.isAfter(dataAtual) )
                throw DateTimeException("Nascimento é maior que data atual")

        }catch(e: Exception){
            Log.e("REGRADENEGOCIO", e.message.toString())
            return false
        }
        return true
    }

    fun validaNome() : Boolean{
        if( inputNome.text.isEmpty() ) {
            Log.e("REGRADENEGOCIO", "Nome não está preenchido")
            return false
        }

        return true
    }

    fun calcularIdade(): Period{
        var dataAtual = LocalDate.now()
        var periodo = Period.between(dataNascimento, dataAtual)
        return periodo
    }

    fun acaoCalcular(view: View){
        if( !validaNome() ){
            inputNome.requestFocus();
            txtResultado.setText("Resultado:")
            Toast.makeText(applicationContext, "ERRO: Preencha o nome!", Toast.LENGTH_SHORT).show()
            return
        }

        if( validaData() ){
            var periodo = calcularIdade()
            txtResultado.setText("Resultado:\nA idade do " + inputNome.text.toString() + " é: " + periodo.years + " anos, " + periodo.months + " meses e " + periodo.days + " dias.")
        }else{
            txtResultado.setText("Resultado:")
            inputData.requestFocus();
            Toast.makeText(applicationContext, "ERRO: Data Inválida!", Toast.LENGTH_SHORT).show()
        }
    }

    fun acaoSair(view: View){
        finish()
    }


}