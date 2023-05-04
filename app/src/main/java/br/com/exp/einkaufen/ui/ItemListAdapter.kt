package br.com.exp.einkaufen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.exp.einkaufen.R
import br.com.exp.einkaufen.databinding.ItemListBinding
import br.com.exp.einkaufen.model.Item

class ItemListAdapter: ListAdapter< Item, ItemListAdapter.ItemViewHolder >( DiffCallback() ) {

    var listenerEdit: (Item) -> Unit = {}
    var listenerDelete: (Item) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from( parent.context )
        val binding = ItemListBinding.inflate( inflater, parent, false)

        return ItemViewHolder( binding )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind( getItem( position ) )
    }

    inner class ItemViewHolder (
        private val binding: ItemListBinding
    ): RecyclerView.ViewHolder( binding.root ){

        fun bind(item: Item) {
            binding.recyclerViewText.text = item.item
            binding.menuMore.setOnClickListener {
                showPopUp( item )
            }
        }

        private fun showPopUp( item: Item ) {
            val menuMore = binding.menuMore
            val popupMenu = PopupMenu(menuMore.context, menuMore)
            popupMenu.menuInflater.inflate( R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when( it.itemId ) {
                    R.id.action_edit -> listenerEdit( item )
                    R.id.action_delete -> listenerDelete( item )
                }

                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }

    }
}

class DiffCallback: DiffUtil.ItemCallback< Item >() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

}