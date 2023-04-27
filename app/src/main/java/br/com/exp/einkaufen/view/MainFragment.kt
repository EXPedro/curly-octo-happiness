package br.com.exp.einkaufen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import br.com.exp.einkaufen.R
import br.com.exp.einkaufen.databinding.FragmentMainBinding
import br.com.exp.einkaufen.datasource.ItemDataSource

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val adapter by lazy { ItemListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        updateList()

        binding.toAddItemFragment.setOnClickListener {
            val result = ""
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            view.findNavController().navigate(R.id.action_mainFragment_to_addItem)
        }

        binding.recyclerViewList.adapter = adapter
        
        adapter.listenerEdit = {
            val result = it.item
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            view.findNavController().navigate(R.id.action_mainFragment_to_addItem)
            ItemDataSource.updateItem( it )
            //updateList()
        }

        adapter.listenerDelete = {
            ItemDataSource.deleteItem( it )
            //updateList()
            view.findNavController().navigate(R.id.action_mainFragment_self)
            Log.w("MainFragment:", "listenerDelete clicked", )
        }

    }

    private fun updateList() {
        adapter.submitList(ItemDataSource.getList())
        Log.i("MainFragment", "updateList() ")
    }

    // installed Navigation in build.gradle (app and project, for safeargs)
    // https://developer.android.com/jetpack/androidx/releases/navigation

    // floating action button
    // at https://developer.android.com/develop/ui/views/components/floating-action-button
}