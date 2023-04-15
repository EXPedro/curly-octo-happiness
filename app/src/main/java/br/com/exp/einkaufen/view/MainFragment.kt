package br.com.exp.einkaufen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import br.com.exp.einkaufen.R
import br.com.exp.einkaufen.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater, container, false)

//        binding.addButtom.setOnClickListener {
//            view: View -> Navigation.findNavController(view)
//                                    .navigate(R.id.action_mainFragment_to_addItem)
//        }
//        PODE SER SUBSTITUÍDO POR:

        binding.addMoreItems.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_addItem)
        )

        return binding.root
    }

    // installed Navigation in build.gradle (app and project, for safeargs)
    // https://developer.android.com/jetpack/androidx/releases/navigation

    // floating action button
    // at https://developer.android.com/develop/ui/views/components/floating-action-button
}