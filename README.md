[![Build Status](https://travis-ci.org/otrosien/esplugin-spike.svg)](https://travis-ci.org/otrosien/esplugin-spike)

# Elasticsearch Plugin Spike

## Building

`./mvnw clean install`

`docker-compose build`

## Running

`docker-compose up -d`

## Observations

* Building: Elasticsearch Plugins (at least up to the current release) can only be built with `maven`, not with `gradle`
  due to a restrictive `SecurityManager` being introduced during testing.
  Elasticsearch itself has switched to `gradle` in `master`, so it is likely to be fixed with the next major release.
  Details are here: https://discuss.gradle.org/t/gradle-consistently-stuck-at-test-task/14125/11

* Developing: Elasticsearch relies on `Guice` for dependency injection and plugin management.
  Every plugin has its own classloader to avoid side-effects.

* Testing: `ESTestCase`, `ESIntegTestCase` and `ESRestTestCase` are test cases to inherit from that offer a lot
  of predefined checks, including randomized data, to ensure compatibility.

* Packaging: Elasticsearch plugins are packaged as zip-files containing a plugin-descriptor.properties and 
  the jar files for the plugin. There is an installer command-line tool for this.

## References

* https://www.elastic.co/guide/en/elasticsearch/plugins/current/plugin-authors.html
* https://github.com/elastic/elasticsearch/tree/v2.3.2/plugins/analysis-phonetic
* https://www.elastic.co/blog/elasticsearch-docker-plugin-management