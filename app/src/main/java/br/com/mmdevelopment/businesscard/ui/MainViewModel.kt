package br.com.mmdevelopment.businesscard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.mmdevelopment.businesscard.data.BusinessCard
import br.com.mmdevelopment.businesscard.data.BusinessCardRepository

class MainViewModel(private val cardRepository: BusinessCardRepository) : ViewModel() {

    fun getAll(): LiveData<List<BusinessCard>> = cardRepository.getAll()

    fun findById(id: Int) = cardRepository.findById(id)

    fun insert(businessCard: BusinessCard) = cardRepository.insert(businessCard)

    fun delete(businessCard: BusinessCard) = cardRepository.delete(businessCard)
}

class MainViewModelFactory(private val repository: BusinessCardRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}