package br.com.exp.einkaufen.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

//        binding.recyclerViewList.adapter = adapter
//        binding.addButtom.setOnClickListener {
//            view: View -> Navigation.findNavController(view)
//                                    .navigate(R.id.action_mainFragment_to_addItem)
//        }
//        PODE SER SUBSTITUÍDO POR:

        binding.toAddItemFragment.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_mainFragment_to_addItem)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewList.adapter = adapter
        adapter.submitList(ItemDataSource.getList())
        adapter.listenerEdit = {

            Log.e("myERROR", "listenerEdit clicked", )
        }
        adapter.listenerDelete = {
            Log.e("myERROR", "listenerDelete clicked", )
        }

    }

    // installed Navigation in build.gradle (app and project, for safeargs)
    // https://developer.android.com/jetpack/androidx/releases/navigation

    // floating action button
    // at https://developer.android.com/develop/ui/views/components/floating-action-button
}