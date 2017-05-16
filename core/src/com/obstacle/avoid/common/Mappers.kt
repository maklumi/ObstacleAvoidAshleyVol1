package com.obstacle.avoid.common

import com.badlogic.ashley.core.ComponentMapper
import com.obstacle.avoid.component.BoundsComponent
import com.obstacle.avoid.component.MovementComponent


object Mappers {

    val BOUNDS: ComponentMapper<BoundsComponent> = ComponentMapper.getFor(BoundsComponent::class.java)

    val MOVEMENT: ComponentMapper<MovementComponent> = ComponentMapper.getFor(MovementComponent::class.java)

}
