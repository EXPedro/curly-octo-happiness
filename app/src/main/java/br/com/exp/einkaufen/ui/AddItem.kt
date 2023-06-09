package br.com.exp.einkaufen.ui

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import br.com.exp.einkaufen.R
import br.com.exp.einkaufen.model.Item
import br.com.exp.einkaufen.databinding.FragmentAddItemBinding
import br.com.exp.einkaufen.utils.Utils
import br.com.exp.einkaufen.ui.viewmodel.AddItemViewModel
import br.com.exp.einkaufen.ui.viewmodel.AddItemViewModelFactory


class AddItem : Fragment() {

    private lateinit var binding: FragmentAddItemBinding
    private lateinit var stringInputText: String
    //viewModels lifecycle is this fragment, activityViewModels lifecycle is the main activity
    private lateinit var viewModel: AddItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pass in the Application context as a constructor argument
        val appContext = activity?.applicationContext as Application
        viewModel = ViewModelProviders.of(
            this, AddItemViewModelFactory(appContext)).get(AddItemViewModel::class.java)


        binding.addItemViewModel = viewModel    //addItemViewModel is the link with xml
        binding.lifecycleOwner = this.viewLifecycleOwner   //bind lifecycleowner to this fragment

        //Observe: Triggered by rotating screen
        viewModel.newItems.observe( viewLifecycleOwner) { newItem ->

        }

        insertListeners(binding)

    }

    private fun insertListeners(addItemComponents: FragmentAddItemBinding){

        //# Toolbar Navigation Icon
        addItemComponents.materialToolbar.setNavigationOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_addItem_to_mainFragment)
        )

        //# EditText
        addItemComponents.inputText.editText?.addTextChangedListener(
            updateText()
        )

        //## Button AddItem
        addItemComponents.buttonAddItem.setOnClickListener {
            stringInputText = addItemComponents.inputText.editText?.text.toString()
            var listaItens: List<Item> =
                Utils.createItem(Utils.createStringList(stringInputText))

            listaItens.forEach { item ->
                run {
                    viewModel.addItem(item)
                }
            }
            view?.findNavController()?.navigate(R.id.action_addItem_to_mainFragment)
        }

        //## Button Cancel
        addItemComponents.buttonCancel.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_addItem_to_mainFragment)
        }

        //# Fragment result
        setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getString("bundleKey")
            if ( result != null) {
                binding.inputText.editText?.text = SpannableStringBuilder(result)
            }
        }

    }

    private fun updateText(): TextWatcher {
        val textWatcher = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val textoEntrada: String = s.toString()
                viewModel.setInputText(MutableLiveData(textoEntrada))
            }

        }

        return textWatcher
    }
}