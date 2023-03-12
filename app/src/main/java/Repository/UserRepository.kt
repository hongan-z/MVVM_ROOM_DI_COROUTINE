package Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dao.UserDao
import model.User
import javax.inject.Inject

//class UserRepository(val userDao: UserDao)
class UserRepository @Inject constructor(val userDao: UserDao) {

   val readAllData: LiveData<List<User>> = userDao.readAllData()


    suspend fun addUser(user: User){
        userDao.addUser(user)

    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUsers()
    }


}