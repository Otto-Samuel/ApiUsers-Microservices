package org.example.applicacaospring.repository;

import org.example.applicacaospring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    User findByCpf(String cpf);
    List<User> queryByNomeLike(String name);
}
