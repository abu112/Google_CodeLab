package com.example.android.guesstheword.screens.game

import android.content.Context
import android.content.IntentSender
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlin.coroutines.coroutineContext

class GameViewModel: ViewModel(){

    companion object{
        //These represent different important times in the game, such as game length.

        //This is when the game is over
        private const val  DONE = 0L

        //This is the number of milliseconds in a second
        private const val ONE_SECOND = 1000L

        //This is the total time of the game
        private const val COUNTDOWN_TIME = 10000L

    }

    private val timer: CountDownTimer

    // The current word
    private val _word = MutableLiveData<String>()
    val word : LiveData<String>
    get() = _word
    // The current score

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long>
    get() = _currentTime
    val currentTimeString = Transformations.map(_currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init{
        _currentTime.value = 0
        _eventGameFinish.value = false
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisecondFinished: Long) {
                _currentTime.value = millisecondFinished/ ONE_SECOND
            }

            override fun onFinish() {
                _eventGameFinish.value = true
            }
        }


//        DateUtils.formatElapsedTime(_timerdata.value)

        timer.start()

    }


    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }


    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
//            _eventGameFinish.value = true
            //gameFinished()
        }
            _word.value = wordList.removeAt(0)

    }
    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

}