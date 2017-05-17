package com.obstacle.avoid.system.collision

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.graphics.profiling.GLProfiler.listener
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.utils.Logger
import com.obstacle.avoid.common.Mappers
import com.obstacle.avoid.component.BoundsComponent
import com.obstacle.avoid.component.ObstacleComponent
import com.obstacle.avoid.component.PlayerComponent


class CollisionSystem : EntitySystem() {

    private val log = Logger(CollisionSystem::class.java.simpleName, Logger.DEBUG)
//    private var counter = 0
    companion object {
        private val PLAYER_FAMILY = Family.all(
                PlayerComponent::class.java,
                BoundsComponent::class.java
        ).get()

        private val OBSTACLE_FAMILY = Family.all(
                ObstacleComponent::class.java,
                BoundsComponent::class.java
        ).get()
    }

    override fun update(deltaTime: Float) {
        // size of players array will always be 1 since we have only 1 player
        val players: ImmutableArray<Entity> = engine.getEntitiesFor(PLAYER_FAMILY)
        val obstacles = engine.getEntitiesFor(OBSTACLE_FAMILY)

        for (playerEntity in players) {
            for (obstacleEntity in obstacles) {
                val obstacle = Mappers.OBSTACLE.get(obstacleEntity)

                if (obstacle.hit) {
                    continue
                }

                if (checkCollision(playerEntity, obstacleEntity)) {
                    obstacle.hit = true
//                    counter++
//                    log.debug("collision $counter")
                }
            }
        }
    }

    private fun checkCollision(player: Entity, obstacle: Entity): Boolean {
        val playerBounds = Mappers.BOUNDS.get(player)
        val obstacleBounds = Mappers.BOUNDS.get(obstacle)
        return Intersector.overlaps(playerBounds.bounds, obstacleBounds.bounds)
    }

}
