FROM elasticsearch:2.3.1

COPY build/libs/esplugin-spike.jar /tmp
RUN /usr/share/elasticsearch/bin/plugin install --batch file:///tmp/esplugin-spike.jar