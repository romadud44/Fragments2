package com.example.fragments2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

import com.example.fragments2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val firstFragment = FirstFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.main, firstFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> {
                finishAndRemoveTask()
                finishAffinity()
                finish()
                Toast.makeText(this, "Программа завершена", Toast.LENGTH_LONG).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
//
//    override fun onData(data: Note?) {
//        val bundle = Bundle()
//        val secondFragment = SecondFragment()
//        bundle.putParcelable("note", data)
//        val transaction = this.supportFragmentManager.beginTransaction()
//
//        secondFragment.arguments = bundle
//
//        transaction.replace(R.id.fragmentContainer, secondFragment)
//        transaction.addToBackStack(null)
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        transaction.commit()
//    }
}