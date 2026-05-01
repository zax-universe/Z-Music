package com.zmusic.innertube.models.body

import com.zmusic.innertube.models.Context
import com.zmusic.innertube.models.Continuation
import kotlinx.serialization.Serializable

@Serializable
data class BrowseBody(
    val context: Context,
    val browseId: String?,
    val params: String?,
    val continuation: String?
)
