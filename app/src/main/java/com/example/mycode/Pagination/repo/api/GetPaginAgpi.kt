package com.example.mycode.Pagination.repo.api

import com.example.mycode.Pagination.model.DataModles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface GetPagingApi {
    @GET(com.example.mycode.Pagination.Url.BASE_URL)
    fun getPage():Call<List<DataModles>>
}