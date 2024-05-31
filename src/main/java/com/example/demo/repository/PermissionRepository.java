package com.example.demo.repository;

import com.example.demo.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
    Optional<Permission> findByKey(String permissionKey);
    @Query("""
        select p 
        from Permission p
        where p.key in (:keys)
""")
    List<Permission> findAllByKey(@Param("keys") List<String> keys);
}
