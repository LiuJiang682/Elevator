This is the code test package for Isobar's elevator simulation.

This package contains two projects:
   1. The Elevator project -- the simulator for the elevator.
   2. The Elevator monitor project -- the web monitor component.
 
 
I have made the following assumptions:
    1. There is a live Tomcat instance.
    2. The live Tomcat instance can access to local disk.
    
To run it, here is the precedures:
    1. Download the zip.
    2. Extract it to a directory.
    3. cd path-to-directory.
    4. cd ElevatorMonitor/target
    5. Deploy the ElevatorMonitor-0.0.1-SNAPSHOT.war file in this directory to your live Tomcat instance.
    6. After you successfully deployed the war file, open the bowser with this URL: http://localhost:8080/ElevatorMonitor-0.0.1-SNAPSHOT/. And you should see the flashing "Isobar Security Monitor System" title and blank data fields for elevator 1 and elevator 2.
    7. cd ../../Elevator
    8. java -jar target/Elevator-0.0.1-SNAPSHOT-jar-with-dependencies.jar
    9. enter command like : 0,up to move the elevator 1 one level. 1, lock to lock elevator 2.
    10. After you entered each command, press the refresh button on your bowser to see the status have updated.
    11. After you happy with your test, do Ctrl-C to exit the Elevator simulator.
    12. Have fun!
    
The design of this monitor system:
1. Web component:
    It use Spring-boot framework since it is most easiest. The persistent component is used native Java file to reduce any dependencies and easy to maintain. It only has 2 layers -- the controller and the service. No persistent layer because the service layer is the persistent layer.
2. The simulator:
    It just java application with a CLI to accept user input. It updates the Web component via REST/JSON. The reason for choice it is due to its simplicity and well supported by Spring.       
    
