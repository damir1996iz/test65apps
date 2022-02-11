package com.damikkg.test65appsfragments.domain.base

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object DatabaseError : Failure()
    object UnknownError: Failure()
    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}