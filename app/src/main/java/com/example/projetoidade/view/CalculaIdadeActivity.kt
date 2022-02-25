package com.example.projetoidade.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.projetoidade.R
import com.example.projetoidade.controller.PessoaController
import com.example.projetoidade.model.Pessoa
import java.time.DateTimeException
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class CalculaIdadeActivity : AppCompatActivity() {

    lateinit var inputNome: EditText
    lateinit var inputData: EditText
    lateinit var btnCalcular: Button
    lateinit var txtResultado: TextView
    lateinit var btnSair: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calcula_idade_activity)
        inicializaComponentes()
    }

    override fun onPause(){
        super.onPause()
        val prefs = getSharedPreferences("app_idade", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("txtNome",inputNome.text.toString())
        editor.putString("txtNascimento", inputData.text.toString())
        editor.apply()
        Log.i("INFORMACAO","Salvando os dados da interface")
    }

    override fun onResume(){
        super.onResume()
        val prefs = getSharedPreferences("app_idade", Context.MODE_PRIVATE)
        var nome = prefs.getString("txtNome","")
        var nasc = prefs.getString("txtNascimento", "")
        inputNome.setText(nome)
        inputData.setText(nasc)
        Log.i("INFORMACAO","Recuperando os dados da interface")
    }

    private fun inicializaComponentes() {
        inputNome = findViewById(R.id.inputNome)
        inputData = findViewById(R.id.inputData)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnSair = findViewById(R.id.btnSair)
        txtResultado = findViewById(R.id.txtResultado)
    }

    fun acaoCalcular(view: View){

        var nome = inputNome.text.toString()
        var nascimento = inputData.text.toString()
        var pessoa : Pessoa

        if( nome.isEmpty()){
            inputNome.setError("Campo Obrigatório!")
            inputNome.requestFocus();
            txtResultado.setText("Resultado:")
            Toast.makeText(applicationContext, "ERRO: Preencha o nome!", Toast.LENGTH_SHORT).show()
            return
        }

        if( !Pessoa.validaData(nascimento) ){
            txtResultado.setText("Resultado:")
            inputData.requestFocus();
            inputData.setError("Campo Inválido!")
            Toast.makeText(applicationContext, "ERRO: Data Inválida!", Toast.LENGTH_SHORT).show()
            return
        }

        var dtNascimento : LocalDate = Pessoa.parseDataNascimento(nascimento)
        pessoa = Pessoa(nome, dtNascimento)
        var idade = pessoa.periodo
        txtResultado.setText("Resultado:\nA idade do " + inputNome.text.toString() + " é: " + idade.years + " anos, " + idade.months + " meses e " + idade.days + " dias.")

        var pessoaController = PessoaController(this)
        pessoaController.salvarRegistro(pessoa)
        //pessoaController.buscarRegistros(this)
    }

    fun acaoSair(view: View){
        finish()
    }

    fun acaoExibirHistorico(view: View){
        val intent = Intent(this, HistoricoActivity::class.java)
        startActivity(intent)
        finish()
    }


}