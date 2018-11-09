# weatherpreview

Simple Android App focused on show forecast

There are two main folders: documentation and android

## Documentation

Folder related to documentation - UI, UX and Assets

## Android

Folder containing the Android project itself, tested under Android Studio 3.2.1.

# FOR CORRECT USE, PLEASE ADD KEYS AND TOKENS AT ANDROID PROKJET (strings.xml)

- < string name="weather_key" translatable="false">XXXXXXXXXXXXXX</ string>

- < string name="facebook_app_id" translatable="false">XXXXXXXXXXXXXX</ string>

- < string name="fb_login_protocol_scheme" translatable="false">XXXXXXXXXXXXXX</ string>

## Android Requirements

- API 19 or higher

- Uses of APP Compat

- Material Design Guideline

- Kotlin

## System Requirements

- Must have 2 main UIs

- First one searches position over GPS and shows local forecast/weather.

- Second shows London`s forecast/weather

- use the provided API https://openweathermap.org/api

## Missing Points

- Finish UI Design, UX and Assets
- Add Loading feedback to user
- Change Facebook Login from Fragment to a new Activity and use Intent to change between Main and Login
- Persist last Location and Weather info to improve the UX
