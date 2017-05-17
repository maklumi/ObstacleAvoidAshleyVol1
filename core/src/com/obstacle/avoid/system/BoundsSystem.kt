package com.obstacle.avoid.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.obstacle.avoid.common.Mappers
import com.obstacle.avoid.component.BoundsComponent
import com.obstacle.avoid.component.DimensionComponent
import com.obstacle.avoid.component.PositionComponent


class BoundsSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(
                BoundsComponent::class.java,
                PositionComponent::class.java,
                DimensionComponent::class.java
        ).get()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bc = Mappers.BOUNDS.get(entity)
        val position = Mappers.POSITION.get(entity)
        val dimension = Mappers.DIMENSION.get(entity)

        bc.bounds.x = position.x + dimension.width / 2f
        bc.bounds.y = position.y + dimension.height / 2f
    }


}
