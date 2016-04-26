FROM elasticsearch:2.3.1

COPY build/distributions/esplugin-spike.zip /tmp
RUN /usr/share/elasticsearch/bin/plugin install --batch file:///tmp/esplugin-spike.zip