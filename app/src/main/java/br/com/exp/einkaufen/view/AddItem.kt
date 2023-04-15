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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.exp.einkaufen.databinding.FragmentAddItemBinding
import br.com.exp.einkaufen.view.viewmodel.AddItemViewModel
import com.google.android.material.textfield.TextInputLayout


class AddItem : Fragment() {

    //Declarando ViewModel utilizado nesse fragment
    private lateinit var viewModel: AddItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAddItemBinding.inflate(inflater, container, false)

//      Usando ViewModel, deve-se chamá-los usando uma classe chamada ViewModelProviders
//      que assegura que a instância certa está sendo utilizada é a seguinte linha que
//      assegura que a ligação com os dados seja feita corretamente.
        viewModel = ViewModelProviders.of(this).get(AddItemViewModel::class.java)


        Log.i(ADD_ITEM, "Chamado ViewModelProviders.of!")

        binding.addItemViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.newItems.observe(viewLifecycleOwner, Observer{ newItem ->
            binding.inputText.editText?.text= SpannableStringBuilder( newItem)
        })

        verificaEnter(binding.inputText, viewModel)

        return binding.root
    }

    fun verificaEnter(texto: TextInputLayout, viewModel: AddItemViewModel) {
        val textWatcher = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val textoEntrada: String = s.toString()
                viewModel.firstStep(MutableLiveData(textoEntrada))
                Log.i(ADD_ITEM, "mensagem = $textoEntrada")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    Log.i(
                        ADD_ITEM,
                        s?.get(s.length - 1).toString()
                    )//TODO: tratar erro  java.lang.IndexOutOfBoundsException

                    if (s?.get(s.length - 1)?.equals(' ', true) == true)
                        Log.i(ADD_ITEM, "Espaço entered")
                    // Caso enter seja pressionado
                    if (s?.subSequence(start, start + 1).toString()
                            .equals("\n", ignoreCase = true)
                    ) {
                        Log.i(ADD_ITEM, "** Enter pressed **")
                    }
                } catch(ex: Exception){
                    Log.e(ADD_ITEM, "onTextChanged error", )
                }
            }
        }

        texto.editText?.addTextChangedListener(textWatcher)

    }

    companion object {
        private const val ADD_ITEM = "addItem"
    }
}