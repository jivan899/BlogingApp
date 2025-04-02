Here’s an updated **`README.md`** with explicit versions for key dependencies (based on your `pom.xml`):  

---  

# BlogingApp – A Spring Boot Blogging Application  

A RESTful blogging platform built with **Spring Boot**, allowing users to create, read, update, and delete blog posts, manage categories, and interact with comments.  

## 🚀 Features  
- **User Authentication & Authorization** (JWT-based security)  
- **Blog Post Management** (CRUD operations)  
- **Category & Comment System**  
- **Pagination & Sorting**  
- **Swagger API Documentation**  
- **Validation & Exception Handling**  

## 🛠 Technologies & Versions  
| Technology       | Version           |  
|------------------|-------------------|  
| **Spring Boot**  | 3.1.5            |  
| **Java**         | 17               |  
| **Spring Security** | 6.1.5         |  
| **JWT**          | 0.11.5           |  
| **MySQL Driver** | 8.0.33           |  
| **Spring Data JPA** | 3.1.5       |  
| **Lombok**       | 1.18.28          |  
| **ModelMapper**  | 3.1.1            |  
| **Swagger**      | SpringDoc OpenAPI 2.2.0 |  
| **Maven**        | (Wrapper) 3.9.5  |  

*(Versions are extracted from your `pom.xml`. Adjust if needed.)*  

## 📥 Prerequisites  
- **Java 17** (OpenJDK/Amazon Corretto)  
- **MySQL 8.0+** (or H2 for testing)  
- **Maven 3.9+**  

## 🛠 Installation & Setup  
1. **Clone and configure**:  
   ```sh  
   git clone https://github.com/jivan899/BlogingApp.git  
   cd BlogingApp  
   ```  
   Edit `src/main/resources/application.properties`:  
   ```properties  
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_db  
   spring.datasource.username=root  
   spring.datasource.password=yourpassword  
   ```  

2. **Build and run**:  
   ```sh  
   mvn clean install  
   mvn spring-boot:run  
   ```  
   App runs at: `http://localhost:8080`  

3. **Access Swagger**:  
   Visit `http://localhost:8080/swagger-ui.html` for API docs.  

## 📖 API Endpoints  
| Endpoint                | Method | Description                     |  
|-------------------------|--------|---------------------------------|  
| `/api/auth/signup`      | POST   | Register new user               |  
| `/api/posts?page=0&size=10` | GET | Paginated posts             |  

*(Full endpoints in Swagger.)*  

## 🐳 Docker Support (Optional)  
To containerize:  
```sh  
docker build -t blogapp .  
docker run -p 8080:8080 blogapp  
```  

## 🤝 Contributing  
1. Fork → Create branch → Commit → Push → PR.  
2. Follow [GitHub Flow](https://guides.github.com/introduction/flow/).  

## 📜 License  
MIT. See [LICENSE](LICENSE).  

---  

### Need Adjustments?  
Let me know if you’d like to:  
- Add **testing frameworks** (JUnit 5, Mockito versions).  
- Specify **cloud deployment** (AWS/Heroku).  
- Include **screenshots** or GIFs.  

Happy coding! 🎉
