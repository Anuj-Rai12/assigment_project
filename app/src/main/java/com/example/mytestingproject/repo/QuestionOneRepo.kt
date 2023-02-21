package com.example.mytestingproject.repo

import com.example.mytestingproject.api.MarvelApiInterface
import com.example.mytestingproject.utils.ApiResponse
import com.example.mytestingproject.utils.buildApi
import com.example.mytestingproject.utils.getEmojiByUnicode
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit

class QuestionOneRepo(retrofit: Retrofit) {

    private val api = buildApi<MarvelApiInterface>(retrofit)


    fun getMarvelHeroes() = flow {
        emit(ApiResponse.Loading("Please Wait Finding Heroes ${getEmojiByUnicode(0x1F575)}"))
        val data = try {
            val res = api.getMarvelHeroes()
            if (res.isSuccessful) {
                res.body()?.let { data ->
                    ApiResponse.Success(data)
                } ?: ApiResponse.Error(
                    "Cannot Generate Response ${getEmojiByUnicode(0x1F615)}",
                    null
                )
            } else {
                ApiResponse.Error("Cannot Process the Request  ${getEmojiByUnicode(0x1F615)}", null)
            }
        } catch (e: Exception) {
            ApiResponse.Error(null, e)
        }
        emit(data)
    }.flowOn(IO)
}
