package com.obstacle.avoid.system.debug

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import com.obstacle.avoid.common.Mappers
import com.obstacle.avoid.component.BoundsComponent


class DebugRenderSystem(
        private val viewport: Viewport,
        private val renderer: ShapeRenderer)
    : IteratingSystem(FAMILY) {


    companion object {
        private val FAMILY = Family.all(BoundsComponent::class.java).get()
    }

    override fun update(deltaTime: Float) {
        val oldColor = renderer.color.cpy()

        viewport.apply()
        renderer.projectionMatrix = viewport.camera.combined

        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.color = Color.RED

        super.update(deltaTime)

        renderer.end()
        renderer.color = oldColor
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bc = Mappers.BOUNDS.get(entity)
        renderer.circle(bc.bounds.x, bc.bounds.y, bc.bounds.radius, 30)
    }

}
