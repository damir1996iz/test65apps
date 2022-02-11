package com.damikkg.test65appsfragments.di

import android.content.Context
import androidx.room.Room
import com.damikkg.test65appsfragments.data.local.LocalCacheImp
import com.damikkg.test65appsfragments.data.local.LocalSourceImp
import com.damikkg.test65appsfragments.data.local.dao.LocalDAO
import com.damikkg.test65appsfragments.data.local.dao.LocalDB
import com.damikkg.test65appsfragments.domain.contracts.ILocalCache
import com.damikkg.test65appsfragments.domain.contracts.ILocalSource
import com.damikkg.test65appsfragments.domain.contracts.IRepository
import com.damikkg.test65appsfragments.domain.repo.RepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun getRoomDB(@ApplicationContext context: Context): LocalDB
    {

        return Room.databaseBuilder(context, LocalDB::class.java, "local")
            .build()
    }

    @Provides
    @Singleton
    fun getRoomDAO(db: LocalDB) = db.dao()

    @Provides
    @Singleton
    fun getLocalCache(localDAO: LocalDAO) : ILocalCache = LocalCacheImp(localDAO)

    @Provides
    @Singleton
    fun getLocalSource(localDAO: LocalDAO) : ILocalSource = LocalSourceImp(localDAO)

    @Provides
    @Singleton
    fun getRepo(localSource: ILocalSource) : IRepository = RepoImp(localSource)

}