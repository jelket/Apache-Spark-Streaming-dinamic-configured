
DIR=dirname $0 CWD=cd $DIR; pwd

export SPARK_MAJOR_VERSION=2
spark-submit \
--verbose \
--master local[8] \
--class org.example.SparkMain \
--driver-cores 1 \
--driver-memory 1g \
--num-executors 4 \
--executor-cores 1 \
--executor-memory 1g \
--name streaming_app \
./spark_test-1.0-SNAPSHOT-jar-with-dependencies.jar
