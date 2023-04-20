package com.foodrecipe.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.foodrecipe.dto.RegisterRequest;
import com.foodrecipe.dto.AuthenticationResponse;
import com.foodrecipe.dto.AuthenticationRequest;
import com.foodrecipe.model.User;
import com.foodrecipe.model.Role;
import com.foodrecipe.repository.UserRepository;

public class AuthenticationService {
	private final UserRepository repo;
	
	public AuthenticationService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		repo = new UserRepository();
	}
	
	public int register(RegisterRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String encrypted = AES.encrypt(request.getPassword());
		User user = new User((long)0,request.getUsername(),encrypted,request.getFirstname(),request.getLastname(),request.getMidname(),Role.USER);
		return repo.create(user);
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
		User user = repo.findByUsername(request.getUsername());
		if(user != null && user.getPassword().equals(AES.encrypt(request.getPassword()))) {
			AuthenticationResponse response = new AuthenticationResponse(user.getUserId(),user.getFirstname());
			return response;
		}
		return null;
	}

}
