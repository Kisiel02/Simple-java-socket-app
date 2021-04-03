# Simple-java-socket-app
Simple java app based on web sockets and threads
App made for laboratory at 4th semester

### How it works
First you need to launch server, then client.
The server works on web sockets on java. It accepts connections from client and handles them in new threads.
Clients send photos to the server from folder _source_, then the serves saves them in folder _saved_
In order to simulate multiple clients simultaneously, connections are opened by many threads at the same moment.  
They send different photos. Server uses buffered streams to save files.

