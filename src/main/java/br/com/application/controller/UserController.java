package br.com.application.controller;

import br.com.application.dto.UserDetailsDTO;
import br.com.application.dto.UserEditInfoDTO;
import br.com.application.dto.UserEditPasswordDTO;
import br.com.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserEditInfoDTO userEditInfoDTO, @PathVariable Long id) {
        userService.update(userEditInfoDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDtoById(@PathVariable Long id){
        UserDetailsDTO userDetailsDTO = userService.findDtoById(id);
        return new ResponseEntity<>(userDetailsDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<UserDetailsDTO> userDetailsDTOList = userService.findAll();
        return new ResponseEntity<>(userDetailsDTOList, userDetailsDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @PostMapping("change-password/{id}")
    public ResponseEntity<?> changePassword(@RequestBody UserEditPasswordDTO userEditPasswordDTO, @PathVariable Long id) {
        userService.changePassword(userEditPasswordDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("reset-password-by-admin/{id}")
    public ResponseEntity<?> resetPasswordByAdmin(@PathVariable Long id) {
        String newPassword = userService.resetPasswordByAdmin(id);
        return new ResponseEntity<>(newPassword, HttpStatus.OK);
    }

}
