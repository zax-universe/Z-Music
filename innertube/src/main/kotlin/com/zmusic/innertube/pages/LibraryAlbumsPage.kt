package com.zmusic.innertube.pages

import com.zmusic.innertube.models.Album
import com.zmusic.innertube.models.AlbumItem
import com.zmusic.innertube.models.Artist
import com.zmusic.innertube.models.ArtistItem
import com.zmusic.innertube.models.MusicResponsiveListItemRenderer
import com.zmusic.innertube.models.MusicTwoRowItemRenderer
import com.zmusic.innertube.models.PlaylistItem
import com.zmusic.innertube.models.SongItem
import com.zmusic.innertube.models.YTItem
import com.zmusic.innertube.models.oddElements
import com.zmusic.innertube.utils.parseTime

data class LibraryAlbumsPage(
    val albums: List<AlbumItem>,
    val continuation: String?,
) {
    companion object {
        fun fromMusicTwoRowItemRenderer(renderer: MusicTwoRowItemRenderer): AlbumItem? {
            return AlbumItem(
                        browseId = renderer.navigationEndpoint.browseEndpoint?.browseId ?: return null,
                        playlistId = renderer.thumbnailOverlay?.musicItemThumbnailOverlayRenderer?.content
                            ?.musicPlayButtonRenderer?.playNavigationEndpoint
                            ?.watchPlaylistEndpoint?.playlistId ?: return null,
                        title = renderer.title.runs?.firstOrNull()?.text ?: return null,
                        artists = null,
                        year = renderer.subtitle?.runs?.lastOrNull()?.text?.toIntOrNull(),
                        thumbnail = renderer.thumbnailRenderer.musicThumbnailRenderer?.getThumbnailUrl() ?: return null,
                        explicit = renderer.subtitleBadges?.find {
                            it.musicInlineBadgeRenderer?.icon?.iconType == "MUSIC_EXPLICIT_BADGE"
                        } != null
                    )
        }
    }
}
