package org.huejacksonfoundation.hopejustfound.repository;

import org.huejacksonfoundation.hopejustfound.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
