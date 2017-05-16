package com.obstacle.avoid.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.obstacle.avoid.common.Mappers
import com.obstacle.avoid.component.MovementComponent
import com.obstacle.avoid.component.PositionComponent


class MovementSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(
                PositionComponent::class.java,
                MovementComponent::class.java
        ).get()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = Mappers.POSITION.get(entity)
        val movement = Mappers.MOVEMENT.get(entity)

        position.x += movement.xSpeed
        position.y += movement.ySpeed
    }


}
