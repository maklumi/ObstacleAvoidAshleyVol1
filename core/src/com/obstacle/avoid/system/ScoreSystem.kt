package com.obstacle.avoid.system

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.obstacle.avoid.common.GameManager
import com.obstacle.avoid.config.GameConfig


class ScoreSystem : IntervalSystem(GameConfig.SCORE_INTERVAL) {

    override fun updateInterval() {
        GameManager.updateScore(MathUtils.random(1, 5))
    }
}
