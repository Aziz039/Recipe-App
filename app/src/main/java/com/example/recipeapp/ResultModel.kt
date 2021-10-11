package com.example.recipeapp

import com.google.gson.annotations.SerializedName

class ResultModel {
    var data: List<ResultValue>? = null

    class ResultValue {
        @SerializedName("title")
        var title: String? = null

        @SerializedName("author")
        var author: String? = null

        @SerializedName("ingredients")
        var ingredients: String? = null

        @SerializedName("instructions")
        var instructions: String? = null

        constructor(name: String?, location: String?, ingredients: String?, instructions: String?) {
            this.title = name
            this.author = location
            this.ingredients = ingredients
            this.instructions = instructions
        }
    }
}
