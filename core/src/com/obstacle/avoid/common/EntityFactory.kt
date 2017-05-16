package com.obstacle.avoid.common

import com.badlogic.ashley.core.PooledEngine
import com.obstacle.avoid.component.BoundsComponent
import com.obstacle.avoid.config.GameConfig


class EntityFactory(private val engine: PooledEngine) {


    fun addPlayer() {
        val x = (GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE) / 2f
        val y = 1 - GameConfig.PLAYER_SIZE / 2f

        val bounds = engine.createComponent(BoundsComponent::class.java)
        bounds.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS)

        val entity = engine.createEntity().apply {
            add(bounds)
        }

        engine.addEntity(entity)
    }


}
