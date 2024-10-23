package com.example.fragments2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.fragments2.databinding.FragmentFirstBinding
import com.example.fragments2.databinding.FragmentSecondBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var detailsNote: Note? = null
    private var detailsPosition: Int? = null
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
        detailsPosition = arguments?.getInt("position")
        binding.detailsTextET.setText(detailsNote?.text)
        binding.detailsNumberET.setText(detailsNote?.id.toString())
        if (detailsNote?.check == true) binding.detailsCheckCB.isChecked

        binding.detailEditBTN.setOnClickListener {
            val (currentDate, dateFormat, timeFormat) = triple()
            val date = "${dateFormat.format(currentDate)} ${timeFormat.format(currentDate)}"

            var newCheck = false
            if (binding.detailsCheckCB.isChecked) newCheck = true
            val newNote = Note(
                binding.detailsNumberET.text.toString().toInt(),
                binding.detailsTextET.text.toString(),
                date,
                newCheck
            )


            val bundle = Bundle()
            val firstFragment = FirstFragment()

            detailsPosition?.let { it1 -> Note.notes.removeAt(it1) }
            detailsPosition?.let { it1 -> Note.notes.add(it1, newNote) }

            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            firstFragment.arguments = bundle


            transaction.replace(R.id.fragmentContainer, firstFragment)
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        }
    }
    private fun triple(): Triple<Date, SimpleDateFormat, SimpleDateFormat> {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH.mm", Locale.getDefault())
        return Triple(currentDate, dateFormat, timeFormat)
    }

}