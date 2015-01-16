package org.xiaoqiaotq.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xiaoqiaotq.domain.User;

/**
 * @author xiaoqiaotq@gmail.com	
 * @date   2015年1月9日
 */
@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>,PagingAndSortingRepository<User, Long>{
	Page<User> findByUsername(String username,Pageable pageable);
	
	@Query("select u from User u where u.username=:username")
	User isExist(@Param("username")String username);
	
	@Query("select u from User u where u.username=:username and u.pass=:pass")
	User isExist(@Param("username")String username,@Param("pass") String pass);
}
