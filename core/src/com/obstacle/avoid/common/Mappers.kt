package com.obstacle.avoid.common

import com.badlogic.ashley.core.ComponentMapper
import com.obstacle.avoid.component.*


object Mappers {

    val BOUNDS: ComponentMapper<BoundsComponent> = ComponentMapper.getFor(BoundsComponent::class.java)

    val MOVEMENT: ComponentMapper<MovementComponent> = ComponentMapper.getFor(MovementComponent::class.java)

    val POSITION: ComponentMapper<PositionComponent> = ComponentMapper.getFor(PositionComponent::class.java)

    val OBSTACLE: ComponentMapper<ObstacleComponent> = ComponentMapper.getFor(ObstacleComponent::class.java)

    val TEXTURE: ComponentMapper<TextureComponent> = ComponentMapper.getFor(TextureComponent::class.java)

    val DIMENSION: ComponentMapper<DimensionComponent> = ComponentMapper.getFor(DimensionComponent::class.java)

}
