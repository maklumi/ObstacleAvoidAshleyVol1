package com.obstacle.avoid.system

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.obstacle.avoid.common.EntityFactory
import com.obstacle.avoid.config.GameConfig


class ObstacleSpawnSystem(private val factory: EntityFactory) : IntervalSystem(GameConfig.OBSTACLE_SPAWN_TIME) {

    override fun updateInterval() {
        val obstacleX = MathUtils.random(0f, GameConfig.WORLD_WIDTH - GameConfig.OBSTACLE_SIZE)
        val obstacleY = GameConfig.WORLD_HEIGHT

        factory.addObstacle(obstacleX, obstacleY)
    }
}
