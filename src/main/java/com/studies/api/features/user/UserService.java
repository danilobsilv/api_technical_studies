package com.studies.api.features.user;

import com.studies.api.features.user.dto.CreateUserType;
import com.studies.api.features.user.dto.GetAllUsersNoSensitiveInfoDto;
import com.studies.api.features.user.dto.UpdateUserModType;
import com.studies.api.features.user.validation.UserValidator;
import com.studies.api.features.user.validation.ValidatePassword;
import com.studies.api.infra.errorHandler.resourceConflictException.ResourceConflictException;
import com.studies.api.infra.errorHandler.resourceNotFoundException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<UserValidator> validators;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(CreateUserType data){
        validators.forEach(validator -> validator.validate(data));
        var user = new User(data);
        user.setUserPasswordHash(passwordEncoder.encode(data.userPassword()));
        return userRepository.save(user);
    }

    public Page<GetAllUsersNoSensitiveInfoDto> listActiveUsers(Pageable pageable){
        var page = userRepository.findAllByActiveTrue(pageable).map(GetAllUsersNoSensitiveInfoDto::new);
        return page;
    }

    @Transactional
    public void updateUser(String userId, UpdateUserModType data) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar o usuário requisitado."));

        if (data.userEmail() != null && !data.userEmail().isBlank()){
            if (userRepository.existsByUserEmailAndUserIdNot(data.userEmail(), userId)){
                throw new ResourceConflictException("Já existe outro usuário cadastrado com esse email.");
            }
        }

        if (data.userPassword() != null && !data.userPassword().isBlank()) {
            ValidatePassword.validatePassword(data.userPassword());
            String encodedPassword = passwordEncoder.encode(data.userPassword());
            data = new UpdateUserModType(data.userName(), data.userEmail(), encodedPassword);
        }

        user.updateUser(data);
    }


}
