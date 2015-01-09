package org.xiaoqiaotq.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.xiaoqiaotq.domain.User;

/**
 * @author xiaoqiaotq@gmail.com	
 * @date   2015年1月9日
 */
@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>,PagingAndSortingRepository<User, Long>{
	Page<User> findByUsername(String username,Pageable pageable);
}
