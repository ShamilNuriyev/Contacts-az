package jwtdemo.repository;



import jwtdemo.models.RefreshToken;
import jwtdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Override
    Optional<RefreshToken> findById(Long id);


    Optional<RefreshToken> findByToken(String token);

}