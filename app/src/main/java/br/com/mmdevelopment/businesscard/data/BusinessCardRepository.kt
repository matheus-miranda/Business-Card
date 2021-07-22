package br.com.mmdevelopment.businesscard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDao) {

    fun getAll() = dao.getAll()

    fun findById(cardId: Int) = runBlocking { dao.findById(cardId) }

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }

    fun delete(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.delete(businessCard)
        }
    }
}