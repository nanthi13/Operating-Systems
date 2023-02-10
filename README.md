# Operating-Systems
Tasks done in operating systems class
 Assignement 1 Single and multi threaded server
 
# How to run
To run the code, run the main method in the Main.java file

# Report
*Note: there seems to be a bug when running this on a Macbook pro where it created two threads for the same connection, 
I tried to fix the bug but couldn't find a solution to the problem. When running on a computer running windows, 
it seemed to work as intended by only creating one thread per connection*

As you can see from the logs bellow, the server received and accepted the connection when it received it. 
It then proceeded to run the task for each request while still accepting new requests and returned a response as soon as it was ready for each thread.
```
New client connected 192.168.0.112
New client connected 192.168.0.112
client_socket: 1735026314 - Request received: 1676038701660 - Server text: Multithreaded Server
client_socket: 340280132 - Request received: 1676038701660 - Server text: Multithreaded Server
New client connected 0:0:0:0:0:0:0:1
New client connected 0:0:0:0:0:0:0:1
client_socket: 934264557 - Request received: 1676038710607 - Server text: Multithreaded Server
client_socket: 188753112 - Request received: 1676038710610 - Server text: Multithreaded Server
client_socket: 340280132 - Request processed: 1676038711740 - Server text: Multithreaded Server
client_socket: 1735026314 - Request processed: 1676038711740 - Server text: Multithreaded Server
New client connected 192.168.0.112
client_socket: 1443196469 - Request received: 1676038711833 - Server text: Multithreaded Server
New client connected 192.168.0.112
client_socket: 1300122922 - Request received: 1676038711994 - Server text: Multithreaded Server
client_socket: 934264557 - Request processed: 1676038720613 - Server text: Multithreaded Server
client_socket: 188753112 - Request processed: 1676038720615 - Server text: Multithreaded Server
client_socket: 1443196469 - Request processed: 1676038721837 - Server text: Multithreaded Server
client_socket: 1300122922 - Request processed: 1676038721997 - Server text: Multithreaded Server
New client connected 192.168.0.112
client_socket: 1010513496 - Request received: 1676038722023 - Server text: Multithreaded Server
client_socket: 1010513496 - Request processed: 1676038732028 - Server text: Multithreaded Server
```


