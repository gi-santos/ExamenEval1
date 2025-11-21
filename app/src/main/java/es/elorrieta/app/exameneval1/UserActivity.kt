package es.elorrieta.app.exameneval1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import es.elorrieta.app.exameneval1.room.RoomDB
import es.elorrieta.app.exameneval1.room.adapter.UserAdapter
import es.elorrieta.app.exameneval1.room.entity.User

class UserActivity : AppCompatActivity() {

    private lateinit var editTextTextLoginActivityUser: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var db : RoomDB
    private lateinit var adaptador: UserAdapter
    private val lista: MutableList<User> = mutableListOf()


    private var id: Int = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //coge la info de la pantalla anterior
        id = intent.getIntExtra("id", 0)

        val nombre = intent.getStringExtra("nombre")
        val textView = findViewById<TextView>(R.id.editTextTextLoginActivityUser)
        textView.text = nombre



        //intent para el boton cancelar
        findViewById<Button>(R.id.buttonCancelarActivityUser).setOnClickListener {
            val intent = Intent(this@UserActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.buttonRegistrarActivityUser).setOnClickListener {
            val intent2 = Intent(this@UserActivity, NewUserActivity::class.java)
            startActivity(intent2)
            finish()
        }

        if (id == 0) {
            finish()
        }

    }
}