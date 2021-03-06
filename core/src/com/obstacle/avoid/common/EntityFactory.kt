package com.obstacle.avoid.common

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.assets.AssetManager
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.assets.RegionNames
import com.obstacle.avoid.component.*
import com.obstacle.avoid.config.GameConfig


class EntityFactory(private val engine: PooledEngine, assetManager: AssetManager) {

    private val gamePlayAtlas = assetManager.get(AssetDescriptors.GAME_PLAY)

    fun addPlayer() {
        val x = (GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE) / 2f
        val y = 1 - GameConfig.PLAYER_SIZE / 2f

        val bounds = engine.createComponent(BoundsComponent::class.java)
        bounds.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS)

        val movement = engine.createComponent(MovementComponent::class.java)

        val player = engine.createComponent(PlayerComponent::class.java)

        val position = engine.createComponent(PositionComponent::class.java)
        position.x = x
        position.y = y

        val worldWrap = engine.createComponent(WorldWrapComponent::class.java)

        val texture = engine.createComponent(TextureComponent::class.java)
        texture.region = gamePlayAtlas.findRegion(RegionNames.PLAYER)

        val dimension = engine.createComponent(DimensionComponent::class.java)
        dimension.width = GameConfig.PLAYER_SIZE
        dimension.height = GameConfig.PLAYER_SIZE

        val entity = engine.createEntity().apply {
            add(bounds)
            add(movement)
            add(player)
            add(position)
            add(worldWrap)
            add(texture)
            add(dimension)

        }

        engine.addEntity(entity)
    }

    fun addObstacle(x: Float, y: Float) {
        val bounds = engine.createComponent(BoundsComponent::class.java).apply {
            bounds.set(x, y, GameConfig.OBSTACLE_BOUNDS_RADIUS)
        }

        val movement = engine.createComponent(MovementComponent::class.java).apply {
            ySpeed = -GameManager.difficultyLevel.obstacleSpeed
        }

        val position = engine.createComponent(PositionComponent::class.java).apply {
            this.x = x
            this.y = y
        }

        val cleanUp = engine.createComponent(CleanUpComponent::class.java)
        val obstacle = engine.createComponent(ObstacleComponent::class.java)

        val texture = engine.createComponent(TextureComponent::class.java)
        texture.region = gamePlayAtlas.findRegion(RegionNames.OBSTACLE)

        val dimension = engine.createComponent(DimensionComponent::class.java)
        dimension.width = GameConfig.OBSTACLE_SIZE
        dimension.height = GameConfig.OBSTACLE_SIZE

        val entity = engine.createEntity().apply {
            add(bounds)
            add(movement)
            add(position)
            add(cleanUp)
            add(obstacle)
            add(texture)
            add(dimension)
        }

        engine.addEntity(entity)
    }

    fun addBackground() {
        val texture = engine.createComponent(TextureComponent::class.java)
        texture.region = gamePlayAtlas.findRegion(RegionNames.BACKGROUND)

        val position = engine.createComponent(PositionComponent::class.java)
        position.x = 0f
        position.y = 0f

        val dimension = engine.createComponent(DimensionComponent::class.java)
        dimension.width = GameConfig.WORLD_WIDTH
        dimension.height = GameConfig.WORLD_HEIGHT

        val entity = engine.createEntity().apply {
            add(texture)
            add(position)
            add(dimension)
        }

        engine.addEntity(entity)
    }
}
