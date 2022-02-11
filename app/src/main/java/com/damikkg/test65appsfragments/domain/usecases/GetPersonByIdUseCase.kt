package com.damikkg.test65appsfragments.domain.usecases

import android.util.Log
import com.damikkg.test65appsfragments.domain.base.Either
import com.damikkg.test65appsfragments.domain.base.Failure
import com.damikkg.test65appsfragments.domain.base.UseCase
import com.damikkg.test65appsfragments.domain.contracts.IRepository
import com.damikkg.test65appsfragments.domain.models.Person
import javax.inject.Inject

class GetPersonByIdUseCase @Inject constructor(
    private val repo: IRepository
) : UseCase<Person, Long>()
{
    override suspend fun run(params: Long): Either<Failure, Person> {
       return try {
           Either.Right(repo.getPersonById(params))
       } catch (e:Exception)
       {
           Log.e("GetPersonByIdUseCase", e.toString())
           Either.Left(Failure.DatabaseError)
       }
    }
}