all:
	javac prir/api/Api.java prir/api/Plate.java
#	jar cvf api.jar prir/api/*.class
	javac prir/server/Server.java prir/server/ApiImplementation.java
#	jar cvf server.jar prir/server/*.class
	javac prir/client/Client.java
#	jar cvf client.jar prir/client/*.class
kill:
	killall -q java
run_server:
#	java -classpath /home/drobny/workspace_java/Test2/classes/api.jar /home/drobny/workspace_java/Test2/classes/server.jar -Djava.rmi.server.codebase=file:///home/drobny/workspace_java/Test2/classes/ prir.server.Server &
#	java -classpath . -Djava.rmi.server.codebase=file:///home/drobny/workspace_java/Test2/classes/ prir.server.Server &
	java -classpath . prir.server.Server &

run_client:
	java -classpath . prir.client.Client
