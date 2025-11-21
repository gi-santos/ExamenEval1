package es.elorrieta.app.exameneval1

import android.R.attr.id
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import es.elorrieta.app.exameneval1.room.RoomDB
import es.elorrieta.app.exameneval1.room.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewUserActivity : AppCompatActivity() {

    //inicializo variables que no podrán ser ser nulos

    private lateinit var login: EditText
    private lateinit var nombre: EditText
    private lateinit var pass: EditText
    private lateinit var empresa: EditText

    private lateinit var db: RoomDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //intent pata boton cancelar
        findViewById<Button>(R.id.buttonCancelActivityNewUser).setOnClickListener {
            val intent = Intent(this@NewUserActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 🔹 Inicializar vistas
        login = findViewById(R.id.editTextLoginActivityNewUser)
        pass = findViewById(R.id.editTextPassActivityNewUser)
        nombre = findViewById(R.id.editTextNameActivityNewUser)
        empresa = findViewById(R.id.editTextEnterpriseActivityNewUser)


        //evento para boton de registrar
        findViewById<Button>(R.id.buttonNewActivityNewUser).setOnClickListener {
            registrarNuevoUsuario()

        }
    }

    private fun registrarNuevoUsuario() {
        // Obtener valores de los campos
        val loginText = login.text.toString().trim()
        val passText = pass.text.toString().trim()
        val nombreText = nombre.text.toString().trim()
        val empresaText = empresa.text.toString().trim()

        // Validación
        if (loginText.isBlank() || passText.isBlank() || nombreText.isBlank() || empresaText.isBlank()) {
            Toast.makeText(this, "Completa todos los campos obligatorios", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Crear objeto User
        val usuario = User(
            id = 0, // Room autogenerará el ID si lo configuras así
            login = loginText,
            pass = passText,
            nombre = nombreText,
            empresa = empresaText
        )

        // Insertar en Room (si tu DAO tiene función suspend, usar Coroutine)
        CoroutineScope(Dispatchers.IO).launch {
            db.getUserDAO().insertUser(usuario)

            // Volver al hilo principal para UI
            withContext(Dispatchers.Main) {
                Toast.makeText(this@NewUserActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()

                // Limpiar campos
                login.text.clear()
                pass.text.clear()
                nombre.text.clear()
                empresa.text.clear()

                // Navegar a MainActivity
                val intent = Intent(this@NewUserActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}