# Ribot Developer Challenge, by Andrew

## Build / Software Requirements

* Android Studio with gradle version 2.1.3
* Android SDK build tools version 24.0.2
* Android SDK v24 (Nougat) [but it should compile with API23 if you don't already have 24 ready]
* All other dependencies (libraries, etc) are listed in the app's `build.gradle` files
* Min SDK for running set at API 17

## Build Instructions

* Simply hit build / run / debug in android studio
* There are two product flavors included as a result of working without the live API for a while. Select the `onlineApi` flavor in the Build Variants window in Android Studio before building / running.
* You can run the unit tests in the test directory with a right-click and 'Run' - I haven't included the tests in the build / run scripts

## Overview of the app

### User Interface

There are two main screens:

* The Ribots List (`MainRibotsActivity`)
  * Overview grid of Ribot people with picture and name
* Ribot profile (`RibotProfileActivity`)
  * More info on each Ribot person

I aimed to follow google's Material Design principles and made use of shared element Activity Transitions to give the user a feeling of continuity - watch for how the avatar moves between the list and the detail screens. I made use of the `CoordinatorLayout` with a collapsing toolbar in the detail view - this would be particularly useful if the API is extended to include more data about each Ribot team member, allowing the user to slide away the avatar and focus on the particulars.

### Architecture

Each screen is implemented using a Model-View-Presenter architecture to separate concerns cleanly and avoid business logic in the android `Activity`s. I aimed to keep the views as stupid as possible in terms of what they should display and allow the presenter to control what should be shown on screen. The presenter for the `MainRibotsActivity` also instantiates the access to the Ribot API (the Model) which is currently implemented using Retrofit. Note, however, that I abstracted the Retrofit interface behind the `RibotsClient` interface so that the Retrofit library could be swapped in for another library (or even a secondary offline-mode implementation) at a later date.

## Areas for improvement

* There are only a limited amount of tests. With further time, I would like to add more rigourous testing to increase coverage and improve depth. For example:
  * I would like to mock the Ribot API using something like [Mock Web Server](https://github.com/square/okhttp/tree/master/mockwebserver) to test slow connections, server errors, etc; making sure that appropriate error messages were shown.
  * I would like to add more involved Mockito object mocks rather than just verifying that certain methods are called in response to an input.
* Offline mode: At the moment the app needs a connection to get the Ribots. I would add a cache that maintains the most recent response from the API and displays the same information when offline (with a heads up to the user). It could be as simple as storing the JSON as a file and reading it in or by using a more structured format such as an SQLite DB.
* Make the Profile screen more interactive e.g. allow them to click on each property and have the app do something sensible - e.g. add a birthday reminder to your calendar, copy-paste a field.
* For maintainability and testing flexibility, I would like to make use of Dependency Injection (e.g. Dagger) but I'm still quite new to that library so didn't feel comfortable using it here this time.
* Design: The Ribots list screen is a bit sparse and could do with some kind of introduction for the user.

