# Chatroom

A Java implementation of a chat room where multiple clients can connect into one server and chat.

## Features
- Server <-> Client communication via Java sockets
- System messages on specific events like user connection/disconnection
- A dynamic command system which allows users to do simple tasks like changing their name or exiting the client

## Demo Instructions
All sockets are hardcoded to port 5000. Please make sure to have the port available before starting.

1. Start the server using the `..chatroom.core.Server` class to create the room
2. Start any number of clients using the `..chatroom.core.Client` class
3. (For each client) Before a client connects to the server, they are prompted for a name to go by and identify as

### Client Activity
Clients can complete one of three options:
- Run `/help` to see the available commands  (All commands are all run locally on that client's connection with exceptions. No other client will know how many times you run `/ping`!)
- Run any command via a slash character at the start of their message (ie: `/help` or `/ping`)
- Send messages into the chatroom for everyone else to see.