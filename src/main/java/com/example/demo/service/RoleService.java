package com.example.demo.service;


import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.exception.MyCustomException;
import com.example.demo.model.ReqRoleCreate;
import com.example.demo.model.ReqRoleUpdate;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepo;

    public void create(ReqRoleCreate roleCreate)  {
        Optional<Role> optional = roleRepo.findByName(roleCreate.getName());
        if (optional.isPresent()) {
            throw new MyCustomException(Status.EXIST.getMessage());
        }
        Role role = new Role();
        roleToSave(role, roleCreate.getName(), roleCreate.getDescription(),roleCreate.getPermissions());
    }

    public void update(ReqRoleUpdate roleUpdate)  {
        Optional<Role> optional = roleRepo.findById(roleUpdate.getId());
        if (optional.isEmpty()) {
            throw new MyCustomException(Status.NOT_FOUND.getMessage());
        }
        Role role = optional.get();
        roleToSave(role, roleUpdate.getName(), roleUpdate.getDescription(),roleUpdate.getPermissions());
    }

    public void delete(Long roleId)  {
        Optional<Role> optional = roleRepo.findById(roleId);
        if (optional.isEmpty()){
            throw new MyCustomException(Status.NOT_FOUND.getMessage());
        }
        roleRepo.delete(optional.get());
    }

    private void roleToSave(Role role, String name, String description, Set<Long> permissions) {
        role.setName(name);
        role.setPermissions(permissionRepo.findAllById(permissions));
        role.setDescription(description);
    }

    @Transactional
    public void createRoleIfNotFound(String roleName) {
        roleRepo.findByName(roleName).orElseGet(() -> {
            Role role = new Role();
            role.setName(roleName);
            if ("ADMIN".equals(roleName)) {
                role.setPermissions(permissionRepo.findAll());
            } else if ("CARRIER".equals(roleName)) {
                role.setPermissions(permissionRepo.findAllByKey(List.of(
                        "TRANSACTION_READ",
                        "TRANSACTION_CREATE",
                        "TRANSACTION_UPDATE",
                        "TRANSACTION_DELETE"
                )));
            }
            return roleRepo.save(role);
        });
    }

    @Transactional
    public void createPermissionIfNotFound(String permissionKey) {
        permissionRepo.findByKey(permissionKey).orElseGet(() -> {
            Permission permission = new Permission();
            permission.setKey(permissionKey);
            return permissionRepo.save(permission);
        });
    }
}
