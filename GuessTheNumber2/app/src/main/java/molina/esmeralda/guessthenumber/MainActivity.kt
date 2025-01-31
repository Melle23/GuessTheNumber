package molina.esmeralda.guessthenumber

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.max
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 100
    var num:Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessing: TextView = findViewById(R.id.guessing)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guess: Button = findViewById(R.id.guess)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessing.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guess.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue = num
            if (checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessing.setText(num.toString())
            } else {
                guessing.setText("No puede ser :( Me ganaste")
            }
        }

        down.setOnClickListener {
            maxValue = num
            if (checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessing.setText(num.toString())
            } else {
                guessing.setText("No puede ser :( Me ganaste")
            }
        }

        guess.setOnClickListener {
            if (!won){
                guessing.setText("Adivine tu numero es el: "+ num.toString())
                guess.setText("Volver a Jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessing.setText("Tap on Generate to Start")
                guess.setText("Guessed")
                guess.visibility = View.GONE
                resetValues()
            }
        }

    }

    fun resetValues(){
        minValue = 0
        maxValue = 100
        num = 0
    }

    fun checkingLimits(): Boolean{
        return minValue != maxValue
    }

}