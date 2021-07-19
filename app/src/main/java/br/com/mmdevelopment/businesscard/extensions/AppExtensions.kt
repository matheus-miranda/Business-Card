package br.com.mmdevelopment.businesscard.extensions

import com.google.android.material.textfield.TextInputLayout

// Return String value for TextInput and set text to received value
var TextInputLayout.text: String
    get() = editText?.text.toString()
    set(value) {
        editText?.setText(value)
    }