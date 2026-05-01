/**
 * Z-Music Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.zmusic.app.lyrics

import android.content.Context
import com.zmusic.app.betterlyrics.BetterLyrics
import com.zmusic.app.constants.EnableBetterLyricsKey
import com.zmusic.app.utils.dataStore
import com.zmusic.app.utils.get

object BetterLyricsProvider : LyricsProvider {
    override val name = "BetterLyrics"

    override fun isEnabled(context: Context): Boolean = context.dataStore[EnableBetterLyricsKey] ?: true

    override suspend fun getLyrics(
        context: Context,
        id: String,
        title: String,
        artist: String,
        duration: Int,
        album: String?,
    ): Result<String> = BetterLyrics.getLyrics(title, artist, duration, album)

    override suspend fun getAllLyrics(
        context: Context,
        id: String,
        title: String,
        artist: String,
        duration: Int,
        album: String?,
        callback: (String) -> Unit,
    ) {
        BetterLyrics.getAllLyrics(title, artist, duration, album, callback)
    }
}
