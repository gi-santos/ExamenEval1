package es.elorrieta.app.exameneval1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Aplicar animación al logo
        val logo = findViewById<android.widget.ImageView>(R.id.logo)
        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        logo.startAnimation(animation)

        // Navegar a MainActivity después de 3 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}