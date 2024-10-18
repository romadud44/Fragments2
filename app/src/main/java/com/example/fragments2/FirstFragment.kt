package com.example.fragments2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments2.databinding.FragmentFirstBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("UNREACHABLE_CODE")
class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var listAdapter: CustomAdapter? = null
    private var id = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val addBTN: Button = view.findViewById(R.id.addBTN)
//        val addET: EditText = view.findViewById(R.id.addET)
//        val recyclerViewRV: RecyclerView = view.findViewById(R.id.recyclerviewRV)


        listAdapter = CustomAdapter(Note.notes)
        binding.recyclerviewRV.adapter = listAdapter
        listAdapter?.notifyDataSetChanged()

        binding.recyclerviewRV.setHasFixedSize(true)



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