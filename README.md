# Virtual Public Library
System Development Document (SDD)

#### Authors: Mariyam Sohail, Ramiyan Gangatharan
**Date:** October 14, 2025

---

## Overview

The **Virtual Public Library** is a Java-based system that simulates a simplified library management environment.  
It is designed to reinforce core **object-oriented programming principles** while incorporating additional system design features such as persistence, authentication, and a user-friendly interface.

This project demonstrates the ability to structure, implement, and maintain a scalable software solution that allows users to **borrow, return, and search for books**, while accurately maintaining transaction records.

---

## User Roles

### Librarian
- Manages book inventory and member records.
- Oversees borrowing activity and system state.

### Member
- Searches the catalog.
- Borrows and returns books.
- Views personal borrowing history.

---

## Primary Implementations
- Core Java Syntax
- Object-Oriented Programming (OOP)
- File Input/Output (I/O)
- Exception Handling
- Collections Framework
- Unit Testing

---

## Secondary Implementations
- Database Integration
- User Authentication (Login Functionality)
- Due Date Tracking System
- Graphical User Interface (GUI)
- Persistent Local Storage (Cached Storage for Faster Retrieval)

---

## Core Functionality
- **CRUD Operations for Books** – Add, update, or remove books (Librarian only).
- **Book Search Functionality** – Search by title, author, or ISBN.
- **Borrow Books** – Members can borrow available books.
- **Return Books** – Members can return previously borrowed books.
- **Display Functionality** – View individual book details or all books in the system.
- **Data Persistence** – Store and retrieve data via file I/O or serialized storage.

---

## Non-Functional Requirements
- **Usability:** Interfaces must be intuitive and user-friendly.
- **Maintainability:** Code should follow modular design and encapsulation principles.
- **Reliability:** Ensure consistent state management between library transactions.
- **Scalability:** Architecture should allow easy feature expansion and integration.

---

## Testing
Unit testing ensures system reliability through verification of:
- Core CRUD operations
- Book borrowing and return logic
- Exception handling during invalid transactions
- Data persistence validation

---

## Future Enhancements
- Online synchronization with cloud database
- Enhanced GUI using JavaFX or Swing
- Overdue fine calculation system
- Recommendation system based on borrowing history  