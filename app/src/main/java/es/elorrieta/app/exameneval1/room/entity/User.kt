package es.elorrieta.app.exameneval1.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity (tableName = "usuarios")

data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val login: String,
    val pass: String,
    val nombre: String,
    val empresa: String
)
