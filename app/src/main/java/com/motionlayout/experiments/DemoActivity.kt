package com.motionlayout.experiments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

data class MotionExperiment(
    val number: String,
    val name: String,
    val caption: String,
    val activity: KClass<out Activity>,
    val highlight: Boolean = false
)

private val experiments = listOf(
    MotionExperiment(
        "Experiment 1",
        "Floating Action Menu animation",
        "Learn how to build a Floating Action Menu animation with Motion Layout.",
        MotionFabMenuActivity::class
    )
)

class DemoActivity : AppCompatActivity(R.layout.activity_demo) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = ExperimentsAdapter(experiments)
    }
}

class ExperimentsAdapter(private val data: List<MotionExperiment>) :
    RecyclerView.Adapter<ExperimentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperimentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ExperimentsViewHolder(view as CardView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ExperimentsViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

class ExperimentsViewHolder(private val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
    private val header: TextView = cardView.findViewById(R.id.header)
    private val description: TextView = cardView.findViewById(R.id.description)
    private val caption: TextView = cardView.findViewById(R.id.caption)

    fun bind(motionExperiment: MotionExperiment) {
        header.text = motionExperiment.number
        description.text = motionExperiment.name
        caption.text = motionExperiment.caption
        val context = cardView.context
        cardView.setOnClickListener {
            val intent = Intent(context, motionExperiment.activity.java)
            context.startActivity(intent)
        }
        val color = if (motionExperiment.highlight) {
            ContextCompat.getColor(context, R.color.colorSecondaryLight)
        } else {
            ContextCompat.getColor(context, R.color.colorPrimaryText)
        }
        header.setTextColor(color)
        description.setTextColor(color)
    }

}
