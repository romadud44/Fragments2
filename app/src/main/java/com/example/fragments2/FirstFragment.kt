package com.example.fragments2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragments2.databinding.FragmentFirstBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("UNREACHABLE_CODE")
class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var listAdapter: CustomAdapter? = null
    private var id = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        listAdapter?.notifyDataSetChanged()
        binding.recyclerviewRV.layoutManager = LinearLayoutManager(requireContext())
        listAdapter = CustomAdapter(Note.notes)
        binding.recyclerviewRV.adapter = listAdapter
        binding.recyclerviewRV.setHasFixedSize(true)
        listAdapter?.setOnNoteClickListener(object :
            CustomAdapter.OnNoteClickListener {
            override fun onNoteClick(note: Note, position: Int) {
                Toast.makeText(requireActivity(), "клик", Toast.LENGTH_LONG).show()

                onFragmentDataListener.onData(note)

            }
        })
        binding.recyclerviewRV

        binding.addBTN.setOnClickListener {
            val (currentDate, dateFormat, timeFormat) = triple()
            id++
            val text = binding.addET.text.toString()
            val date = "${dateFormat.format(currentDate)} ${timeFormat.format(currentDate)}"
            val check = false
            val note = Note(id, text, date, check)
            Note.notes.add(note)
            listAdapter?.notifyDataSetChanged()


        }

    }

    private fun triple(): Triple<Date, SimpleDateFormat, SimpleDateFormat> {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH.mm", Locale.getDefault())
        return Triple(currentDate, dateFormat, timeFormat)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}