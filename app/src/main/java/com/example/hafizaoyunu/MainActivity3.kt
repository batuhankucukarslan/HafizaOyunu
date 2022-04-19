package com.example.hafizaoyunu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
private const val TAG = "MainActivity3"
class MainActivity3 : AppCompatActivity() {
    private lateinit var butonlar: List<ImageButton>
    private lateinit var kartlar: List<HafizaOyunu>
    private var kartnumarası: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btnAnasayfa)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        val resimler = mutableListOf(R.drawable.ic_card1,R.drawable.ic_card2,R.drawable.ic_card3,
            R.drawable.ic_card4,R.drawable.ic_card5,R.drawable.ic_card6,R.drawable.ic_card7,
            R.drawable.ic_card8)
        resimler.addAll(resimler)

        resimler.shuffle()

        butonlar = listOf(imageButton1,imageButton2,imageButton3,imageButton4,imageButton5,
            imageButton6,imageButton7,imageButton8,imageButton9,imageButton10,imageButton11,
            imageButton12,imageButton13,imageButton14,imageButton15,imageButton16)

        kartlar = butonlar.indices.map { i ->
            HafizaOyunu(resimler[i])
        }
        var hamlesay = 0
        butonlar.forEachIndexed { i, button ->
            button.setOnClickListener {

                hamlesay = hamlesay + 1
                HamleBtn.text = "Hamle: " + hamlesay
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
                EslesBtn.text = "Eşleşme: " + eslesme/2

            }
            puanBtn2.text = "Puan: " + ((eslesme*5)-(hamleler - eslesme)*2)
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