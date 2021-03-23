# Star Verse ☄ 

<p align = "center"><img height="80%px" width="80%px" src ="https://github.com/Cfoulcard/Star-Verse/blob/main/preview_pics/banner_long.png"></p>

<p align = "center">Star verse is an open source star gazing Android application equipping you with the power to view pictures of the universe 🌌 The ability to view these images is gifted by NASA's API and (soon to be) Astrobin's API.</p>
<br>

<img height="35%px" width="35%px" src ="https://github.com/Cfoulcard/Star-Verse/blob/main/preview_pics/homepage.png"> <img height="55%px" width="55%px" src ="https://github.com/Cfoulcard/Star-Verse/blob/main/preview_pics/homepage_2.png">



## Technologies in Use

- Kotlin
- Material Design
- Fragments
- Navigation Graphs
- Volley (For RESTful API)
- JSON
- Glide (For displaying images through JSON)
- [Curved Bottom Navigation Library](https://github.com/susonthapa/curved-bottom-navigation) by Susonthapa
- ViewBinding
- ViewModels
- Dark Mode (Halfway complete)
- YouTube's API

## Tools Used
- Android Studio
- Afinity Designer (Graphic + Vector Image Editing)
- Typora (For Easy Markdown styling)

## Future Features of the App:

- Implement Astrobin's search engine to find images of the universe
- Add a settings menu to toggle options such as day/night mode and notifications
- Possibly add details about objects in space through APIs, including weather
- Upgrade animations throughout the app (such as loading images and more interactive UI)
- Add a share button feature

## State of the App

**🛠This app is currently a work in progress🛠** A future release will be planned on the Google Play Store!

## Contributing

Because this is an open source project, anyone familiar with the technologies used to build Android Apps is allowed to help. There are a couple of features that are to be developed in the app, and here as well as within the app will be a list of contributors who helped build the finished product! 

To contribute, please view the [contributing guidelines](https://github.com/Cfoulcard/Star-Verse/blob/main/contributing.md). It is important to note that the API Key used to parse pictures and other information will not be provided by me, but you can follow this [NASA API Generator Link](https://api.nasa.gov/) and this [Astrobin API](https://welcome.astrobin.com/application-programming-interface) to generate your keys and plug them into code where necessary. **Please do not publicly display your API Key, store in a different object/class and do not list it in your pull request!** 

The API code will look like this:

```kotlin
val url = "https://api.nasa.gov/planetary/apod/?api_key=$API_KEY"
```

Where API_KEY is the variable used to parse the API Key.
