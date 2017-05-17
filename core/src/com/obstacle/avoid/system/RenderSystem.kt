package com.obstacle.avoid.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.Viewport
import com.obstacle.avoid.common.Mappers
import com.obstacle.avoid.component.DimensionComponent
import com.obstacle.avoid.component.PositionComponent
import com.obstacle.avoid.component.TextureComponent


class RenderSystem(private val viewport: Viewport,
                   private val batch: SpriteBatch) : EntitySystem() {

    companion object {
        private val FAMILY = Family.all(
                TextureComponent::class.java,
                PositionComponent::class.java,
                DimensionComponent::class.java
        ).get()
    }

    private val renderQueue = Array<Entity>()

    override fun update(deltaTime: Float) {
        val entities = engine.getEntitiesFor(FAMILY)
        entities.forEach { renderQueue.add(it) }

        viewport.apply()
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        draw()
        batch.end()

        renderQueue.clear()
    }

    private fun draw() {
        for (entity in renderQueue) {
            val position = Mappers.POSITION.get(entity)
            val dimension = Mappers.DIMENSION.get(entity)
            val texture = Mappers.TEXTURE.get(entity) ?: continue

            batch.draw(texture.region!!,
                    position.x, position.y,
                    dimension.width, dimension.height)
        }
    }

}
