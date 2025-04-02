# BlogingApp – A Spring Boot Blogging Application  

A RESTful blogging platform built with **Spring Boot**, allowing users to create, read, update, and delete blog posts, manage categories, and interact with comments.  

## 🚀 Features  
- **User Authentication & Authorization** (JWT-based security)  
- **Blog Post Management** (Create, Read, Update, Delete)  
- **Category Management** (Organize posts by categories)  
- **Comment System** (Users can comment on posts)  
- **Pagination & Sorting** (For better post navigation)  
- **Swagger API Documentation** (Easy API testing)  
- **Exception Handling** (Custom error responses)  
- **Validation** (Request body validation)  

## 🛠 Technologies Used  
- **Backend**: Spring Boot, Spring Security, JWT  
- **Database**: MySQL (or H2 for testing)  
- **ORM**: Spring Data JPA (Hibernate)  
- **API Docs**: Swagger (OpenAPI)  
- **Build Tool**: Maven  
- **Other**: Lombok, ModelMapper  

## 📥 Prerequisites  
- **Java 17+** (Recommended: Amazon Corretto, OpenJDK)  
- **MySQL** (or any preferred database)  
- **Maven** (Dependency management)  
- **Postman/Insomnia** (API Testing)  

## 🛠 Installation & Setup  
1. **Clone the repository**  
   ```sh  
   git clone https://github.com/jivan899/BlogingApp.git  
   cd BlogingApp  
   ```  

2. **Configure Database**  
   - Update `application.properties` (or `application.yml`) with your MySQL credentials:  
     ```properties  
     spring.datasource.url=jdbc:mysql://localhost:3306/blog_db  
     spring.datasource.username=root  
     spring.datasource.password=yourpassword  
     spring.jpa.hibernate.ddl-auto=update  
     ```  

3. **Build & Run**  
   ```sh  
   mvn clean install  
   mvn spring-boot:run  
   ```  
   The app will start at: `http://localhost:8080`  

4. **Access Swagger UI**  
   Open `http://localhost:8080/swagger-ui.html` to explore APIs.  

## 📖 API Endpoints (Key Examples)  
| Endpoint                | Method | Description                     |  
|-------------------------|--------|---------------------------------|  
| `/api/auth/signup`      | POST   | Register a new user             |  
| `/api/auth/login`       | POST   | Authenticate & get JWT token    |  
| `/api/posts`            | GET    | Get all posts (paginated)       |  
| `/api/posts/{id}`       | GET    | Get a post by ID                |  
| `/api/categories`       | POST   | Create a new category           |  
| `/api/comments/{postId}`| POST   | Add a comment to a post         |  


