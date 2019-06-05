package com.paligot.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.paligot.agenda.databinding.FragmentAgendaBinding
import com.paligot.internal.TalkUi
import com.paligot.shared.decorations.ScheduleTimeHeadersDecoration
import com.paligot.shared.extensions.clearDecorations
import com.paligot.shared.repositories.Talk

class AgendaFragment : Fragment() {
    private val agendaViewModel: AgendaViewModel by viewModels { AgendaViewModel.Factory() }

    private lateinit var binding: FragmentAgendaBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentAgendaBinding.inflate(inflater, container, false).run {
            binding = this
            agenda.adapter = AgendaAdapter()
            viewModel = agendaViewModel
            lifecycleOwner = this@AgendaFragment
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (binding.agenda.adapter as AgendaAdapter).itemSelected.observe(this, Observer {
            findNavController().navigate(AgendaFragmentDirections.actionAgendaFragmentToSessionFragment(it))
        })
    }
}

@BindingAdapter(value = ["agendaItems"])
fun agendaItems(recyclerView: RecyclerView, list: List<Talk>?) {
    (recyclerView.adapter as AgendaAdapter).submitList(list?.map { TalkUi.fromRepo(it) })
    // Recreate the decoration used for the sticky date headers
    recyclerView.clearDecorations()
    if (list != null && list.isNotEmpty()) {
        recyclerView.addItemDecoration(ScheduleTimeHeadersDecoration(recyclerView.context, list))
    }
}