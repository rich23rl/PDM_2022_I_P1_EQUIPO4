package hn.edu.ujcv.pdm_2022_i_p1_equipo4


import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import hn.edu.ujcv.pdm_2022_i_p1_equipo4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

        lateinit var binding: ActivityMainBinding
        lateinit var proyectDBHelper: SQLiteHelper

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            proyectDBHelper = SQLiteHelper(this)

            binding.btnAgregar.setOnClickListener {
                if (binding.txtNombre.text.isNotBlank() &&
                    binding.txtAutor.text.isNotBlank()  &&
                    binding.txtFecha.text.isNotBlank() &&
                    binding.txtEditorial.text.isNotBlank()) {
                    proyectDBHelper.agregarDatos(binding.txtNombre.text.toString(),
                        binding.txtAutor.text.toString(),
                        binding.txtFecha.text.toString(),
                        binding.txtEditorial.text.toString())
                    binding.txtNombre.text.clear()
                    binding.txtAutor.text.clear()
                    binding.txtFecha.text.clear()
                    binding.txtEditorial.text.clear()
                    Toast.makeText(this, "Agregado",
                        Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "No se ha podido agregar",
                        Toast.LENGTH_LONG).show()
                }
            }

            binding.btnVisualizar.setOnClickListener {
                binding.txvVisualizar.text = ""
                val db : SQLiteDatabase = proyectDBHelper.readableDatabase
                val cursor = db.rawQuery(
                    "SELECT * FROM proyect",
                    null)

                if (cursor.moveToFirst()) {
                    do {
                        binding.txvVisualizar.append(
                            cursor.getInt(0).toString() + ": ")
                        binding.txvVisualizar.append(
                            cursor.getString(1).toString()+ ", ")
                        binding.txvVisualizar.append(
                            cursor.getString(2).toString()+ ", ")
                        binding.txvVisualizar.append(
                            cursor.getString(3).toString()+ ", ")
                        binding.txvVisualizar.append(
                            cursor.getString(4).toString() + "\n")
                    } while (cursor.moveToNext())
                }

            }

            binding.btnBorrar.setOnClickListener {

                var cantidad = 0

                if (binding.txtId.text.isNotBlank()) {
                    cantidad = proyectDBHelper.borrarDatos(
                        binding.txtId.text.toString().toInt())
                    binding.txtId.text.clear()
                }
                else {
                    Toast.makeText(this,
                        "Datos borrados: $cantidad",
                        Toast.LENGTH_LONG).show()
                }

            }

            binding.btnEditar.setOnClickListener {
                if ( binding.txtId.text.isNotBlank() &&
                    binding.txtNombre.text.isNotBlank() &&
                    binding.txtAutor.text.isNotBlank()  &&
                    binding.txtFecha.text.isNotBlank() &&
                    binding.txtEditorial.text.isNotBlank()) {
                    proyectDBHelper.editarDatos(
                        binding.txtId.text.toString().toInt(),
                        binding.txtNombre.text.toString(),
                        binding.txtAutor.text.toString(),
                        binding.txtFecha.text.toString(),
                        binding.txtEditorial.text.toString())
                    binding.txtNombre.text.clear()
                    binding.txtAutor.text.clear()
                    binding.txtFecha.text.clear()
                    binding.txtEditorial.text.clear()
                    Toast.makeText(this, "Editado",
                        Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this,
                        "Los campos no deben estar vacíos",
                        Toast.LENGTH_LONG).show()
                }
            }

            binding.btnVisualizarLv.setOnClickListener {
                val intentListView = Intent(this, ActivityListaLibros::class.java)
                startActivity(intentListView)
            }



        }
    }