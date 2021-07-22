package br.com.mmdevelopment.businesscard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val jobRole: String,
    val phone: String,
    val email: String,
    val company: String,
    val website: String,
    val cardColor: Int
)