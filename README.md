# java-weather-api
Sample code for fetching forecast weather data from the National Weather Service using their API. It parses the results as JSON and outputs some forecast information.

This code follows, step by step, the same types of calls made by the Python and PHP versions.

Developed with Apache NetBeans 12.4 on macOS. Tested on macOS in NetBeans with JDK 16.

Requires: https://github.com/stleary/JSON-java

The NWS API information is located at: https://weather-gov.github.io/api/general-faqs

The code uses the sample lat-lon provided by the faq (the location of the Washington Monument); you can change this to any other appropriate location.

Note that once you get back the forecast URL (for the sample it's https://api.weather.gov/gridpoints/LWX/96,70/forecast) you can use this in a browser to see what the actual forecast elements are.
