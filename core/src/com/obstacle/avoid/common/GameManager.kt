package com.obstacle.avoid.common

import com.badlogic.gdx.Gdx
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.config.DifficultyLevel
import com.obstacle.avoid.config.GameConfig

object GameManager {

    private val HIGH_SCORE_KEY = "highScore"
    private val DIFFICULTY_KEY = "difficulty"

    private val pref = Gdx.app.getPreferences(ObstacleAvoidGame::class.java.simpleName)
    // preferences is saved in HomePC/.prefs
    var highScore = pref.getInteger(HIGH_SCORE_KEY, 0)
    private var difficulty = pref.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name)
    var difficultyLevel = DifficultyLevel.valueOf(difficulty)
    var lives = GameConfig.LIVES_START
    var score = 0

    val isGameOver: Boolean
        get() = lives <= 0

    fun updateHighScore() {
        if (score < highScore) return
        highScore = score
        pref.putInteger(HIGH_SCORE_KEY, highScore)
        pref.flush() // commit to disk. ie flush from memory to disk
    }

    fun updateDifficulty(newDifficultyLevel: DifficultyLevel) {
        if (difficultyLevel == newDifficultyLevel) return
        difficultyLevel = newDifficultyLevel
        pref.putString(DIFFICULTY_KEY, difficultyLevel.name)
        pref.flush()
    }

    fun decrementLives() {
        lives--
    }

    fun reset() {
        lives = GameConfig.LIVES_START
        score = 0
    }

    fun updateScore(amount: Int) {
        score += amount
    }
}