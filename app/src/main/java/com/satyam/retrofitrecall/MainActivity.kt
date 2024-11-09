package com.satyam.retrofitrecall

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.satyam.retrofitrecall.apiwork.AlbumService
import com.satyam.retrofitrecall.apiwork.RetrofitInstance
import com.satyam.retrofitrecall.databinding.ActivityMainBinding
import com.satyam.retrofitrecall.model.AlbumsResponse
import com.satyam.retrofitrecall.model.AlbumsResponseItem
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val retService : AlbumService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
         val responseLiveData : LiveData<Response<AlbumsResponse>> = liveData {
             val response = retService.getSortedAlbums(3)
             emit(response)
         }

        responseLiveData.observe(this,Observer{
         val albumList = it.body()?.listIterator()
         if(albumList!=null){
             while (albumList.hasNext()){
                 val albumsItem = albumList.next()
                 val result = "Albums Title : ${albumsItem.title} \n" +
                                "Album Id : ${albumsItem.id} \n" +
                                "UserId : ${albumsItem.userId}\n\n"
                 binding.txtText.append(result)
             }
         }
        })

        val pathResponse :LiveData<Response<AlbumsResponseItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this,Observer{
            val title = it.body()?.title
            Toast.makeText(applicationContext,title,Toast.LENGTH_LONG).show()
        })
    }
}