# Unit Test
## _Instrumentation Testing With Hilt Testing_


## Dependencies
Hilt
```
androidTestImplementation "com.google.dagger:hilt-android-testing:$hilt_version"
kaptAndroidTest "com.google.dagger:hilt-android-compiler:$hilt_version"
```

## Test Scenario :
- Open Application
- Find Weather by Input City 
  - Positive Case : User Input with valid character
  - Negative Case: User Input with invalid charachter, User let text field as empty
- Find Weather by Input GPS
  -    Positive Case : User successfully got Lat Long data
  -    Negative Case: User Failed to retrieve latlong data

_For more details about this unit testing please visit this dir_ : 
https://github.com/sebastianseno/openweather/blob/master/app/src/androidTest/java/com/dodolife/weather/WeatherTest.kt
