package com.example.starverse.utilties

// Create JSON Object using JSONObjectRequest
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, nasaUrlKey, null,
//                { response ->
//                    try {
//
//                        /* The response call may crash if getActivity() is null. This will return the
//                         response results if the getActivity() is not null. This is needed, or app
//                         may crash! */
//                        if (activity != null) {
//
//                            // Places NASA's image/video in the imageView via Glide
//                            if (response.has("hdurl")) {
//                              //  binding.videoContent.visibility = GONE
//                                val loadImage = Glide.with(this)
//                                        .load(response.getString("hdurl"))
//                                        .into(binding.mediaContent)
//
//                            } else if (response.has("url")) {
//                                binding.mediaContent.visibility = GONE
//
//                             //   youtubeVideoSuperPlayer?.youTubeVidPlayer = binding.videoContent
//                            }
//
//                            // Get title information from JSON if listed in API - Hide if not listed
//                            if (response.has("title")) {
//                                binding.titleText.text = "%s".format(response["title"])
//                            } else {
//                                binding.titleCardview.visibility = GONE
//                            }
//
//                            // Get copyright information from JSON if listed in API - Hide if not listed
//                            if (response.has("copyright")) {
//                                binding.copyrightText.text = "Captured by %s".format(response["copyright"])
//                            } else {
//                                binding.copyrightCardview.visibility = GONE
//                            }
//
//                            // Get description information from JSON if listed in API - Hide if not listed
//                            if (response.has("explanation")) {
//                                binding.descriptionText.text = "%s".format(response["explanation"])
//                            } else {
//                                binding.descriptionCardview.visibility = GONE
//                            }
//                        }
//                    } catch (e: JSONException) {
//                    }
//                },
//                { error ->
//                    Toast.makeText(context, "Cannot get info", Toast.LENGTH_SHORT).show()
//                }
//        )
//
//        // Use the RequestQueue via the Singleton class which is needed to parse the JSON
//         // Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
//        queue.add(jsonObjectRequest)