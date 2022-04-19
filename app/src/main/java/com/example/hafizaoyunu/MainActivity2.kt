package com.example.hafizaoyunu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.zor.*

private const val TAG = "MainActivity2"
class MainActivity2 : AppCompatActivity() {

    private lateinit var butonlar: List<ImageButton>
    private lateinit var kartlar: List<HafizaOyunu>
    private var kartnumarası: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zor)

        val button = findViewById<Button>(R.id.btnZorAnasayfa)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val resimler = mutableListOf(R.drawable.ic_card1,R.drawable.ic_card2,R.drawable.ic_card3,
            R.drawable.ic_card4,R.drawable.ic_card5,R.drawable.ic_card6,R.drawable.ic_card7,
            R.drawable.ic_card8,R.drawable.ic_card9,R.drawable.ic_card10,R.drawable.ic_card11,
            R.drawable.ic_card12,R.drawable.ic_card13,R.drawable.ic_card14,R.drawable.ic_card15,
            R.drawable.ic_card16,R.drawable.ic_card17,R.drawable.ic_card18)
        resimler.addAll(resimler)

        resimler.shuffle()

        butonlar = listOf(imageButton30,imageButton31,imageButton32,imageButton33,imageButton34,
            imageButton35,imageButton36,imageButton37,imageButton38,imageButton39,imageButton40,imageButton41,
            imageButton42,imageButton43,imageButton44,imageButton45,imageButton46,imageButton47,imageButton48,imageButton49,imageButton50,imageButton51,
            imageButton57,imageButton58,imageButton59,imageButton60,imageButton61,imageButton62,imageButton63,imageButton64,imageButton65,
            imageButton52,imageButton53,imageButton54,imageButton55,imageButton56)

        kartlar = butonlar.indices.map { i ->
            HafizaOyunu(resimler[i])
        }
        var hamlesay = 0
        butonlar.forEachIndexed { i, button ->
            button.setOnClickListener {

                hamlesay = hamlesay + 1
                btnHamlez.text = "Hamle: " + hamlesay
                modelDegistir(i)

                gorselDegistir(hamlesay)

            }
        }

    }



    private fun gorselDegistir(hamleler: Int) {
        var eslesme = 0
        kartlar.forEachIndexed { i, kart ->
            val button = butonlar[i]
            if (kart.Eslestiyse) {
                button.alpha = 0.1f
                eslesme = eslesme + 1
                btnEslesmez.text = "Eşleşme: " + eslesme/2

            }
            button4.text = "Puan: " + ((eslesme*5)-(hamleler - eslesme)*2)
            button.setImageResource(if (kart.Secildiyse) kart.identifier else R.drawable.ic_card)
        }
    }

    private fun modelDegistir(position: Int) {
        val kart = kartlar[position]

        if (kart.Secildiyse) {

            Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show()
            return
        }

        if (kartnumarası == null) {

            kartlariYenile()
            kartnumarası = position
        } else {

            eslestimi(kartnumarası!!, position)
            kartnumarası = null
        }
        kart.Secildiyse = !kart.Secildiyse
    }

    private fun kartlariYenile() {
        for (kart in kartlar) {
            if (!kart.Eslestiyse) {
                kart.Secildiyse = false
            }
        }
    }

    private fun eslestimi(position1: Int, position2: Int) {
        if (kartlar[position1].identifier == kartlar[position2].identifier) {
            Toast.makeText(this, "Eşleşme Bulundu!!", Toast.LENGTH_SHORT).show()
            kartlar[position1].Eslestiyse = true
            kartlar[position2].Eslestiyse = true
        }
    }
}