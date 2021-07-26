package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        metodoGet()
        metodoPost()
    }
    fun metodoGet(){
        "https://jsonplaceholder.typicode.com/posts/1"
            .httpGet()
            .responseString { req, resm ,result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("http-klaxon","error ${error}")
                    }
                    is Result.Success ->{
                        val getSrting = result.get()
                        Log.i("http-klaxon","getString ${getSrting}")
                         //"https://jsonplaceholder.typicode.com/posts/1"
                        //"https://jsonplaceholder.typicode.com/posts/1"

                        val post = Klaxon()
                            .parse<IPostHttp>(getSrting)
                            //.parseArray<IPostHttp>(getString)
                        Log.i("http-klaxon", "${post?.body}")
                    }

                }

            }
    }
    fun metodoPost(){
        val parametros: List<Pair<String, *>> = listOf(
            "title" to "titulo moviles",
            "body" to "descripcion moviles",
            "userId" to 1
        )
        "https://jsonplaceholder.typicode.com/posts"
            .httpPost(parametros)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        val post = Klaxon()
                            .parse<IPostHttp>(postString)
                        Log.i("http-klaxon", "${post?.title}")
                    }
                }
            }
    }
}