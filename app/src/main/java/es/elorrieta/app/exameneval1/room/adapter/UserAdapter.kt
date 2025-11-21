package es.elorrieta.app.exameneval1.room.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.elorrieta.app.exameneval1.R
import es.elorrieta.app.exameneval1.room.dao.UserDAO
import es.elorrieta.app.exameneval1.room.entity.User

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAdapter(
    private val listaUsers: MutableList<User>
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewId: TextView = itemView.findViewById(R.id.id)
        val textViewLogin: TextView = itemView.findViewById(R.id.login)
        val textViewNombre: TextView = itemView.findViewById(R.id.nombre)
        val textViewEmpresa: TextView = itemView.findViewById(R.id.empresa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listaUsers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listaUsers[position]

        holder.textViewId.text = user.id.toString()
        holder.textViewLogin.text = user.login
        holder.textViewNombre.text = user.nombre
        holder.textViewEmpresa.text = user.empresa
    }
}