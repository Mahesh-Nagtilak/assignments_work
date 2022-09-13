package com.cybage.flight.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.flight.entities.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findById(int id);

	@Transactional
	@Modifying
	@Query("UPDATE User a " + "SET a.isLocked = False WHERE a.email = ?1")
	void unlockUser(String email);

	User findByEmail(String email);
}

