package hn.edu.ujcv.pdm_2022_i_p1_equipo4

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.CursorAdapter
import hn.edu.ujcv.pdm_2022_i_p1_equipo4.databinding.ActivityListaBinding
import hn.edu.ujcv.pdm_2022_i_p1_equipo4.databinding.ItemListviewBinding


class ActivityListaLibros : AppCompatActivity() {

    lateinit var binding: ActivityListaBinding
    lateinit var proyectDBHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        proyectDBHelper = SQLiteHelper(this)

        val db : SQLiteDatabase = proyectDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM proyect",
            null)

        val adaptador = CursorAdapterListView(this, cursor)
        binding.lvDatos.adapter = adaptador
        db.close()
    }

    inner class CursorAdapterListView(context: Context, cursor: Cursor) :
        CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER) {

        override fun newView(context: Context?,
                             cursor: Cursor?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            return inflater.inflate(R.layout.item_listview,
                parent, false )
        }

        override fun bindView(view: View?,
                              context: Context?, cursor: Cursor?) {
            val bindingItems = ItemListviewBinding.bind(view!!)
            bindingItems.tvItemNombre.text = cursor!!.getString(1)
            bindingItems.tvItemAutor.text = cursor!!.getString(2)
            bindingItems.tvItemFecha.text = cursor!!.getString(3)
            bindingItems.tvItemEditorial.text = cursor!!.getString(4)

            view.setOnClickListener {
                Toast.makeText(this@ActivityListaLibros,
                    "${bindingItems.tvItemNombre.text}, " +
                    "${bindingItems.tvItemAutor.text}, " +
                    "${bindingItems.tvItemFecha.text}, " +
                    "${bindingItems.tvItemEditorial.text}",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}