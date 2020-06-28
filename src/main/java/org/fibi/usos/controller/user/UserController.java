package org.fibi.usos.controller.user;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.user.UserDto;
import org.fibi.usos.entity.response.standard.StandardMessageResponse;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @RequireRole({UserRole.DEAN,UserRole.RECTOR,UserRole.PORTER})
    @GetMapping("/all")
    public ResponseEntity<Collection<UserDto>> getAllUsers() {
        List<UserDto> res = new LinkedList<>();
        Iterable<UserModel> userModelList = userService.findAll();
        userModelList.forEach( u -> res.add(u.mapToDto()));
        return ResponseEntity.ok(res);
    }

    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @GetMapping("/{role}/all")
    public ResponseEntity<Collection<UserDto>> getAllUsersInRole(@PathVariable("role") UserRole role) {
        List<UserDto> res = new LinkedList<>();
        Optional<Collection<UserModel>> userModelList = userService.findAllByRole(role);
        userModelList.ifPresent( users -> users.forEach(u -> res.add(u.mapToDto())));
        return ResponseEntity.ok(res);
    }

    @CrossOrigin(methods = RequestMethod.DELETE)
    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardMessageResponse> removeUserById(@PathVariable("id") Long id) {
        StandardMessageResponse response = new StandardMessageResponse();
        if(userService.remove(id))
           response.setMessage("User deleted.");
        else
            response.setMessage("User not found.");
        return ResponseEntity.ok(response);
    }
}
