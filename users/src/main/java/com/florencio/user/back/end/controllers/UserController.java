package com.florencio.user.back.end.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.florencio.user.back.end.dto.UserDTO;
import com.florencio.user.back.end.service.UserService;

@RestController
@RequestMapping(value= "/users")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public List<UserDTO> getUsers() {        
        List<UserDTO> usuarios = userService.getAll();        
        return usuarios;
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDTO findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	UserDTO newUser(@RequestBody UserDTO userDTO) {
		userDTO.setDataCadastro(new Date());
		return userService.save(userDTO);
	}

	@GetMapping("/cpf/{cpf}")
    UserDTO findByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf);
    }

	@RequestMapping ( value = "/{id}",method = RequestMethod.DELETE)
	UserDTO delete(@PathVariable Long userId){
		return userService.delete(userId);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<UserDTO> findName (@RequestParam(name = "nome", required = true) String nome){
		return userService.queryByName(nome);
	}

}
