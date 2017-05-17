package com.obstacle.avoid.screen.game

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.common.EntityFactory
import com.obstacle.avoid.common.GameManager
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.screen.menu.MenuScreen
import com.obstacle.avoid.system.*
import com.obstacle.avoid.system.collision.CollisionListener
import com.obstacle.avoid.system.collision.CollisionSystem
import com.obstacle.avoid.system.debug.DebugCameraSystem
import com.obstacle.avoid.system.debug.DebugRenderSystem
import com.obstacle.avoid.system.debug.GridRenderSystem
import com.obstacle.avoid.util.GdxUtils


class GameScreen(val game: ObstacleAvoidGame) : Screen {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    private val renderer = ShapeRenderer()
    private val engine = PooledEngine()
    private val factory = EntityFactory(engine)
    private val hudViewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
    private val font = game.assetManager.get(AssetDescriptors.FONT)
    private val hit = game.assetManager.get(AssetDescriptors.HIT_SOUND)
    private var reset: Boolean = false

    private val listener = object : CollisionListener {
        override fun hitObstacle() {
            GameManager.decrementLives()
            hit.play()

            if (GameManager.isGameOver) {
                GameManager.updateHighScore()
            } else {
                engine.removeAllEntities()
                reset = true
            }
        }
    }


    override fun show() {
        engine.apply {
            addSystem(GridRenderSystem(viewport, renderer))
            addSystem(DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y))
            addSystem(DebugRenderSystem(viewport, renderer))
            addSystem(PlayerSystem())
            addSystem(MovementSystem())
            addSystem(ObstacleSpawnSystem(factory))
            addSystem(CleanUpSystem())
            addSystem(CollisionSystem(listener))
            addSystem(ScoreSystem())
            addSystem(WorldWrapSystem(viewport))
            addSystem(BoundsSystem())
            addSystem(HudRenderSystem(hudViewport, game.batch, font))
        }

        factory.addPlayer()

    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()
        engine.update(delta)

        if (GameManager.isGameOver) {
            GameManager.reset()
            game.screen = MenuScreen(game)
        }

        if (reset) {
            reset = false
            addEntities()
        }
    }

    private fun addEntities() {
        factory.addPlayer()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        hudViewport.update(width, height, true)
    }

    override fun hide() {
        dispose()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        renderer.dispose()
    }
}
