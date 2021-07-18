package br.com.mmdevelopment.businesscard

import android.app.Application
import com.google.android.material.textfield.TextInputLayout

/**
 * This class will run when our app starts
 */
class App : Application() {

    // Return String value for TextInput and set text to received value
    var TextInputLayout.text: String
        get() = editText?.text.toString()
        set(value) {
            editText?.setText(value)
        }
}