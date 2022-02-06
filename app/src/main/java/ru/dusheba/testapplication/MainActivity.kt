package ru.dusheba.testapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var inputTextField: EditText
    lateinit var editTextButton: Button
    lateinit var editedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initElements()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("text", editedTextView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        editedTextView.text = savedInstanceState.getString("text")
    }

    private fun initElements(){
        val emptyFieldError = getString(R.string.empty_field_error)
        inputTextField = findViewById(R.id.inputTextField)
        editTextButton = findViewById(R.id.editTextButton)
        editedTextView = findViewById(R.id.editedTextView)

        editTextButton.setOnClickListener {
            val inputTextStr = inputTextField.text.toString().trim()

            if(inputTextStr.isEmpty()){
                inputTextField.hint = emptyFieldError
                inputTextField.setHintTextColor(Color.RED)
                return@setOnClickListener
            }

            inputTextField.text.clear()
            editedTextView.text = inputTextStr
        }
    }
}