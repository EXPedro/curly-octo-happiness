package br.com.exp.einkaufen.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.exp.einkaufen.controller.AddItemController
import br.com.exp.einkaufen.databinding.FragmentAddItemBinding
import br.com.exp.einkaufen.view.viewmodel.AddItemViewModel
import com.google.android.material.textfield.TextInputLayout


class AddItem : Fragment() {

    //Declarando ViewModel utilizado nesse fragment
    private lateinit var viewModel: AddItemViewModel
    private lateinit var aic: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentAddItemBinding.inflate(inflater, container, false)

        //Usando ViewModel, deve-se chamá-los usando uma classe chamada ViewModelProviders
        //que assegura que a instância certa está sendo utilizada
        //é a seguinte linha que assegura que a ligação com os dados seja feita corretamente
        Log.i(ADD_ITEM, "Chamado ViewModelProviders.of!")
        viewModel = ViewModelProviders.of(this).get(AddItemViewModel::class.java)

        ajustaTamanhoDoTexto(binding.inputText, binding.buttonAdicionaItem)
        mostraSoftTeclado()
        verificaEnter(binding.inputText)

        aic= AddItemController().capturaString( binding.inputText)

        return binding.root
    }

    private fun mostraSoftTeclado (){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    private fun escondeSoftTeclado() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        //imm.hideSoftInputFromWindow(texto.windowToken, 0);
    }

    private fun ajustaTamanhoDoTexto(texto: TextInputLayout, buttonAdicionaItem: Button){
        texto.editText?.setOnFocusChangeListener { view, b ->
            // Ajusta o tamanho do texto, em sp
            texto.editText?.textSize = 30F
            //println("editText focus change")
        }

        buttonAdicionaItem.setOnFocusChangeListener { view, b ->
            // Ajusta o tamanho do texto, em sp
            texto.editText?.textSize = 24F
        }
    }

    private fun verificaEnter(texto: TextInputLayout) {
        val textWatcher = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s?.get(s.length - 1)?.equals( ' ', true) == true)
                    Log.i(ADD_ITEM, "Espaço entered")
                // Caso enter seja pressionado
                if (s?.subSequence(start, start + 1).toString().equals("\n", ignoreCase = true)) {
                    val editable: Editable =
                        SpannableStringBuilder(s?.substring(0, s.length-1).toString())

                    Log.i(ADD_ITEM, "** Enter pressed **")
                    texto.editText?.clearFocus()
                    texto.editText?.text = editable
                    texto.editText?.textSize = 24F
                    //texto.editText?.removeTextChangedListener(this)
                    escondeSoftTeclado()
                }
            }
        }

        texto.editText?.addTextChangedListener(textWatcher)

    }

    companion object {
        private const val ADD_ITEM = "addItem"
    }
}