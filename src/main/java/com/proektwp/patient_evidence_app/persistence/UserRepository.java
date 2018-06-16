package com.proektwp.patient_evidence_app.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.proektwp.patient_evidence_app.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository<T extends User> extends CrudRepository<T, String> {

    @Query("select u from #{#entityName} as u where u.userId = :userId")
    public Optional<T> findUserByUserId(@Param("userId") String userId);


    @Transactional
    @Modifying
    @Query("update #{#entityName} user set user.password =:newPassword where user.userId =:userId")
    public void updatePassword(@Param("newPassword") String newPassword, @Param("userId") String userId);

}
