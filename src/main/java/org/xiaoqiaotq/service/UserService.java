package org.xiaoqiaotq.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public void save(User user){
		userRepository.save(user);
	}
	public boolean isExist(Long openid){
		return userRepository.findOne(openid)!=null;
	}
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	public User find(Long id){
		userRepository.findAll(new Specification<User>() {
			
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				return null;
			}
		});
		return userRepository.findOne(id);
	}
	public void remove(Long id){
		 userRepository.delete(id);;
	}
}