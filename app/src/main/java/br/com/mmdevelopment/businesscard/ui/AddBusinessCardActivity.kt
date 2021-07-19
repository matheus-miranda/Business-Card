package br.com.mmdevelopment.businesscard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.businesscard.R
import br.com.mmdevelopment.businesscard.databinding.ActivityAddBusinessCardBinding
import dev.sasikanth.colorsheet.ColorSheet

class AddBusinessCardActivity : AppCompatActivity() {

    companion object {
        private const val COLOR_SELECTED = "selectedColor"
    }

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private var mSelectedColor: Int = ColorSheet.NO_COLOR

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        insertListeners()
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
}