package com.paligot.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paligot.agenda.databinding.ItemAgendaBinding
import com.paligot.shared.livedata.SingleLiveEvent
import com.paligot.shared.repositories.Room
import com.paligot.shared.repositories.Talk

class AgendaAdapter : ListAdapter<Talk, AgendaAdapter.ViewHolder>(AgendaConnectedDiff()) {
    val itemSelected: SingleLiveEvent<Talk> by lazy {
        SingleLiveEvent<Talk>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAgendaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(item)
            itemView.setOnClickListener { itemSelected.setValue(item) }
            itemView.tag = item
        }
    }

    class ViewHolder(private val binding: ItemAgendaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Talk) {
            binding.talk = item
            binding.executePendingBindings()
        }
    }
}

class AgendaConnectedDiff : DiffUtil.ItemCallback<Talk>() {
    override fun areItemsTheSame(oldItem: Talk, newItem: Talk): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Talk, newItem: Talk): Boolean {
        return oldItem == newItem
    }
}

val Talk.roomDisplayName
    get() = when (room) {
        Room.ACTION -> R.string.agenda_room_action
        Room.COMEDY -> R.string.agenda_room_comedy
        Room.SCIENCE_FICTION -> R.string.agenda_room_sf
        Room.THRILLER -> R.string.agenda_room_thriller
        Room.UNKNOWN -> R.string.agenda_room_unknown
    }