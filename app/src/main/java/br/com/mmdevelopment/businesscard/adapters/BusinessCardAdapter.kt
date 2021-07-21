package br.com.mmdevelopment.businesscard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.mmdevelopment.businesscard.data.BusinessCard
import br.com.mmdevelopment.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.CardViewHolder>(DiffCallBack()) {

    var onSingleClickHandler: (View) -> Unit = {}
    var onLongClickHandler: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater =
            ItemBusinessCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardViewHolder(private val binding: ItemBusinessCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusinessCard) {
            binding.tvName.text = item.name
            binding.tvJobTitle.text = item.jobRole
            binding.tvPhone.text = item.phone
            binding.tvEmail.text = item.email
            binding.tvCompany.text = item.company
            binding.tvWebsite.text = item.website
            binding.mcvContent.setBackgroundColor(item.cardColor)

            binding.mcvContent.setOnClickListener {
                onSingleClickHandler(it)
            }
            binding.mcvContent.setOnLongClickListener {
                onLongClickHandler(it)
                true
            }
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem == newItem

}