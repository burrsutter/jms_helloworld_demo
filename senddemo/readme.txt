
unzip apache-artemis-2.6.2-bin.zip
cd apache-artemis-2.6.2/bin
./artemis create /Users/burr/gdrive/code/activemq_artemis/abroker --user admin --password admin --allow-anonymous

"/Users/burr/gdrive/code/activemq_artemis/abroker/bin/artemis" run

Web console
http://localhost:8161/console
admin, admin

To run this example
mvn compile exec:java

The queued message should be seen as "1" in the console
https://screencast.com/t/zam8kbHJ