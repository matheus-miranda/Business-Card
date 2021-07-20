package br.com.mmdevelopment.businesscard

import android.app.Application
import br.com.mmdevelopment.businesscard.data.AppDatabase
import br.com.mmdevelopment.businesscard.data.BusinessCardRepository

/**
 * This class will run when our app starts
 */
class App : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessCardDao()) }
}