package br.com.mmdevelopment.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.businesscard.App
import br.com.mmdevelopment.businesscard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getAllBusinessCards()
        insertListeners()
    }

    /**
     * ClickListeners for views in activity
     */
    private fun insertListeners() {
        binding.fabAdd.setOnClickListener {
            Intent(this, AddBusinessCardActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    /**
     * Notify changes in Business Cards
     */
    private fun getAllBusinessCards() {
        mainViewModel.getAll().observe(this, { businessCards ->

        })
    }
}