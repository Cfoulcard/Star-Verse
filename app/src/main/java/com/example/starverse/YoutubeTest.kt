package com.example.starverse

import android.os.Bundle
import android.view.View
import com.google.android.youtube.player.*

open class YoutubeTest : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private var playerView: YouTubePlayerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.youtube_test)
        playerView = findViewById<View>(R.id.video_content) as YouTubePlayerView
        // playerView.initialize(DEVELOPER_KEY, (OnInitializedListener) this);
        playerView!!.initialize(YOUTUBE_API_KEY, this)
    }

    override fun onInitializationFailure(
        arg0: YouTubePlayer.Provider?,
        arg1: YouTubeInitializationResult?
    ) {
        // TODO Auto-generated method stub7
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer, wasRestored: Boolean
    ) {
        if (!wasRestored) {
            player.cueVideo(VEDIO_ID) // for indivitual video
            //	player.loadVideo(VEDIO_PLAY_LIST_ID); // for play list
        }
    }

    protected val youTubePlayerProvider: YouTubePlayer.Provider
        get() = findViewById<View>(R.id.video_content) as YouTubePlayerView

    companion object {
        // for individual video
        const val VEDIO_ID = "FQgeT36R4ew" // You can change video id

        //public static final String VEDIO_PLAY_LIST_ID ="72CF07D200AA2AFA"; // for play list
        const val DEVELOPER_KEY = "Add you Developer key here"
    }
}


