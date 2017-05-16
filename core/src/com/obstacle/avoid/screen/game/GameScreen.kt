package com.obstacle.avoid.screen.game

import com.badlogic.gdx.Screen
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors


class GameScreen(val game: ObstacleAvoidGame) : Screen {

    private val assetManager = game.assetManager

    override fun show() {
        assetManager.load(AssetDescriptors.FONT)
        assetManager.load(AssetDescriptors.GAME_PLAY)
        assetManager.finishLoading()

    }

    override fun render(delta: Float) {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun hide() {
        dispose()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
    }
}
