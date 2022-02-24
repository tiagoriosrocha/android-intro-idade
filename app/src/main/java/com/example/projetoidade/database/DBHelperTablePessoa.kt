package com.example.projetoidade.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.projetoidade.model.Pessoa
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class DBHelperTablePessoa(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "db_pessoa", factory, 1) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE tb_pessoa (" +
                "pessoa_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT," +
                "nascimento TEXT," +
                "idade_atual TEXT," +
                "data_consulta TEXT)")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS tb_pessoa")
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addPessoa(nome : String, nascimento : String, idadeAtual : String, dataConsulta : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put("nome", nome)
        values.put("nascimento", nascimento)
        values.put("idade_atual", idadeAtual)
        values.put("data_consulta", dataConsulta)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert("tb_pessoa", null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getPessoas() : ArrayList<Pessoa>{
        var listaPessoa = ArrayList<Pessoa>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tb_pessoa", null)
        if( cursor != null ){
            if(cursor.moveToFirst()){
                do{
                    val id = cursor.getInt(cursor.getColumnIndex("pessoa_id"))
                    val nome = cursor.getString(cursor.getColumnIndex("nome"))
                    val txtNascimento = cursor.getString(cursor.getColumnIndex("nascimento"))
                    val txtDataConsulta = cursor.getString(cursor.getColumnIndex("data_consulta"))

                    var dtNascimento = LocalDate.parse(txtNascimento)
                    val dtConsulta = LocalDate.parse(txtDataConsulta)
                    val pessoa = Pessoa(nome,dtNascimento,id, dtConsulta)

                    listaPessoa.add(pessoa)
                }while(cursor.moveToNext())
            }
        }
        cursor.close()
        return listaPessoa
    }
}