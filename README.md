# RickAndMorty

The app has three main features:
- Show a list of characters.
- Search for more characters by writing on the text field.
- Show the details of a character by clicking on any element of the list of characters.

The architecture is divided into two layers: the UI layer and the Data layer.

For the UI layer, I am using Jetpack Compose, Jetpack Navigation for handling the navigation to the character detail, and Coil to manage the images from the network because it already has an image cache by default.

For the Data Layer, I am using Retrofit to handle the HTTP requests to the server and Room to handle a local database. The local database is used to show a list of characters when the app starts, even if there is not network connection.

Dagger Hilt is used in both layers to handle dependency injection.

There are three types of tests:
- End-to-end tests: The test in CharactersTest.kt checks when a user selects a character and navigates to the CharacterDetailScreen from the CharactersScreen.
- Integration tests:  The tests in CharactersScreenTest.kt and CharactersDetailScreenTest verify the functionality of each screen and validate the interactions with the UI.
- Unit tests:  The tests in CharactersViewModelTest.kt, CharacterDetailViewModelTest.kt and DefaultCharactersRepositoryTeset.kt verify the viewModels and the DefaultCharactersRepository methods.
