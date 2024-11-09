package com.satyam.retrofitrecall.apiwork

import com.satyam.retrofitrecall.model.AlbumsResponse
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<AlbumsResponse>
}