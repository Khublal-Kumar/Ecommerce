package com.press.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.press.Ecommerce.modal.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

}
