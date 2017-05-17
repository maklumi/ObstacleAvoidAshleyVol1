package com.obstacle.avoid.system

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.obstacle.avoid.common.GameManager
import com.obstacle.avoid.config.GameConfig


class HudRenderSystem(private val viewport: Viewport,
                      private val batch: SpriteBatch,
                      private val font: BitmapFont) : EntitySystem() {

    private val layout = GlyphLayout()

    override fun update(deltaTime: Float) {
        viewport.apply()
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        draw()
        batch.end()
    }

    private fun draw() {
        val livesString = "LIVES: " + GameManager.lives
        layout.setText(font, livesString)
        font.draw(batch, livesString, 20f, GameConfig.HUD_HEIGHT - layout.height)

        val scoreString = "SCORE: " + GameManager.score
        layout.setText(font, scoreString)
        font.draw(batch, scoreString, GameConfig.HUD_WIDTH - layout.width - 20f, GameConfig.HUD_HEIGHT - layout.height)
    }
}
