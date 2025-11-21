package es.elorrieta.app.exameneval1.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.elorrieta.app.exameneval1.room.entity.User

@Dao
interface UserDAO {


    @Query("SELECT * FROM usuarios")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM usuarios WHERE id = :idInput")
    fun getUserById(idInput: Int): User

    @Query("SELECT * FROM usuarios WHERE login = :name AND pass = :password")
    suspend fun login(name: String, password: String): User?

    @Insert
    fun insertUser(vararg empleado: User)

    @Update
    fun updateUser(usuario: User)

    @Delete
    fun deleteEmpleado(empleado: User)

    @Query("DELETE FROM usuarios")
    fun deleteAllUsers()
}
