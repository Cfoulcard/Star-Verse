package com.example.starverse.ui

import android.os.Bundle
import com.example.starverse.YOUTUBE_API_KEY
import com.example.starverse.databinding.YoutubeTestBinding
import com.google.android.youtube.player.*

class YoutubeTest : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    // View Binding
    private lateinit var binding: YoutubeTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YoutubeTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  val youTubePlayerView = YouTubePlayerView(this)
      //  youTubePlayerView.initialize(YOUTUBE_API_KEY, this)

        val intent = YouTubeStandalonePlayer.createVideoIntent(this, YOUTUBE_API_KEY, "H7qoj9xDJ2I")
        startActivity(intent)

       //  binding.videoContent.initialize(YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.loadVideo("H7qoj9xDJ2I")
        p1?.play()
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        TODO("Not yet implemented")
    }
}