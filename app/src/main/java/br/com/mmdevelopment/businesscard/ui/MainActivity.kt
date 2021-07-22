package br.com.mmdevelopment.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.mmdevelopment.businesscard.App
import br.com.mmdevelopment.businesscard.adapters.BusinessCardAdapter
import br.com.mmdevelopment.businesscard.databinding.ActivityMainBinding
import br.com.mmdevelopment.businesscard.util.Image
import com.littlemango.stacklayoutmanager.StackLayoutManager

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRV()
        getAllBusinessCards()
        //fabBehaviorRvScroll()
        insertListeners()
    }

    /**
     * Define Recycler View layout manager
     */
    private fun initRV() {
        val orientation = StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP
        val manager = StackLayoutManager(orientation, 4)
        binding.rvCards.layoutManager = manager
        binding.rvCards.adapter = adapter
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
        adapter.onSingleClickHandler = { card ->
            Intent(this, AddBusinessCardActivity::class.java).also {
                it.putExtra(AddBusinessCardActivity.CARD_ID, card.id)
                startActivity(it)
            }
        }

        // Long click handler for cards
        adapter.onLongClickHandler = { listItem ->
            Image.share(this@MainActivity, listItem)
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