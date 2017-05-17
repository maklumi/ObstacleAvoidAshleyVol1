package com.obstacle.avoid.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool


class ObstacleComponent : Component, Pool.Poolable {

    var hit: Boolean = false

    //make sure to reset after collision done
    //reset is called after entity is removed
    override fun reset() {
        hit = false
    }
}
