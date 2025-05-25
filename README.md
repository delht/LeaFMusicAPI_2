### 1. Cài đặt dependencies
```bash
mvn clean install
```

### 2. Cấu hình cơ sở dữ liệu
Chỉnh sửa file `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/leafmusic
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
```

### 3. Cấu hình bảo mật, JWT, context-path (nếu cần)
```properties
spring.security.user.name=admin
spring.security.user.password=admin123
jwt.secret=your_jwt_secret
jwt.expiration=86400
server.servlet.context-path=/api
```

### 4. Tạo file cấu hình Cloudinary
File `src/main/resources/env.properties`
```properties
cloudinary.cloud_name=your_cloud_name
cloudinary.api_key=your_api_key
cloudinary.api_secret=your_api_secret
```

## Chạy ứng dụng

### Chạy bằng Maven:
```bash
mvn spring-boot:run
```