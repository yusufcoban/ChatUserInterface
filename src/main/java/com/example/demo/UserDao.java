package com.example.demo;

import org.springframework.data.repository.CrudRepository;



public interface UserDao extends CrudRepository<user,Integer> {

}
