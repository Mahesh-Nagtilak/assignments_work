package com.cybage.flight.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.flight.entities.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
	//Optional<User> findByEmail(String email);
	User findByEmail(String email);

	
	//User findByEmailAndIsEnabled(String email, boolean isEnabled);

	User findByEmailAndIsLocked(String email, boolean isLocked);

	@Transactional
	@Modifying
	@Query("UPDATE User a " + "SET a.isEnabled = TRUE WHERE a.email = ?1")
	int enableUser(String email);

	@Transactional
	@Modifying
	@Query(value = "delete c from  confirmation_token as c  where c.expires_at <now() and confirmed_at is null", nativeQuery = true)
	void deleteUnconfimedUsers();

	@Transactional
	@Modifying
	@Query(value = "delete from user as a where a.id not in (select c.user_id from confirmation_token as c)", nativeQuery = true)
	void deleteUnconfimedUsersfromAppUsers();

	User findByEmailAndPassword(String email, String password);

	@Transactional
	@Modifying
	@Query("UPDATE User a " + "SET a.isLocked = TRUE WHERE a.email = ?1")
	void lockUser(String email);
}
