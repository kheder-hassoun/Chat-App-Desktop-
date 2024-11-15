# RMI Chat Application

A Java-based chat application developed using Remote Method Invocation (RMI) to enable instant communication within chat rooms. The application allows users to interact in either private or group chat rooms, featuring functions like user registration, room management, and messaging.

**Repository**: [GitHub - Chat App Desktop](https://github.com/kheder-hassoun/Chat-App-Desktop-.git)

## Project Structure

The project is organized into two main applications, each with a cleanly separated package structure for easier understanding and maintenance

### Structure Overview

``` css
RMI_chatApp/
│
├── Client_Side/          # Client-side application with GUI and user interaction
│   ├── Data/             # Core data objects for temporary storage and session info
│   │   ├── Message.java
│   │   ├── User.java
│   │   ├── Room.java
│   │   └── Result.java
│   ├── me/chatapp/       # Client-specific classes
│   │   ├── ChatClientImpl.java
│   │   ├── ChatClientService.java
│   │   └── Forms/        # GUI components for chat, rooms, and user interactions
│   │       └── (Various form classes)
│   └── org/SharedInterface/ # Shared interfaces for RMI operations
│       ├── ChatService.java
│       └── ...
│
├── ChatServer/           # Server-side application handling sessions and user management
│   └── Singleton/        # Classes implementing Singleton pattern for efficient resource management
│       └── (Singleton-related classes)
└── README.md             # Project documentation
```

### Detailed Package Description

- **Data**: Contains essential classes to manage temporary or local session data, such as:
  - `Message`: Manages message details for chat interactions.
  - `User`: Stores user information.
  - `Room`: Defines chat rooms.
  - `Result`: Stores session or operation results.

- **me.chatapp**: The client package, containing:
  - `ChatClientImpl`: Implements the client interface, handling primary operations for RMI-based communication with the server.
  - `ChatClientService`: Defines services available to the client, invoked by the server.
  - `Forms`: GUI components, providing visual elements for users, such as chat interfaces, room views, etc.

- **org.SharedInterface**: Contains shared interfaces between the client and server, defining RMI methods and allowing interaction consistency.

- **Singleton**: Contains classes that follow the Singleton design pattern, ensuring efficient connection and resource management by limiting specific resources to a single instance.

---

### Key Features

- **User Management**: Facilitates registration and room membership for each user.
- **Room Management**: Enables room creation and deletion (only by the creator).
- **Messaging**: Supports private and group messaging within chat rooms.
- **Singleton Pattern**: Ensures efficient management of connections and resources through the Singleton design pattern.

---

## Getting Started

1. **Clone the Repository**: `git clone https://github.com/kheder-hassoun/Chat-App-Desktop-.git`
2. **Run the Server**: Compile and run the server-side application to start managing sessions and chat rooms.
3. **Run the Client**: Compile and run the client-side application to interact within chat rooms.

--- 
