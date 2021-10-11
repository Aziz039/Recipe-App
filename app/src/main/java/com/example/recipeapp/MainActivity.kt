package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var clMain: ConstraintLayout
    private lateinit var rv_recipes: RecyclerView
    private lateinit var bt_newRecipe: Button
    val recipes = ArrayList<ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVars()

        fecthDataFromAPI()


        bt_newRecipe.setOnClickListener {
            val intent = Intent(this, NewRecipe::class.java)
            startActivity(intent)
        }
    }

    private fun initVars() {
        clMain = findViewById(R.id.clMain)
        rv_recipes = findViewById(R.id.rv_recipes)
        bt_newRecipe = findViewById(R.id.bt_newRecipe)
    }

    private fun addDataToUI(allRecipes: ArrayList<ArrayList<String>>) {
        rv_recipes.adapter = RecyclerAdapter(allRecipes)
        rv_recipes.layoutManager = LinearLayoutManager(this)
    }

    private fun fecthDataFromAPI() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<List<ResultModel.ResultValue>> = apiInterface!!.getRecipes()

        call?.enqueue(object : Callback<List<ResultModel.ResultValue>> {
            override fun onResponse(
                call: Call<List<ResultModel.ResultValue>>,
                response: Response<List<ResultModel.ResultValue>>
            ) {
                for(recipe in response.body()!!){
                    val title = recipe.title.toString()
                    val author = recipe.author.toString()
                    val ingredients = recipe.ingredients.toString()
                    val instructions = recipe.instructions.toString()
                    val tempRecipe = arrayListOf<String>(title, author, ingredients, instructions)
                    recipes.add(tempRecipe)
                }
                addDataToUI(recipes)
            }

            override fun onFailure(call: Call<List<ResultModel.ResultValue>>, t: Throwable) {
                Log.d("MainActivityAPI", "Connection failed.. $t")
                Toast.makeText(clMain.context, "Connection failed..", Toast.LENGTH_SHORT).show()
                call.cancel()
            }
        })

    }
}