package com.obstacle.avoid.common

import com.badlogic.ashley.core.PooledEngine
import com.obstacle.avoid.component.*
import com.obstacle.avoid.config.GameConfig


class EntityFactory(private val engine: PooledEngine) {


    fun addPlayer() {
        val x = (GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE) / 2f
        val y = 1 - GameConfig.PLAYER_SIZE / 2f

        val bounds = engine.createComponent(BoundsComponent::class.java)
        bounds.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS)

        val movement = engine.createComponent(MovementComponent::class.java)

        val player = engine.createComponent(PlayerComponent::class.java)

        val position = engine.createComponent(PositionComponent::class.java)
        position.x = x
        position.y = y

        val worldWrap = engine.createComponent(WorldWrapComponent::class.java)

        val entity = engine.createEntity().apply {
            add(bounds)
            add(movement)
            add(player)
            add(position)
            add(worldWrap)

        }

        engine.addEntity(entity)
    }

    fun addObstacle(x: Float, y: Float) {
        val bounds = engine.createComponent(BoundsComponent::class.java).apply {
            bounds.set(x, y, GameConfig.OBSTACLE_BOUNDS_RADIUS)
        }

        val movement = engine.createComponent(MovementComponent::class.java).apply {
            ySpeed = -GameManager.difficultyLevel.obstacleSpeed
        }

        val position = engine.createComponent(PositionComponent::class.java).apply {
            this.x = x
            this.y = y
        }

        val entity = engine.createEntity().apply {
            add(bounds)
            add(movement)
            add(position)
//            add(engine.createComponent(WorldWrapComponent::class.java))

        }

        engine.addEntity(entity)
    }
}
