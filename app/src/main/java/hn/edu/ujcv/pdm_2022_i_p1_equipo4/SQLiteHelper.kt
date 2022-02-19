package hn.edu.ujcv.pdm_2022_i_p1_equipo4

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(

    context, "proyect.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val crearTabla = "CREATE TABLE proyect " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, autor TEXT, fecha TEXT, editorial TEXT)"
        db!!.execSQL(crearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS proyect"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun agregarDatos(nombre: String, autor: String, fecha: String, editorial: String) {
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("autor", autor)
        datos.put("fecha", fecha)
        datos.put("editorial", editorial)

        val db = this.writableDatabase
        db.insert("proyect", null, datos)
        db.close()
    }

    fun editarDatos(id: Int, nombre: String, autor: String, fecha: String, editorial: String) {
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("autor", autor)
        datos.put("fecha", fecha)
        datos.put("editorial", editorial)

        val db = this.writableDatabase
        db.update("proyect", datos,"_id = ?", args)
        db.close()
    }

    fun borrarDatos(id: Int): Int {
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        val borrado = db.delete("proyect", "_id = ?", args)
        db.close()
        return borrado
    }
}