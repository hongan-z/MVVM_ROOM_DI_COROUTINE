package di

import Repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dao.UserDao
import viewModel.UserViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerDao(userDao: UserDao):UserRepository = UserRepository(userDao)


//    @Singleton
//    @Provides
//    fun provideUserRepo(userRepo: UserRepository):UserViewModel = UserViewModel(userRepo)
}

