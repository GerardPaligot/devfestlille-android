package com.paligot.session

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.paligot.agenda.R
import com.paligot.agenda.databinding.FragmentSessionBinding

class SessionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentSessionBinding.inflate(inflater, container, false).run {
            activity?.let {
                toolbar.setupWithNavController(findNavController())
                toolbar.setTitle(R.string.agenda_session_title)
            }
            arguments?.let {
                sessionInfos.adapter = SessionAdapter(SessionFragmentArgs.fromBundle(it).talk)
            }
            root
        }
    }
}