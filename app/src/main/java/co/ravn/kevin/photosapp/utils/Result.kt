package co.ravn.kevin.photosapp.utils

sealed class Result<out T> {
    data class Ok<out T>(val value: T) : Result<T>()
    data class Err<out E>(val error: E) : Result<Nothing>()
}

fun <T> T.toResult(): Result<T> = Result.Ok(this)
fun <E : Throwable> E.toResult(): Result<Nothing> = Result.Err(this)

suspend inline fun <T, R> T.runToResult(crossinline block: suspend T.() -> R): Result<R> {
    return try {
        Result.Ok(block())
    } catch (e: Throwable) {
        Result.Err(e)
    }
}