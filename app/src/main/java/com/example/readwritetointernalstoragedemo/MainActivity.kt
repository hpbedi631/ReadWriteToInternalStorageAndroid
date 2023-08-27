package com.example.readwritetointernalstoragedemo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // on "Save" button click, the information from editData will be saved to file
        btnSave.setOnClickListener {
            val file = editFile.text.toString()
            val data = editData.text.toString()

            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            showToast("Saved to file ")
        }
        // on "Display" button click, the information from file will display in editData
        btnView.setOnClickListener {
            val filename = editFile.text.toString()

            if (filename.toString() != null && filename.trim() != "") {
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null

                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }

                // Displaying data on editText
                editData.setText(stringBuilder.toString()).toString()
            } else {
                showToast("name of file cannot be blank")
            }
        }


    }

    // function extension for Toast.makeText(...)
    fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }
}