package br.com.mmdevelopment.businesscard.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.businesscard.App
import br.com.mmdevelopment.businesscard.R
import br.com.mmdevelopment.businesscard.data.BusinessCard
import br.com.mmdevelopment.businesscard.databinding.ActivityAddBusinessCardBinding
import br.com.mmdevelopment.businesscard.extensions.text
import dev.sasikanth.colorsheet.ColorSheet

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private var mSelectedColor: Int = ColorSheet.NO_COLOR
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getExtra()
        insertListeners()
    }

    /**
     * If user clicked on a card, populate the fields and allow editing/deletion
     */
    private fun getExtra() {
        if (intent.hasExtra(CARD_ID)) {
            binding.btnDelete.visibility = View.VISIBLE
            binding.etTitle.text = getString(R.string.edit_card)
            val cardId = intent.getIntExtra(CARD_ID, 0)

            mainViewModel.findById(cardId)?.let {
                Log.e("Card", it.toString())
                binding.apply {
                    tilName.text = it.name
                    tilRole.text = it.jobRole
                    tilPhone.text = it.phone
                    tilEmail.text = it.email
                    tilCompany.text = it.company
                    tilWebsite.text = it.website
                    tilColor.boxBackgroundColor = it.cardColor
                }
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COLOR_SELECTED, mSelectedColor)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mSelectedColor = savedInstanceState.getInt(COLOR_SELECTED)
        binding.tilColor.boxBackgroundColor = mSelectedColor
    }

    /**
     * ClickListeners for views in activity
     */
    private fun insertListeners() {
        binding.btnClose.setOnClickListener { finish() }

        binding.tilColor.editText?.setOnClickListener { setColorPicker() }

        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tilName.text,
                jobRole = binding.tilRole.text,
                phone = binding.tilPhone.text,
                email = binding.tilEmail.text,
                company = binding.tilCompany.text,
                website = binding.tilWebsite.text,
                cardColor = mSelectedColor,
                id = intent.getIntExtra(CARD_ID, 0)
            )
            mainViewModel.insert(businessCard)
            finish()
        }
    }

    /**
     * Set the background color of the TIL
     */
    private fun setColorPicker() {
        val colors = resources.getIntArray(R.array.colors)
        ColorSheet().colorPicker(
            colors = colors,
            noColorOption = true,
            selectedColor = mSelectedColor,
            listener = { color ->
                mSelectedColor = color
                binding.tilColor.boxBackgroundColor = color
                //val hexColor = ColorSheetUtils.colorToHex(color)
            })
            .show(supportFragmentManager)
    }

    companion object {
        private const val COLOR_SELECTED = "selectedColor"
        const val CARD_ID = "cardId"
    }
}