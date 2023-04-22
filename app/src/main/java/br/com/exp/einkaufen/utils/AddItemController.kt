package br.com.exp.einkaufen.utils

import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.textfield.TextInputLayout

class AddItemController() {

    fun verificaEnter(texto: TextInputLayout) {
        val textWatcher = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s?.get(s.length - 1)?.equals( ' ', true) == true)
                    Log.i(ADD_ITEM_CONTROLLER, "Espaço entered")
                // Caso enter seja pressionado
                if (s?.subSequence(start, start + 1).toString().equals("\n", ignoreCase = true)) {
                    val editable: Editable =
                        SpannableStringBuilder(s?.substring(0, s.length-1).toString())

                    Log.i(ADD_ITEM_CONTROLLER, "** Enter pressed **")
                    texto.editText?.clearFocus()
                    texto.editText?.text = editable
                    Log.i(ADD_ITEM_CONTROLLER, "TextSize = ${texto.editText?.textSize}")
                    //texto.editText?.textSize = 24F
                    //texto.editText?.removeTextChangedListener(this)
                    //escondeSoftTeclado()
                }
            }
        }

        texto.editText?.addTextChangedListener(textWatcher)

    }

    fun capturaString(inputText: TextInputLayout): String{
        Log.i(ADD_ITEM_CONTROLLER, "$inputText")
        return "Hello darkness. It's ${inputText.editText?.text.toString()}"
    }

    //    private fun mostraSoftTeclado (){
//        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
//    }
//
//    private fun escondeSoftTeclado() {
//        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
//        //imm.hideSoftInputFromWindow(texto.windowToken, 0);
//    }
//
//    private fun ajustaTamanhoDoTexto(texto: TextInputLayout, buttonAdicionaItem: Button){
//        texto.editText?.setOnFocusChangeListener { view, b ->
//            // Ajusta o tamanho do texto, em sp
//            texto.editText?.textSize = 30F
//            //println("editText focus change")
//        }
//
//        buttonAdicionaItem.setOnFocusChangeListener { view, b ->
//            // Ajusta o tamanho do texto, em sp
//            texto.editText?.textSize = 24F
//        }
//    }

//    private fun verificaEnter(texto: TextInputLayout) {
//        val textWatcher = object : TextWatcher {
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//                if (s?.get(s.length - 1)?.equals( ' ', true) == true)
//                    Log.i(ADD_ITEM, "Espaço entered")
//                // Caso enter seja pressionado
//                if (s?.subSequence(start, start + 1).toString().equals("\n", ignoreCase = true)) {
//                    val editable: Editable =
//                        SpannableStringBuilder(s?.substring(0, s.length-1).toString())
//
//                    Log.i(ADD_ITEM, "** Enter pressed **")
//                    texto.editText?.clearFocus()
//                    texto.editText?.text = editable
//                    Log.i(ADD_ITEM, "TextSize = ${texto.editText?.textSize}")
//                    //texto.editText?.textSize = 24F
//                    //texto.editText?.removeTextChangedListener(this)
//                    //escondeSoftTeclado()
//                }
//            }
//        }
//
//        texto.editText?.addTextChangedListener(textWatcher)
//
//    }

    companion object {
        private const val ADD_ITEM_CONTROLLER = "addItemController"
    }

}