package com.android.roomwordsample

import androidx.lifecycle.LiveData

class WordRepository (private val wordDao: WordDao){
    val allWords : LiveData<List<Word>> = wordDao.getAlphabetizedWord()

    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

}