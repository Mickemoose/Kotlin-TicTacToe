package com.example.tictactoe

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private var playerTurn : String = "O" // Player Turn
    private var oScore : Int = 0 // Player O score
    private var xScore : Int = 0 // Player X score
    private lateinit var button1 : Button
    private lateinit var button2 : Button
    private lateinit var button3 : Button
    private lateinit var button4 : Button
    private lateinit var button5 : Button
    private lateinit var button6 : Button
    private lateinit var button7 : Button
    private lateinit var button8 : Button
    private lateinit var button9 : Button
    private lateinit var xScoreView : TextView
    private lateinit var oScoreView : TextView
    private lateinit var winSound : MediaPlayer
    private lateinit var tapSound : MediaPlayer
    private var gameOver : Boolean = false
    private val buttons = ArrayList<Button>() //ArrayList to hold all the buttons

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = this.findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        winSound = MediaPlayer.create(this, R.raw.win)
        tapSound = MediaPlayer.create(this, R.raw.tap)
        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)
        button5 = findViewById(R.id.btn5)
        button6 = findViewById(R.id.btn6)
        button7 = findViewById(R.id.btn7)
        button8 = findViewById(R.id.btn8)
        button9 = findViewById(R.id.btn9)

        oScoreView = findViewById(R.id.oScoreUI)
        xScoreView = findViewById(R.id.xScoreUI)
        xScoreView.text = "Score: $xScore"
        oScoreView.text = "Score: $oScore"
        buttons.add(button1)
        buttons.add(button2)
        buttons.add(button3)
        buttons.add(button4)
        buttons.add(button5)
        buttons.add(button6)
        buttons.add(button7)
        buttons.add(button8)
        buttons.add(button9)
        //Defined every button and added them to the buttons list
        //Then I use a for loop to set all of them to the same click listener
        for (button in buttons)
            button.setOnClickListener {
                if(button.text.isEmpty() && !gameOver ){
                    tapSound.start()
                    if(playerTurn == "X") button.setTextColor(Color.WHITE)
                    if(playerTurn == "O") button.setTextColor(Color.RED)
                    button.text = playerTurn
                    if(checkWin())
                        winGame(playerTurn)
                    else
                        turnChange(playerTurn)
                }
            }

    }
    // Menu icons are inflated just as they were with actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    private fun winGame(turn: String){
        when(turn){

            "O" -> {
                oScore++
                oScoreView.text = "Score: $oScore"
            }

            "X" -> {
                xScore++
                xScoreView.text = "Score: $xScore"
            }
        }
        winSound.start()
        gameOver = true
        //resetGame()
    }

    //Function handles resetting the game
    private fun resetGame() {
        for(button in buttons){
            button.text = ""
            turnChange(playerTurn)
            gameOver = false
        }
    }
    //Function to handle changing whos turn it is.
    private fun turnChange(turn: String) {
        when(turn) {
            "O" -> playerTurn = "X"
            "X" -> playerTurn = "O"
        }
    }

    private fun checkWin(): Boolean {
        return checkRows() || checkCols() || checkDiag()
    }
    private fun checkRows(): Boolean {
        for(button in buttons) {
            if(button1.text == playerTurn && button2.text == playerTurn && button3.text == playerTurn){
                button1.setTextColor(Color.GREEN)
                button2.setTextColor(Color.GREEN)
                button3.setTextColor(Color.GREEN)
                return true
            }
            if(button4.text == playerTurn && button5.text == playerTurn && button6.text == playerTurn){
                button4.setTextColor(Color.GREEN)
                button5.setTextColor(Color.GREEN)
                button6.setTextColor(Color.GREEN)
                return true
            }
            if(button7.text == playerTurn && button8.text == playerTurn && button9.text == playerTurn){
                button7.setTextColor(Color.GREEN)
                button8.setTextColor(Color.GREEN)
                button9.setTextColor(Color.GREEN)
                return true
            }
        }
        return false
    }
    private fun checkCols(): Boolean {
        for(button in buttons) {
            if(button1.text == playerTurn && button4.text == playerTurn && button7.text == playerTurn){
                button1.setTextColor(Color.GREEN)
                button4.setTextColor(Color.GREEN)
                button7.setTextColor(Color.GREEN)
                return true
            }
            if(button2.text == playerTurn && button5.text == playerTurn && button8.text == playerTurn){
                button2.setTextColor(Color.GREEN)
                button5.setTextColor(Color.GREEN)
                button8.setTextColor(Color.GREEN)
                return true
            }
            if(button3.text == playerTurn && button6.text == playerTurn && button9.text == playerTurn){
                button3.setTextColor(Color.GREEN)
                button6.setTextColor(Color.GREEN)
                button9.setTextColor(Color.GREEN)
                return true
            }
        }
        return false
    }

    private fun checkDiag(): Boolean {
        for(button in buttons) {
            if(button1.text == playerTurn && button5.text == playerTurn && button9.text == playerTurn) {
                button1.setTextColor(Color.GREEN)
                button5.setTextColor(Color.GREEN)
                button9.setTextColor(Color.GREEN)
                return true
            }
            if(button3.text == playerTurn && button5.text == playerTurn && button7.text == playerTurn) {
                button3.setTextColor(Color.GREEN)
                button5.setTextColor(Color.GREEN)
                button7.setTextColor(Color.GREEN)
                return true
            }
        }

        return false
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reset -> { resetGame() }
        }
    return true
    }
}