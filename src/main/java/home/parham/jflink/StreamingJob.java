/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package home.parham.jflink;

import home.parham.jflink.domain.SoccerEvent;
import home.parham.jflink.marshal.SoccerEventSchema;
import home.parham.jflink.op.SoccerEventTypeCounter;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties kafkaProperties = new Properties();
		kafkaProperties.setProperty("bootstrap.servers", "localhost:9092");
		kafkaProperties.setProperty("group.id", "sevda");

		DataStream<Tuple2<String, Integer>> dataStream = env
				.addSource(new FlinkKafkaConsumer<>("events", new SimpleStringSchema(), kafkaProperties))
                .flatMap(new FlatMapFunction<String, SoccerEvent>() {
					@Override
					public void flatMap(String value, Collector<SoccerEvent> out) throws Exception {
						out.collect(new SoccerEventSchema().deserialize(value));
					}
				})
				.flatMap(new SoccerEventTypeCounter())
                .keyBy(0)
                .timeWindow(Time.seconds(60))
				.sum(1);


		dataStream.print();
		// uncomment the following line to have output on socket. please note that if you want to have output on socket
		// you must run the listener before submitting the task.
		// dataStream.writeToSocket("localhost", 9998, element -> element.toString().getBytes());

		env.execute("Soccer Events Processor!");
	}
}