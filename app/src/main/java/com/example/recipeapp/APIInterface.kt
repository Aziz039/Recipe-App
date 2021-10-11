package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/recipes/")
    fun getRecipes(): Call<List<ResultModel.ResultValue>>

    @Headers("Content-Type: application/json")
    @POST("/recipes/")
    fun addUser(@Body userData: ResultModel.ResultValue): Call<ResultModel.ResultValue>
}