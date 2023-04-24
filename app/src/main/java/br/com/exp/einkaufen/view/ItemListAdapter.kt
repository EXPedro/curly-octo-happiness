package br.com.exp.einkaufen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.exp.einkaufen.databinding.ItemListBinding
import br.com.exp.einkaufen.model.Item

class ItemListAdapter: ListAdapter< Item, ItemListAdapter.ItemViewHolder >( DiffCallback() ) {

    class ItemViewHolder (
        private val binding: ItemListBinding
    ): RecyclerView.ViewHolder( binding.root ){

        fun bind(item: Item) {
            binding.recyclerViewText.text = item.item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from( parent.context )
        val binding = ItemListBinding.inflate( inflater, parent, false)
        return ItemViewHolder( binding )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind( getItem( position ) )
    }
}

class DiffCallback: DiffUtil.ItemCallback< Item >() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

}