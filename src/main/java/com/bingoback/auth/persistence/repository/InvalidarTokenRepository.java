package com.bingoback.auth.persistence.repository;

import com.bingoback.auth.persistence.entity.InvalidarToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidarTokenRepository extends JpaRepository<InvalidarToken,Long> {

    boolean existsByToken(String token);
}
