package com.satyam.retrofitrecall.apiwork

import com.satyam.retrofitrecall.model.AlbumsResponse
import com.satyam.retrofitrecall.model.AlbumsResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<AlbumsResponse>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<AlbumsResponse>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id") albumId: Int): Response<AlbumsResponseItem>
}