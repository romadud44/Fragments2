package com.example.fragments2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments2.databinding.FragmentFirstBinding
import com.example.fragments2.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var detailsNote: Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsNote = arguments?.getParcelable("note")
        binding.detailsTextET.setText(detailsNote?.text)
        binding.detailsNumberET.setText(detailsNote?.id.toString())
        if(detailsNote?.check == true) binding.detailsCheckCB.isChecked
    }


}