package com.obstacle.avoid.common

import com.badlogic.ashley.core.ComponentMapper
import com.obstacle.avoid.component.BoundsComponent


object Mappers {

    val BOUNDS: ComponentMapper<BoundsComponent> = ComponentMapper.getFor(BoundsComponent::class.java)

}
