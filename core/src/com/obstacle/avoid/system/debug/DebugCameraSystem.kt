package com.obstacle.avoid.system.debug

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.obstacle.avoid.util.DebugCameraController


class DebugCameraSystem(
        private val camera: OrthographicCamera,
        startX: Float, startY: Float) : EntitySystem() {

    init {
        DebugCameraController.startPosition = Vector2(startX, startY)
    }

    override fun update(deltaTime: Float) {
        DebugCameraController.handleDebugInput(deltaTime)
        DebugCameraController.applyPositionToCamera(camera)
    }

}
