/**
 * Z-Music Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.zmusic.app.models

import com.zmusic.innertube.models.YTItem
import com.zmusic.app.db.entities.LocalItem

data class SimilarRecommendation(
    val title: LocalItem,
    val items: List<YTItem>,
)
