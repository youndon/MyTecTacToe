package com.example.tictactoe

import android.content.Context
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        reset(null)
    }

    fun click(v: View) {

        val btn= v as Button
        var num:Byte= 0
        when(btn.id){
            R.id.btn1 -> num=1
            R.id.btn2 -> num=2
            R.id.btn3 -> num=3
            R.id.btn4 -> num=4
            R.id.btn5 -> num=5
            R.id.btn6 -> num=6
            R.id.btn7 -> num=7
            R.id.btn8 -> num=8
            R.id.btn9 -> num=9
        }
        game(btn,num)
    }
    val player1=ArrayList<Byte>()
    val player2=ArrayList<Byte>()
    var defualtplayer=1

    private fun game(btn: Button, num: Byte){
        if (defualtplayer==1){
            btn.text="x";btn.textSize=30f
            btn.setBackgroundResource(R.color.pink)
            player1.add(num)
            defualtplayer=2
            vib()
//            Handler().postDelayed( { auto() },500) // it's not able yet.
        }else{
            btn.text="o";btn.textSize=30f
            btn.setBackgroundResource(R.color.sea)
            player2.add(num)
            defualtplayer=1
           vib()
        }
        btn.isEnabled=false
        winer()
    }
    private fun winer(){
        var win =-1
        if (player1.contains(1)&&player1.contains(2)&&player1.contains(3)){ win=1 }
        if (player2.contains(1)&&player2.contains(2)&&player2.contains(3)){ win=2 }
        if (player1.contains(1)&&player1.contains(4)&&player1.contains(7)){ win=1 }
        if (player2.contains(1)&&player2.contains(4)&&player2.contains(7)){ win=2 }
        if (player1.contains(8)&&player1.contains(5)&&player1.contains(2)){ win=1 }
        if (player2.contains(8)&&player2.contains(5)&&player2.contains(2)){ win=2 }
        if (player1.contains(4)&&player1.contains(5)&&player1.contains(6)){ win=1 }
        if (player2.contains(4)&&player2.contains(5)&&player2.contains(6)){ win=2 }
        if (player1.contains(7)&&player1.contains(8)&&player1.contains(9)){ win=1 }
        if (player2.contains(7)&&player2.contains(8)&&player2.contains(9)){ win=2 }
        if (player1.contains(9)&&player1.contains(6)&&player1.contains(3)){ win=1 }
        if (player2.contains(9)&&player2.contains(6)&&player2.contains(3)){ win=2 }
        if (player1.contains(1)&&player1.contains(5)&&player1.contains(9)){ win=1 }
        if (player2.contains(1)&&player2.contains(5)&&player2.contains(9)){ win=2 }
        if (player1.contains(7)&&player1.contains(5)&&player1.contains(3)){ win=1 }
        if (player2.contains(7)&&player2.contains(5)&&player2.contains(3)){ win=2 }
        if (win != -1) {
            when (win) {
                1 -> {
                    reset(null)

                    Toast.makeText(this,"player x is win",Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    reset(null)
                    Toast.makeText(this,"player o is win",Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(this,"...",Toast.LENGTH_SHORT).show()
            }
        }
    }
    // the reset button.
    fun reset(v: View?){
        btn1.text="";btn1.setBackgroundResource(R.color.defult);btn1.isEnabled=true
        btn2.text="";btn2.setBackgroundResource(R.color.defult);btn2.isEnabled=true
        btn3.text="";btn3.setBackgroundResource(R.color.defult);btn3.isEnabled=true
        btn4.text="";btn4.setBackgroundResource(R.color.defult);btn4.isEnabled=true
        btn5.text="";btn5.setBackgroundResource(R.color.defult);btn5.isEnabled=true
        btn6.text="";btn6.setBackgroundResource(R.color.defult);btn6.isEnabled=true
        btn7.text="";btn7.setBackgroundResource(R.color.defult);btn7.isEnabled=true
        btn8.text="";btn8.setBackgroundResource(R.color.defult);btn8.isEnabled=true
        btn9.text="";btn9.setBackgroundResource(R.color.defult);btn9.isEnabled=true
        defualtplayer=1;player1.clear();player2.clear()
        vib()
    }
    // the vibrator method.
    private fun vib(){
        val vib:Vibrator= getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vib.vibrate(40)
    }
    // autoplay method.
     fun auto(){

        val list=ArrayList<Int>()
        for (index in 0..9) {
            if(!(player1.contains(index.toByte()) || player2.contains(index.toByte()))){
                list.add(index)
            }
        }
        val random=Random
        val r=random.nextInt(list.size-0)+0
        val num= list[r]
        var butn=Button(this)
        when(num){
            1->butn=btn1
            2->butn=btn2
            3->butn=btn3
            4->butn=btn4
            5->butn=btn5
            6->butn=btn6
            7->butn=btn7
            8->butn=btn8
            9->butn=btn9
        }
        game(butn,num.toByte())
        butn.isEnabled=false
    }
    // dialog method.
    private fun dialog(){
      val dial= AlertDialog.Builder(this)
        dial.setTitle("Dialog")
        dial.setMessage("matter")
        dial.setCancelable(false)
        dial.setPositiveButton("ok",null)
        dial.setNegativeButton("no",null)
        dial.setNeutralButton("cansel",null)
        dial.setIcon(R.drawable.ic_flight_takeoff_black_24dp)
        dial.create()
        dial.show()
    }
    // timer method.
    private fun timer(){
        Timer().schedule(1000){
            // do something.
        }
        Handler().postDelayed({
            // do something.
        },1000)
    }
    // sound method.
    private fun sound(){
        val media=MediaPlayer.create(this, null)
        media.start()

    }

}