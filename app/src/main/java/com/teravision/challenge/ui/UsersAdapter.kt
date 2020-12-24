package com.teravision.challenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.teravision.challenge.R
import com.teravision.challenge.domain.entity.UserEntity

class UsersAdapter(
    private val clickListener: OnUserClickListener,
    private val dataSet: List<UserEntity>
) : RecyclerView.Adapter<UsersAdapter.SettingsViewHolder>() {

    class SettingsViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val title: TextView = v.findViewById(R.id.title)
        val container: CardView = v.findViewById(R.id.card)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SettingsViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.users_item, viewGroup, false)

        return SettingsViewHolder(v)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val res = holder.itemView.context.resources

        val item = dataSet[position]

        holder.title.text = dataSet[position].name

        holder.container.setOnClickListener {
            clickListener.onOptionClicked(item)
        }
    }

    interface OnUserClickListener {
        fun onOptionClicked(option: UserEntity)
    }

}