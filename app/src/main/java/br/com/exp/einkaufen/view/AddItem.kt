package br.com.exp.einkaufen.view

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import br.com.exp.einkaufen.R
import br.com.exp.einkaufen.databinding.FragmentAddItemBinding
import br.com.exp.einkaufen.datasource.ItemDataSource
import br.com.exp.einkaufen.utils.Utils
import br.com.exp.einkaufen.viewmodel.AddItemViewModel


class AddItem : Fragment() {

    private lateinit var binding: FragmentAddItemBinding
    private lateinit var stringInputText: String
    //viewModels lifecycle is this fragment, activityViewModels lifecycle is the main activity
    private val viewModel: AddItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addItemViewModel = viewModel    //addItemViewModel is the link with xml
        binding.lifecycleOwner = this.viewLifecycleOwner   //bind lifecycleowner to this fragment

        //Observe: Triggered by rotating screen
        viewModel.newItems.observe( viewLifecycleOwner) { newItem ->
            Log.i(ADD_ITEM, "newItemObserved: $newItem")
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
            Utils.createItem(Utils.createStringList(stringInputText))
            view?.findNavController()?.navigate(R.id.action_addItem_to_mainFragment)
        }

        //## Button Cancel
        addItemComponents.buttonCancel.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_addItem_to_mainFragment)
            Log.i(ADD_ITEM, "\\* canceled /*")
        }

        //# Fragment result
        setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getString("bundleKey")
            if ( result != null) {
                binding.inputText.editText?.text = SpannableStringBuilder(result)
                Log.i(ADD_ITEM, "setFragmentResultListener = $key")
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

    companion object {
        private const val ADD_ITEM = "AddItem"
    }
}