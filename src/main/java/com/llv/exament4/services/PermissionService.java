package com.llv.exament4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llv.exament4.models.Permission;
import com.llv.exament4.repositories.PermissionRepository;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission updatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }

    public Permission getPermission(Long permissionId) {
        return permissionRepository.findById(permissionId).orElse(null);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
