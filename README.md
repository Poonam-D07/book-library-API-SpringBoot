# 📚 Book Library API

A simple Spring Boot REST API for managing a book library — built as a practice project to strengthen core Spring Boot, JPA, and REST API concepts.

## 🛠️ Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Maven

## 🚀 Features
- Add a new book
- Get all books
- Get a single book by ID
- Update book details
- Hard delete a book (permanent)
- Soft delete a book (flag-based, recoverable)

## 📌 API Endpoints

| Method | Endpoint | Description |
|--------|----------|--------------|
| POST | `/api/books/add` | Add a new book |
| GET | `/api/books/all` | Get all books |
| GET | `/api/books/{bookId}` | Get a single book by ID |
| PUT | `/api/books/update/{bookId}` | Update a book |
| DELETE | `/api/books/hardDelete/{bookId}` | Permanently delete a book |
| PUT | `/api/books/softDelete/{bookId}` | Soft delete a book |

## ▶️ How to Run

1. Clone the repository
```bash
   git clone https://github.com/your-username/book-library.git
```
2. Open the project in IntelliJ IDEA
3. Run `BookLibraryApplication.java`
4. The app will start on `http://localhost:8080`

## 🗄️ H2 Console
Access the in-memory database console at:
http://localhost:8080/h2-console

JDBC URL: `jdbc:h2:mem:bookdb`


## 📖 What I Learned
This project helped me practice:
- Layered architecture (Controller → Service → Repository → Entity)
- DTO pattern for request/response separation
- Hard delete vs Soft delete implementation
- Path variables and request parameters