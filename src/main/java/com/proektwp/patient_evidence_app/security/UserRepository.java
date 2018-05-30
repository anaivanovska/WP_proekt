package com.proektwp.patient_evidence_app.security;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.proektwp.patient_evidence_app.model.User;
import java.util.Optional;

@Repository
public interface UserRepository<T extends User> extends CrudRepository<T, String> {

    @Query("select u from #{#entityName} as u where u.userId = ?1 ")
    public Optional<T> findUserByUserId(String userId);


}
