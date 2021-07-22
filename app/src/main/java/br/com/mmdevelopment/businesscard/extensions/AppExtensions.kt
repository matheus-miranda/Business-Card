package br.com.mmdevelopment.businesscard.extensions

import android.content.Context
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

// Return String value for TextInput and set text to received value
var TextInputLayout.text: String
    get() = editText?.text.toString()
    set(value) {
        editText?.setText(value)
    }

// Create toast
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}