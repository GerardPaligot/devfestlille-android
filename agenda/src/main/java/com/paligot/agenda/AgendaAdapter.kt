package com.paligot.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paligot.agenda.databinding.ItemAgendaBinding
import com.paligot.internal.TalkUi
import com.paligot.shared.extensions.addRipple
import com.paligot.shared.livedata.SingleLiveEvent

class AgendaAdapter : ListAdapter<TalkUi, AgendaAdapter.ViewHolder>(AgendaConnectedDiff) {
    val itemSelected: SingleLiveEvent<TalkUi> by lazy {
        SingleLiveEvent<TalkUi>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAgendaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(item)
            itemView.setOnClickListener { if (item.isBreak.not()) itemSelected.setValue(item) }
            itemView.tag = item
        }
    }

    class ViewHolder(private val binding: ItemAgendaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TalkUi) {
            binding.talk = item
            binding.executePendingBindings()
            if (item.isBreak) binding.container.setBackgroundResource(android.R.color.transparent)
            else binding.container.addRipple()
        }
    }
}

object AgendaConnectedDiff : DiffUtil.ItemCallback<TalkUi>() {
    override fun areItemsTheSame(oldItem: TalkUi, newItem: TalkUi): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: TalkUi, newItem: TalkUi): Boolean {
        return oldItem == newItem
    }
}
