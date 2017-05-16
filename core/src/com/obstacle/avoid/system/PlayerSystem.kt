package com.obstacle.avoid.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.obstacle.avoid.common.Mappers
import com.obstacle.avoid.component.MovementComponent
import com.obstacle.avoid.component.PlayerComponent
import com.obstacle.avoid.config.GameConfig


class PlayerSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(
                PlayerComponent::class.java,
                MovementComponent::class.java
        ).get()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val movement = Mappers.MOVEMENT.get(entity)
        movement.xSpeed = 0f

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movement.xSpeed = GameConfig.MAX_PLAYER_X_SPEED
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movement.xSpeed = -GameConfig.MAX_PLAYER_X_SPEED
        }
    }

}
