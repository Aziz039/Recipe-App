package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewRecipe: AppCompatActivity() {
    private lateinit var clAdd: ConstraintLayout
    private lateinit var ti_title: TextInputEditText
    private lateinit var ti_author: TextInputEditText
    private lateinit var ti_ingredients: TextInputEditText
    private lateinit var ti_instructions: TextInputEditText
    private lateinit var bt_save: Button
    private lateinit var bt_view: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_recipe_activity)
        initVars()
    }

    private fun initVars() {
        clAdd = findViewById(R.id.clAdd)
        ti_title = findViewById(R.id.ti_title)
        ti_author = findViewById(R.id.ti_author)
        ti_ingredients = findViewById(R.id.ti_ingredients)
        ti_instructions = findViewById(R.id.ti_instructions)
        bt_save = findViewById(R.id.bt_save)
        bt_view = findViewById(R.id.bt_view)

        bt_save.setOnClickListener { handleSave() }
        bt_view.setOnClickListener { handleView() }
    }

    private fun handleSave() {
        if (ti_title.text.isNullOrBlank() ||
            ti_author.text.isNullOrBlank() ||
            ti_ingredients.text.isNullOrBlank() ||
            ti_instructions.text.isNullOrBlank() ){
            Toast.makeText(clAdd.context, "Empty field..", Toast.LENGTH_LONG).show()
        } else {
            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
            if (apiInterface != null) {
                var newRecipe = ResultModel.ResultValue(ti_title.text.toString(),
                    ti_author.text.toString(), ti_ingredients.text.toString(),
                    ti_instructions.text.toString())
                Log.d("AddRecipe", "I'm here")
                apiInterface.addUser(newRecipe).enqueue(object : Callback<ResultModel.ResultValue> {
                    override fun onResponse(
                        call: Call<ResultModel.ResultValue>,
                        response: Response<ResultModel.ResultValue>
                    ) {
                        Log.d("AddRecipe", "I'm there")
                        ti_author.setText("")
                        ti_author.setText("")
                        ti_ingredients.setText("")
                        ti_instructions.setText("")
                        handleView()
                        Log.d("AddRecipe", "why")
                    }
                    override fun onFailure(call: Call<ResultModel.ResultValue>, t: Throwable) {
                        Toast.makeText(clAdd.context, "Connection failed..", Toast.LENGTH_SHORT).show()
                        handleView()
                    }
                })
            }
        }
    }

    private fun handleView() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Log.d("AddRecipe", "strange")
    }
}