package dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.android.play.core.install.model.UpdateAvailability
import model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: User)


}