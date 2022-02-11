package com.damikkg.test65appsfragments.domain.usecases

import android.util.Log
import com.damikkg.test65appsfragments.domain.base.Either
import com.damikkg.test65appsfragments.domain.base.Failure
import com.damikkg.test65appsfragments.domain.base.UseCase
import com.damikkg.test65appsfragments.domain.contracts.ILocalCache
import com.damikkg.test65appsfragments.domain.contracts.INetworkSource
import javax.inject.Inject

class UpdateDatabaseUseCase @Inject constructor(
    private val localCacheImp: ILocalCache,
    private val remoteSourceImp: INetworkSource
) : UseCase<Boolean, UseCase.None>(){
    override suspend fun run(params: None): Either<Failure, Boolean> {
        val employersList = try
        {
            remoteSourceImp.getEmployersList()
        } catch (e:Exception)
        {
            Log.e("UpdateDatabaseUseCase", e.toString())
            return Either.Left(Failure.NetworkConnection)
        }

        return try {
            val specialityList = employersList.map {
                it.speciality
            }
                .flatten()
                .distinctBy { it.id }

            specialityList.forEach {
                localCacheImp.insertSpeciality(it)
            }
            employersList.forEach {
                localCacheImp.insertPerson(it)
            }

            Either.Right(true)

        } catch (e:Exception)
        {
            Log.e("UpdateDatabaseUseCase", e.toString())
            return Either.Left(Failure.DatabaseError)
        }
    }
}