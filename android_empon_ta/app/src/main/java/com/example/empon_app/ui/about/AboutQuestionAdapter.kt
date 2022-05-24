package com.example.empon_app.ui.about

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.empon_app.ui.MainActivity
import com.example.empon_app.R
import com.example.empon_app.model.Question
import kotlinx.android.synthetic.main.card_question.view.*

class AboutQuestionAdapter(private val questionList: ArrayList<Question>) :
    RecyclerView.Adapter<AboutQuestionAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_question, parent, false)

        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        Log.d(MainActivity.TAG, "masuk")

        holder.view.textViewQuestion.text = questionList[position].title

        holder.view.textViewQuestion.setOnClickListener {
            val questionTitle = questionList[position].title!!
            val questionDescription = questionList[position].description!!
            val action = FAQFragmentDirections.actionToNavigationDetailFaqFragment(
                questionTitle,
                questionDescription
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        Log.d(MainActivity.TAG, "questionList.size = ${questionList.size}")
        return questionList.size
    }

    fun updateQuestionList(newQuestionList: List<Question>) {
        questionList.clear()
        questionList.addAll(newQuestionList)
        notifyDataSetChanged()
    }
}