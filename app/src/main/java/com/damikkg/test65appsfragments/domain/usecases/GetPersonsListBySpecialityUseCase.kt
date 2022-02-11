package com.damikkg.test65appsfragments.domain.usecases

import android.util.Log
import com.damikkg.test65appsfragments.domain.base.Either
import com.damikkg.test65appsfragments.domain.base.Failure
import com.damikkg.test65appsfragments.domain.base.UseCase
import com.damikkg.test65appsfragments.domain.contracts.IRepository
import com.damikkg.test65appsfragments.domain.models.Person
import com.damikkg.test65appsfragments.domain.models.Speciality
import javax.inject.Inject

class GetPersonsListBySpecialityUseCase @Inject constructor(
    private val repo: IRepository
) : UseCase<List<Person>, Speciality>(){
    override suspend fun run(params: Speciality): Either<Failure, List<Person>> {
        return try {
            Either.Right(repo.getPersonsListBySpeciality(params))
        } catch (e:Exception)
        {
            Log.e("GetPersonsList",e.toString())
            Either.Left(Failure.DatabaseError)
        }
    }
}