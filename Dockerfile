FROM elasticsearch:2.3.1

COPY target/releases/esplugin-spike-0.1.zip /tmp
RUN /usr/share/elasticsearch/bin/plugin install --batch file:///tmp/esplugin-spike-0.1.zip