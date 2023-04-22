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
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import br.com.exp.einkaufen.R
import br.com.exp.einkaufen.databinding.FragmentAddItemBinding
import br.com.exp.einkaufen.utils.Utils
import br.com.exp.einkaufen.view.viewmodel.AddItemViewModel


class AddItem : Fragment() {

    private lateinit var binding: FragmentAddItemBinding
    private lateinit var stringInputText: String

    //viewModels lifecycle is this fragment, activityViewModels lifecycle is the main activity
    private val viewModel: AddItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentAddItemBinding.inflate(inflater, container, false)

        Log.i(ADD_ITEM, "call ViewModelProviders.of!")

        //Using ViewModel, you should call it with a class named ViewModelProviders
        //that assures that the right instance is being used: the next line will
        //assure that the data binding will be done correctly:
        //viewModel = ViewModelProviders.of(this).get(AddItemViewModel::class.java)

        binding.addItemViewModel = viewModel    //addItemViewModel is the link with xml
        binding.lifecycleOwner = this           //bind life cycle owner to this fragment

        //Observe: Triggered by rotating screen
        viewModel.newItems.observe( viewLifecycleOwner) { newItem ->
            binding.inputText.editText?.text = SpannableStringBuilder(newItem)
            Log.i(ADD_ITEM, "newItemObserved: $newItem")
        }

        insertListeners(binding)

        return binding.root
    }

    private fun insertListeners(addItemComponents: FragmentAddItemBinding){

        //#Toolbar Navigation Icon
        addItemComponents.materialToolbar.setNavigationOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_addItem_to_mainFragment)
        )

        //# EditText
        addItemComponents.inputText.editText?.addTextChangedListener(
            updateText()
        )

        //## Button AddItem
        addItemComponents.buttonAddItem.setOnClickListener (
            View.OnClickListener {
                stringInputText = addItemComponents.inputText.editText?.text.toString()

                Utils.createItem(Utils.createStringList(stringInputText))

                Log.i(ADD_ITEM, "* button clicked *")
            }
        )

        //## Button Cancel
        addItemComponents.buttonCancel.setOnClickListener (
            View.OnClickListener {
                Log.i(ADD_ITEM, "\\* canceled /*")
            }
        )

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

                try {
                    //TODO: tratar erro  java.lang.IndexOutOfBoundsException

                    //#Space pressed:
                    if (s?.get(s.length - 1)?.equals(' ', true) == true)
                        Log.i(ADD_ITEM, "Espaço entered")

                    //#Enter pressed:
                    if (s?.subSequence(start, start + 1).toString()
                    //if (s?.get(s.length - 1)?.equals('\n', ignoreCase = true) == true
                            .equals("\n", ignoreCase = true)
                    ) {
                        Log.i(ADD_ITEM, "** Enter pressed **")
                        //...
                    }

                } catch(ex: Exception){

                    Log.e(ADD_ITEM, "onTextChanged error", )

                }

            }
        }

        return textWatcher

    }

    companion object {
        private const val ADD_ITEM = "addItem"
    }
}