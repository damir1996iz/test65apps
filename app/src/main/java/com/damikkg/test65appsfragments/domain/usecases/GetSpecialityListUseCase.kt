package com.damikkg.test65appsfragments.domain.usecases

import android.util.Log
import com.damikkg.test65appsfragments.domain.base.Either
import com.damikkg.test65appsfragments.domain.base.Failure
import com.damikkg.test65appsfragments.domain.base.UseCase
import com.damikkg.test65appsfragments.domain.contracts.IRepository
import com.damikkg.test65appsfragments.domain.models.Speciality
import javax.inject.Inject

class GetSpecialityListUseCase @Inject constructor(
    private val repo: IRepository
) : UseCase<List<Speciality>, UseCase.None>(){
    override suspend fun run(params: None): Either<Failure, List<Speciality>> {
        return try {
            Either.Right(repo.getSpecialityList())
        } catch (e:Exception)
        {
            Log.e("GetSpecialityList", e.toString())
            Either.Left(Failure.DatabaseError)
        }
    }
}