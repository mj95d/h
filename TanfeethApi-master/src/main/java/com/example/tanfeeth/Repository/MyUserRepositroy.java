package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepositroy extends JpaRepository<MyUser,Integer> {

    MyUser findMyUserByEmail(String email);
    MyUser findMyUsersById(Integer id);
}