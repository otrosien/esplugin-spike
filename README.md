[![Build Status](https://travis-ci.org/otrosien/esplugin-spike.svg)](https://travis-ci.org/otrosien/esplugin-spike)

# Elasticsearch Plugin Spike

## Building

`./mvnw clean install`
`docker build -t epages/esplugin-spike .`

## Running

`docker run --name esplugin-spike epages/esplugin-spike`

## References

* https://www.elastic.co/guide/en/elasticsearch/plugins/current/plugin-authors.html
* https://github.com/elastic/elasticsearch/tree/v2.3.2/plugins/analysis-phonetic
