package com.obstacle.avoid.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.Viewport
import com.obstacle.avoid.common.Mappers
import com.obstacle.avoid.component.DimensionComponent
import com.obstacle.avoid.component.PositionComponent
import com.obstacle.avoid.component.WorldWrapComponent


class WorldWrapSystem(private val viewport: Viewport) : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(
                WorldWrapComponent::class.java,
                PositionComponent::class.java,
                DimensionComponent::class.java

        ).get()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val pc = Mappers.POSITION.get(entity)
        val dimension = Mappers.DIMENSION.get(entity)

        pc.x = MathUtils.clamp(pc.x, 0f, viewport.worldWidth - dimension.width)
        pc.y = MathUtils.clamp(pc.y, 0f, viewport.worldHeight - dimension.height)
    }


}
