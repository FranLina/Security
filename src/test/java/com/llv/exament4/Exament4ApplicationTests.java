package com.llv.exament4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.llv.exament4.models.Group;
import com.llv.exament4.models.Permission;
import com.llv.exament4.models.User;
import com.llv.exament4.repositories.PermissionRepository;
import com.llv.exament4.repositories.UserRepository;

@SpringBootTest
class Exament4ApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PermissionRepository permissionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void crearUsuarioTest() {
		User u1 = new User();
		u1.setId(1);
		u1.setName("flb");
		u1.setPassword("1234");
		Group g1 = new Group();
		g1.setId(1);
		u1.setGroup(g1);

		User u2 = new User();
		u2.setId(2);
		u2.setName("flb2");
		u2.setPassword("4321");
		Group g2 = new Group();
		g2.setId(2);
		u1.setGroup(g2);

		Permission p1 = new Permission();
		p1.setId(10);
		p1.setName("ADMIN");

		Permission p2 = new Permission();
		p2.setId(20);
		p2.setName("USER");

		List<Permission> permissions1 = new ArrayList<Permission>();

		List<Permission> permissions2 = new ArrayList<Permission>();

		permissions1.add(p1);
		permissions1.add(p2);

		permissions2.add(p2);

		u1.setPermissions(permissions1);

		u2.setPermissions(permissions2);

		permissionRepository.save(p1);
		permissionRepository.save(p2);

		userRepository.save(u1);
		User saveUser2 = userRepository.save(u2);

		assertTrue(u2.getPassword().equalsIgnoreCase(saveUser2.getPassword()));
	}
}
