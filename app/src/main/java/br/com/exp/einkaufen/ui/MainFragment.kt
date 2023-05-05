package br.com.exp.einkaufen.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import br.com.exp.einkaufen.R
import br.com.exp.einkaufen.databinding.FragmentMainBinding
import br.com.exp.einkaufen.ui.viewmodel.AddItemViewModel
import br.com.exp.einkaufen.ui.viewmodel.AddItemViewModelFactory

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val adapter by lazy { ItemListAdapter() }
    private lateinit var viewModel: AddItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pass in the Application context as a constructor argument
        val appContext = activity?.applicationContext as Application
        viewModel = ViewModelProviders.of(
            this, AddItemViewModelFactory(appContext)
        ).get(AddItemViewModel::class.java)

        updateList()

        binding.recyclerViewList.adapter = adapter

        binding.toAddItemFragment.setOnClickListener {
            val result = ""
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            view.findNavController().navigate(R.id.action_mainFragment_to_addItem)
        }

        adapter.listenerEdit = {
            val result = it.item
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            view.findNavController().navigate(R.id.action_mainFragment_to_addItem)
            viewModel.updateItem(it)
        }

        adapter.listenerDelete = {
            viewModel.deleteItem(it)
            view.findNavController().navigate(R.id.action_mainFragment_self)
            Log.w("MainFragment:", "listenerDelete clicked", )
        }

    }

    private fun updateList() {
        adapter.submitList(viewModel.getAll())
        Log.i("MainFragment", "updateList() ")
    }
}