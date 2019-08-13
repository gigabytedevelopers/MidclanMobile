package com.gigabytedevs.apps.midclan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.gigabytedevs.apps.midclan.R
import com.gigabytedevs.apps.midclan.models.TimelineModel

class TimelineAdapter(val timeLineList: ArrayList<TimelineModel>) : RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val mainImage = view.findViewById<ImageView>(R.id.post_text)
        val title = view.findViewById<AppCompatTextView>(R.id.post_text_title)
        val description = view.findViewById<AppCompatTextView>(R.id.post_text_body)
        val profileImage = view.findViewById<AppCompatImageView>(R.id.user_text_post_profile)
        val name = view.findViewById<AppCompatTextView>(R.id.user_text_username)
        val time = view.findViewById<AppCompatTextView>(R.id.user_text_time)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_text, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return timeLineList.size
    }

    override fun onBindViewHolder(holder: TimelineAdapter.ViewHolder, position: Int) {
        val timeLineModel: TimelineModel = timeLineList[position]

        holder.mainImage.setImageResource(timeLineModel.mainImageResource)
        holder.title.text = timeLineModel.Title
        holder.description.text = timeLineModel.description
        holder.profileImage.setImageResource(timeLineModel.profileImage)
        holder.name.text = timeLineModel.name
        holder.time.text = timeLineModel.time
    }

}
