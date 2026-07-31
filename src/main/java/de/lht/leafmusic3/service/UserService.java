    package de.lht.leafmusic3.service;


    import de.lht.leafmusic3.config.JwtUtil;
    import de.lht.leafmusic3.dto.user.ChangePassword;
    import de.lht.leafmusic3.dto.user.LoginResponse;
    import de.lht.leafmusic3.dto.user.UserDTO;
    import de.lht.leafmusic3.entity.Role;
    import de.lht.leafmusic3.entity.UserAccount;
    import de.lht.leafmusic3.exception.AppException;
    import de.lht.leafmusic3.mapper.UserMapper;
    import de.lht.leafmusic3.repository.UserRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import java.util.List;

    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.apache.commons.validator.routines.EmailValidator;


    @Service
    @RequiredArgsConstructor
    public class UserService {
        private final UserRepository userRepository;
        private final UserMapper userMapper;
        private final JwtUtil jwtUtil;

        private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //=================================================================

        public List<UserDTO> getAllUsers() {
            List<UserAccount> users = userRepository.findAll();
            return userMapper.toDTOs(users);
        }

        public LoginResponse registerUser(String email, String password) {

            String hashedPassword = encoder.encode(password);

            if (!EmailValidator.getInstance().isValid(email)) { throw new AppException(HttpStatus.BAD_REQUEST, "Email không hợp lệ"); }
            if (userRepository.findByEmail(email).isPresent()) { throw new AppException(HttpStatus.CONFLICT, "Email đã tồn tại"); }

            //Lấy phần trước @ làm username
            String username = email.split("@")[0];

            UserAccount account = new UserAccount();
            account.setUserName(username);
            account.setPassWord(hashedPassword);
            account.setEmail(email);
            account.setRole(Role.USER);
            userRepository.save(account);

            String accessToken = jwtUtil.generateAccessToken(account.getUserName(), account.getIdUser(), account.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(account.getUserName(), account.getIdUser(), account.getEmail());

            return new LoginResponse(
                    accessToken,
                    refreshToken,
                    account.getUserName(),
                    account.getIdUser(),
                    account.getEmail(),
                    String.valueOf(account.getRole()),
                    account.getIdArtist(),
                    account.getUpload()
            );

        }

        public LoginResponse login(String email, String password) {
            UserAccount user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(HttpStatus.UNAUTHORIZED, "Email hoặc mật khẩu không đúng"));
            if (!encoder.matches(password, user.getPassWord())) { throw new AppException(HttpStatus.UNAUTHORIZED, "Email hoặc mật khẩu không đúng"); }
            String accessToken = jwtUtil.generateAccessToken(user.getUserName(), user.getIdUser(), user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUserName(), user.getIdUser(), user.getEmail());
            return new LoginResponse(
                    accessToken,
                    refreshToken,
                    user.getUserName(),
                    user.getIdUser(),
                    user.getEmail(),
                    String.valueOf(user.getRole()),
                    user.getIdArtist(),
                    user.getUpload()
            );
        }

        public void changePassword(ChangePassword changePassword) {
            UserAccount user = userRepository.findById(changePassword.getId())
                    .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng"));
            if (!encoder.matches(changePassword.getOldPassword(), user.getPassWord())) {
                throw new AppException(HttpStatus.BAD_REQUEST, "Mật khẩu cũ không đúng");
            }
            String newEncoded = encoder.encode(changePassword.getNewPassword());
            user.setPassWord(newEncoded);
            userRepository.save(user);
        }

    }