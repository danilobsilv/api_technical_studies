package com.studies.api.features.user;

import com.studies.api.features.user.dto.CreateUserType;
import com.studies.api.features.user.dto.GetAllUsersNoSensitiveInfoDto;
import com.studies.api.features.user.dto.UpdateUserModType;
import com.studies.api.features.user.dto.UserResponseType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseType> createAdminUser(@RequestBody @Valid CreateUserType data, UriComponentsBuilder uriComponentsBuilder){
        User adminUser = userService.createUser(data);

        var uri = uriComponentsBuilder.path("/category/{category_id}").buildAndExpand(adminUser.getUserId()).toUri();

        return ResponseEntity.created(uri).body(new UserResponseType(adminUser));
    }

    @GetMapping("/")
    public ResponseEntity<Page<GetAllUsersNoSensitiveInfoDto>> listActiveUsers(@PageableDefault(size=20) Pageable pageable){
        return ResponseEntity.ok(userService.listActiveUsers(pageable));
    }

    @PutMapping("/userId/{userId}")
    public ResponseEntity.BodyBuilder updateAdminMod(@PathVariable String userId, @RequestBody @Valid UpdateUserModType data){
        userService.updateUser(userId, data);
        return ResponseEntity.ok();
    }

}
