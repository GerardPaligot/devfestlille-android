package com.paligot.session

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paligot.agenda.R
import com.paligot.agenda.databinding.ItemSessionInfoBinding
import com.paligot.agenda.databinding.ItemSpeakerBinding
import com.paligot.agenda.databinding.ItemSpeakerHeaderBinding
import com.paligot.internal.SpeakerUi
import com.paligot.internal.TalkUi

class SessionAdapter(talk: TalkUi) : ListAdapter<Any, SessionViewHolder>(DiffCallback) {
    init {
        submitList(arrayListOf(talk) + SpeakerHeaderItem + talk.speakers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_session_info -> SessionViewHolder.SessionInfoViewHolder(
                ItemSessionInfoBinding.inflate(inflater, parent, false)
            )
            R.layout.item_speaker_header -> SessionViewHolder.SpeakerHeaderViewHolder(
                ItemSpeakerHeaderBinding.inflate(inflater, parent, false)
            )
            R.layout.item_speaker -> SessionViewHolder.SpeakerViewHolder(
                ItemSpeakerBinding.inflate(inflater, parent, false)
            )
            else -> throw IllegalStateException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        when (holder) {
            is SessionViewHolder.SessionInfoViewHolder -> holder.binding.apply {
                val item = getItem(position) as TalkUi
                talk = item
                root.tag = item
            }
            is SessionViewHolder.SpeakerViewHolder -> holder.binding.apply {
                val item = getItem(position) as SpeakerUi
                speaker = item
                root.tag = item
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is TalkUi -> R.layout.item_session_info
        is SpeakerUi -> R.layout.item_speaker
        is SpeakerHeaderItem -> R.layout.item_speaker_header
        else -> throw IllegalStateException("Unknown view type at position $position")
    }
}

object SpeakerHeaderItem

object DiffCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem === SpeakerHeaderItem && newItem === SpeakerHeaderItem -> true
            oldItem is TalkUi && newItem is TalkUi -> oldItem.title == newItem.title
            oldItem is SpeakerUi && newItem is SpeakerUi -> oldItem.displayName == newItem.displayName
            else -> false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is TalkUi && newItem is TalkUi -> oldItem == newItem
            oldItem is SpeakerUi && newItem is SpeakerUi -> oldItem == newItem
            else -> true
        }
    }
}

sealed class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class SessionInfoViewHolder(val binding: ItemSessionInfoBinding) : SessionViewHolder(binding.root)
    class SpeakerHeaderViewHolder(val binding: ItemSpeakerHeaderBinding) : SessionViewHolder(binding.root)
    class SpeakerViewHolder(val binding: ItemSpeakerBinding) : SessionViewHolder(binding.root)
}