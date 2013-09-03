TCPMsgServer


Build Script usage:
1. compile all Java sources using Ant build.xml target="compile"
2. build jar file using Ant build.xml target="dist", eg <project-dir>ant dist
3a. creates build directory containing (.class files)
3b. creates dist/lib directory containing runnable jar file, setting TCPMsgServerThread as main runnable class

Run Server script (Windows only):
Start server (runnable; jar above) from dos batch script: <project-dir>run_server.bat


Implementation:

Run server class:
 TCPMsgServer

server listens on localhost port 4848

Run Clients:
    ClientsMain (runs 20 client connections sending messages)

Message Format:
1. Message elements are built by clients as strings and transmitted as bytes, to support language/data-type neutral clients, vs. serialization etc
2. elements are separated by double hash mark "##" delimiter and message is terminated by new-line characters \n
2a. Example client message data string : 110##1111##51111111##it was a dark and stormy night man 234567@@@@ \n
3. Data is strongly typed by server which creates a ValidMessage object from incoming message.
3a. Java types on server: version is typed byte, Message Type is typed short (2 bytes), User Id is typed int (4 bytes), and Payload is typed String.
3a. If there is bad data (data is too large for element type) in a message an exception is handled, the server logs error skips the bad message.
4. Unclear whether negative values in elements are allowed ????, but data types allow within their type, eg byte min value is -128
5. Server logs messages with a message number before each message, indicating the current total number of messages logged by server since startup.

Client class:
1. TCPMsgClient
2. ClientsMain (starts 20 clients)


Message Classes:
1. TestMessage  - message object built by client, containing String elements; string elements are concatenated with delimiter for parsing on server
2. ValidMessage  - message object built by server parsed from incoming string message, where element data types are enforced


Testing:
A. TCPClientServerTest class starts server, waits two seconds, and starts clients.
1. Client classes send some bad data, such as version-number over 1 byte. String input type allows ad-hoc error testing.
2. Performance Tested twenty clients each sending a message each 200ms (1/5 second) intervals,  server processes 100 messages/sec for 30,000 messages.
3. clients send a version id over 1 byte, which causes that message to be rejected, server continues processing next message.
4. Server and client threads will run until they are manually terminated.


Known SocketException issues, caused by a single client sending messages to frequently (approx less than 200 ms intervals per client):
occasional client fails: java.net.BindException: Address already in use: connect







