# LeafMusic API

Backend API cho hệ thống nhạc streaming (LeafMusic), được xây dựng bằng Spring Boot.  
Hệ thống cung cấp các chức năng quản lý bài hát, album, playlist, người dùng, xác thực JWT và upload file.

### Liên quan

- Cơ sở dữ liệu: Xem file trong folder `database/`
- Giao diện (FE-Mobile): https://github.com/delht/LeaFMusic_2
- Giao diện (FE-Web-Admin): https://github.com/delht/AdminLeaFMusic2
----------------------------------------------------------------

## 1. Công nghệ sử dụng

- Java 17+
- Spring Boot 3.4.3
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- MySQL
- Cloudinary
- MapStruct
- Lombok

----------------------------------------------------------------

## 2. Yêu cầu hệ thống

- Java 17 hoặc cao hơn
- Maven 3.8+
- MySQL
- Git

----------------------------------------------------------------

## 3. Cấu hình hệ thống

### 3.1 Database (MySQL)

- Tạo database:
```bash
CREATE DATABASE leafmusic;
```
- Import database schema từ folder `database/`:
```bash
mysql -u root -p leafmusic < database/leafmusic_2_F1.sql
```

----------------------------------------------------------------

### 3.2 Cấu hình application.properties

- File application.properties trong repository đã được loại bỏ toàn bộ thông tin nhạy cảm.  
  Người dùng cần tự cấu hình lại các giá trị sau cho môi trường local.
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/leafmusic  
spring.datasource.username=YOUR_DB_USERNAME  
spring.datasource.password=YOUR_DB_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

server.address=0.0.0.0
server.servlet.context-path=/api
```

----------------------------------------------------------------

### 3.3 JWT
```bash
jwt.secret=YOUR_JWT_SECRET  
jwt.expiration=86400
```

Lưu ý: `jwt.secret` nên là một chuỗi base64 hoặc hex string dài ít nhất 32 ký tự để bảo mật.

----------------------------------------------------------------

### 3.4 Cloudinary (Upload file)
```bash
cloudinary.cloud_name=YOUR_CLOUD_NAME  
cloudinary.api_key=YOUR_API_KEY  
cloudinary.api_secret=YOUR_API_SECRET  
```

- Đăng ký tài khoản tại: https://cloudinary.com
- Cấu hình CORS nếu cần cho file upload từ frontend

----------------------------------------------------------------

### 3.5 File upload cấu hình
```bash
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB

spring.jackson.serialization.write-dates-as-timestamps=false
```

Lưu ý: Giới hạn kích thước file là 100MB, có thể điều chỉnh theo nhu cầu.

----------------------------------------------------------------

### 3.6 Session cấu hình
```bash
server.servlet.session.timeout=15m
spring.session.store-type=none
```

----------------------------------------------------------------

## 4. Chạy ứng dụng

### 4.1 Cài đặt dependencies:
```bash
mvn clean install
```

### 4.2 Chạy ứng dụng:
```bash
mvn spring-boot:run
```

Ứng dụng sẽ chạy tại: `http://localhost:8080/api`

### 4.3 Chạy trong chế độ development (watch mode):
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

----------------------------------------------------------------

## 5. API Endpoints

Các endpoint chính:
- **Users**: `/api/user/*` - Quản lý người dùng
- **Songs**: `/api/song/*` - Quản lý bài hát
- **Albums**: `/api/album/*` - Quản lý album
- **Artists**: `/api/artist/*` - Quản lý nghệ sĩ
- **Genres**: `/api/genre/*` - Quản lý thể loại
- **Playlists**: `/api/favorite-playlist/*` - Quản lý playlist yêu thích
- **Search**: `/api/search/*` - Tìm kiếm
- **Upload**: `/api/upload-request/*` - Yêu cầu upload file

Xem chi tiết các endpoint trong các controller tương ứng.

----------------------------------------------------------------

## 6. Cấu trúc Project

```
src/main/java/de/lht/leafmusic3/
├── controller/          # REST Controllers
├── service/            # Business logic layer
├── repository/         # Data access layer
├── entity/            # JPA Entities
├── dto/               # Data Transfer Objects
├── mapper/            # MapStruct Mappers
├── config/            # Spring Configuration
├── cloud/             # Cloudinary Integration
└── Leafmusic3Application.java  # Main Entry Point
```

----------------------------------------------------------------

