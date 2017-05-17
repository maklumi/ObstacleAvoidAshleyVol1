package com.obstacle.avoid.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.obstacle.avoid.common.Mappers
import com.obstacle.avoid.component.CleanUpComponent
import com.obstacle.avoid.component.PositionComponent
import com.obstacle.avoid.config.GameConfig


class CleanUpSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(
                CleanUpComponent::class.java,
                PositionComponent::class.java
        ).get()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = Mappers.POSITION.get(entity)

        if (position.y < -GameConfig.OBSTACLE_SIZE) {
            engine.removeEntity(entity)
        }
    }


}
