all:
	javac prir/api/Api.java
#	jar cvf api.jar prir/api/*.class
	javac prir/server/Server.java prir/server/ApiImplementation.java
#	jar cvf server.jar prir/server/*.class
	javac prir/client/Client.java prir/client/Plate.java prir/client/PlotFrame.java org/fsf/HeatMap.java org/fsf/Gradient.java
kill:
	killall -q Server
run_server:
#	java -classpath /home/drobny/prir/project2/2DPlate2/src/api.jar /home/drobny/prir/project2/2DPlate2/src/server.jar -Djava.rmi.server.codebase=file:////home/drobny/prir/project2/2DPlate2/src/ prir.server.Server &
#	java -classpath /home/drobny/prir/project2/2DPlate2/src -Djava.rmi.server.codebase=file:////home/drobny/prir/project2/2DPlate2/src prir.server.Server &
#	java -classpath . prir.server.Server 1 &
#	java -classpath . prir.server.Server 2 &
	java -classpath . -Djava.security.policy=java.policy prir.server.Server 1 &
	java -classpath . -Djava.security.policy=java.policy prir.server.Server 2 &	
	
run_client:
	java -classpath . -Djava.security.policy=java.policy prir.client.Client
	
help:
	@echo 'Uzycie:'
	@echo 'all: kompilacja Api, Serwera i Klienta'
	@echo 'kill: zabicie dzialajacych serwerow'
	@echo 'run_server: start servera'
	@echo 'run_client: start klienta'
