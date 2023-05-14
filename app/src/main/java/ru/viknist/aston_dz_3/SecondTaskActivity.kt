package ru.viknist.aston_dz_3

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import coil.load
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import java.net.HttpURLConnection
import java.net.URL

private const val SELECTED_METHOD = "Selected Method"

class SecondTaskActivity : AppCompatActivity() {

    private val uriImageEditText by bind<EditText>(R.id.uriImageEditText, this)
    private val imageView by bind<ImageView>(R.id.imageView, this)
    private val coilMethodButton by bind<Button>(R.id.coilMethodButton, this)
    private val picassoMethodButton by bind<Button>(R.id.picassoMethodButton, this)
    private val glideMethodButton by bind<Button>(R.id.glideMethodButton, this)
    private val classicMethodButton by bind<Button>(R.id.classicMethodButton, this)
    private var selectedMethod: LoadImageMethod = LoadImageMethod.COIL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_task)

        uriImageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                try {
                    loadImage(selectedMethod, uriImageEditText.text.toString(), imageView)
                } catch (exception: Exception){
                    Toast.makeText(applicationContext, "Oops... ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        coilMethodButton.setOnClickListener {
            selectedMethod = LoadImageMethod.COIL
            try {
                loadImage(selectedMethod, uriImageEditText.text.toString(), imageView)
            } catch (exception: Exception){
                Toast.makeText(applicationContext, "Oops... ${exception.message}", Toast.LENGTH_SHORT).show()
            }
            colorButtons(selectedMethod)
        }

        picassoMethodButton.setOnClickListener {
            selectedMethod = LoadImageMethod.PICASSO
            try {
                loadImage(selectedMethod, uriImageEditText.text.toString(), imageView)
            } catch (exception: Exception){
                Toast.makeText(applicationContext, "Oops... ${exception.message}", Toast.LENGTH_SHORT).show()
            }
            colorButtons(selectedMethod)
        }

        glideMethodButton.setOnClickListener {
            selectedMethod = LoadImageMethod.GLIDE
            try {
                loadImage(selectedMethod, uriImageEditText.text.toString(), imageView)
            } catch (exception: Exception){
                Toast.makeText(applicationContext, "Oops... ${exception.message}", Toast.LENGTH_SHORT).show()
            }
            colorButtons(selectedMethod)
        }

        classicMethodButton.setOnClickListener {
            selectedMethod = LoadImageMethod.CLASSIC
            try {
                loadImage(selectedMethod, uriImageEditText.text.toString(), imageView)
            } catch (exception: Exception){
                Toast.makeText(applicationContext, "Oops... ${exception.message}", Toast.LENGTH_SHORT).show()
            }
            colorButtons(selectedMethod)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_METHOD, selectedMethod.ordinal)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredState = LoadImageMethod.values()[savedInstanceState.getInt(SELECTED_METHOD)]
        selectedMethod = restoredState
        colorButtons(selectedMethod)
    }

    fun loadImage(method: LoadImageMethod, uri: String, imageView: ImageView) {
        println(uri)
        if (uri.isEmpty() || uri == ""){
            throw Exception("Empty URI")
        }

        when(method){
            LoadImageMethod.COIL -> imageView.load(uri)
            LoadImageMethod.PICASSO -> Picasso.get().load(uri).into(imageView)
            LoadImageMethod.GLIDE -> Glide.with(this).load(uri).into(imageView)
            LoadImageMethod.CLASSIC -> {
                val thread = Thread {
                    try {
                        val bitmap = BitmapFactory.decodeStream(URL(uri).openConnection().getInputStream())
                        runOnUiThread {
                            imageView.setImageBitmap(bitmap)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                thread.start()
            }
        }
    }

    fun colorButtons(method: LoadImageMethod){
        when(method){
            LoadImageMethod.COIL -> {
                coilMethodButton.background.setTint(getColor(R.color.blue))
                picassoMethodButton.background.setTint(getColor(R.color.grey))
                glideMethodButton.background.setTint(getColor(R.color.grey))
                classicMethodButton.background.setTint(getColor(R.color.grey))
            }
            LoadImageMethod.PICASSO -> {
                coilMethodButton.background.setTint(getColor(R.color.grey))
                picassoMethodButton.background.setTint(getColor(R.color.blue))
                glideMethodButton.background.setTint(getColor(R.color.grey))
                classicMethodButton.background.setTint(getColor(R.color.grey))
            }
            LoadImageMethod.GLIDE -> {
                coilMethodButton.background.setTint(getColor(R.color.grey))
                picassoMethodButton.background.setTint(getColor(R.color.grey))
                glideMethodButton.background.setTint(getColor(R.color.blue))
                classicMethodButton.background.setTint(getColor(R.color.grey))
            }
            LoadImageMethod.CLASSIC -> {
                coilMethodButton.background.setTint(getColor(R.color.grey))
                picassoMethodButton.background.setTint(getColor(R.color.grey))
                glideMethodButton.background.setTint(getColor(R.color.grey))
                classicMethodButton.background.setTint(getColor(R.color.blue))
            }
        }
    }
}