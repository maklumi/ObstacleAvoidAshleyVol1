package com.obstacle.avoid.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool


class MovementComponent : Component, Pool.Poolable {

    var xSpeed = 0f
    var ySpeed = 0f

    override fun reset() {
        xSpeed = 0f
        ySpeed = 0f
    }
}
