package es.elorrieta.app.exameneval1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import es.elorrieta.app.exameneval1.room.RoomDB
import es.elorrieta.app.exameneval1.room.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Int

class MainActivity : AppCompatActivity() {


    private lateinit var db : RoomDB
    private lateinit var user : EditText
    private lateinit var password : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = RoomDB(this)
        lifecycleScope.launch(Dispatchers.IO) {
            var users = db.getUserDAO().getAllUsers()
            if (users.isEmpty()) {
                db.getUserDAO().insertUser(

                    User(
                        id = 1,
                        login = "user",
                        pass = "user",
                        nombre = "Juan",
                        empresa = "MEGASOFT"
                    ),
                    User(
                        id = 2,
                        login = "admin",
                        pass = "admin",
                        nombre = "Ana",
                        empresa = "ELORRIETA"
                    ),
                )
            }
            users = db.getUserDAO().getAllUsers()
            Log.i("USERS", "User:")
            users.forEach { Log.i("USER", "$it") }
        }

        db = RoomDB(this)
        user = findViewById(R.id.editTextLoginActivityMain)
        password = findViewById(R.id.editTextPassActivityMain)

        findViewById<Button>(R.id.buttonLoginActivityMain).setOnClickListener {
            val username = user.text.toString().trim()
            val contrasenya: String = password.text.toString().trim()

            if (username.isEmpty() || contrasenya.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val usuario = withContext(Dispatchers.IO) {
                        db.getUserDAO().login(username, contrasenya)
                    }

                    if (usuario != null) {
                        Toast.makeText(
                            this@MainActivity,
                            "Bienvenido ${usuario.nombre}",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@MainActivity, UserActivity::class.java)
                        intent.putExtra("id", usuario.id)
                        intent.putExtra("nombre", usuario.nombre)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Usuario o contraseña incorrectos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}