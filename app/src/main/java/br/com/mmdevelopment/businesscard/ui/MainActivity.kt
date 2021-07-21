package br.com.mmdevelopment.businesscard.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.mmdevelopment.businesscard.App
import br.com.mmdevelopment.businesscard.adapters.BusinessCardAdapter
import br.com.mmdevelopment.businesscard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvCards.adapter = adapter
        getAllBusinessCards()
        fabBehaviorRvScroll()
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

        // Single click handler for cards
        adapter.onSingleClickHandler = { card->
            Toast.makeText(this, "Short click", Toast.LENGTH_SHORT).show()
        }

        // Long click handler for cards
        adapter.onLongClickHandler = { card->
            Toast.makeText(this, "Long click", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Notify changes in Business Cards
     */
    private fun getAllBusinessCards() {
        mainViewModel.getAll().observe(this, { businessCards ->
            adapter.submitList(businessCards)
        })
    }

    /**
     * Adds collapsing behavior for FAB when scrolling the RecyclerView
     */
    private fun fabBehaviorRvScroll() {
        binding.rvCards.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Scroll down
                if (dy > 20 && binding.fabAdd.isExtended) {
                    binding.fabAdd.shrink()
                }

                // Scroll up
                if (dy < -20 && !binding.fabAdd.isExtended) {
                    binding.fabAdd.extend()
                }

                // At the top
                if (!recyclerView.canScrollVertically(-1)) {
                    binding.fabAdd.extend()
                }
            }
        })
    }
}