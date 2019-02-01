**Create new topic**

`kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic second_topic --create --partitions 6 --replication-factor 1`


**List all kafka topics**

`kafka-topics.sh --zookeeper 127.0.0.1:2181 --list`

**Describe about topic name - first_topic** 

`kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --describe`

**Delete Topic**

`kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --delete`


**Consumer reading from very beginning**

`kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic first_topic --from-beginning`

**Passing Key Value comma separated Producer**

`kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic --property parse.key=true --property key.separator=,`


**Passing Key Value comma separated consumer**

`kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --from-beginning --property print.key=true --property key.separator=,
`