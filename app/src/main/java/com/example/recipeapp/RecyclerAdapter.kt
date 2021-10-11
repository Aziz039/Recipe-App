package com.example.recipeapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private val recipes: ArrayList<ArrayList<String>>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView
        var tv_author: TextView
        var tv_ingredients: String = ""
        var tv_instructions: String = ""
        var cv_card: CardView

        init {
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_author = itemView.findViewById(R.id.tv_author)
            cv_card = itemView.findViewById(R.id.cv_card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recipe_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_title.text = "Title: ${recipes[position][0]}"
        holder.tv_author.text = "Author: ${recipes[position][1]}"
        holder.tv_ingredients = "Ingredients: ${recipes[position][2]}"
        holder.tv_instructions = "Instructions: ${recipes[position][3]}"

        holder.cv_card.setOnClickListener { customAlert( holder, holder.tv_ingredients, holder.tv_instructions, recipes[position][0], recipes[position][1]) }
    }

    override fun getItemCount() = recipes.size

    private fun customAlert(main: RecyclerAdapter.ViewHolder, ingredients: String, instructions:String, title: String, author: String){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(main.cv_card.context)

        // here we set the message of our alert dialog
        dialogBuilder.setMessage("$ingredients \n$instructions")
            // positive button text and action
            .setPositiveButton("Continue", DialogInterface.OnClickListener {
                    dialog, id -> {}
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("$title by $author")

        // show alert dialog
        alert.show()
    }
}