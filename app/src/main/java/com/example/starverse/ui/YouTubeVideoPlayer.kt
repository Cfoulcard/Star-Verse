package com.example.starverse.ui

import com.example.starverse.YOUTUBE_API_KEY
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerView

class YouTubeVideoPlayer : YouTubePlayerFragment(), YouTubePlayer.OnInitializedListener {

    fun newInstance() {
        return YouTubeVideoPlayer()
    }

    fun init() {
        initialize(YOUTUBE_API_KEY, this)
    }

    private fun YouTubeVideoPlayer() {
        // Required empty public constructor
    }

    var youTubeVidPlayer: YouTubePlayerView? = null
    val getVideo = youTubeVidPlayer!!.initialize(YOUTUBE_API_KEY, this)

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?,
                                         player: YouTubePlayer?,
                                         wasRestored: Boolean
    ) {
        player?.loadVideo("wKJ9KzGQq0w")
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        TODO("Not yet implemented")
    }

}