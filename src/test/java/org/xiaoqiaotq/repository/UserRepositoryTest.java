package org.xiaoqiaotq.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.xiaoqiaotq.domain.User;

/**
 * @author xiaoqiaotq@gmail.com	
 * @date   2015年1月9日
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testSave() throws Exception {
		User u=new User();
		u.setAge(3);
		u.setDate(new Date());
		u.setUsername("lisi");
		userRepository.save(u);
		System.err.println(u);
	}
	@Test
	public void findTasksByUserId() throws Exception {
		Page<User> tasks = userRepository.findByUsername("d", new PageRequest(0, 100, Direction.ASC, "id"));
		System.err.println(tasks.getContent());
		System.err.println(tasks.getContent().get(0).getId());
	}
	
	@Test
	public void findCondition() throws Exception {
		List<User> tasks = userRepository.findAll(new Specification<User>() {
			
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate p=cb.conjunction();
				List<Expression<Boolean>> expressions=p.getExpressions();
//				Predicate predicate=cb.and(
//						cb.equal(root.get("username"), "%zhsan%"),
//						cb.gt(root.<Long>get("age"), 3));
//				Predicate p2=cb.equal(root.get("username"), "%zhsan%");
				Date date=null;
				try {
					 date=new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-08");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Predicate p1=cb.equal(root.<Date>get("date"), cb.currentTimestamp());
				expressions.add(p1);
//				expressions.add(cb.gt(root.<e>get("age"), 3));
				return p;
			}
		});
		System.err.println(tasks);
	}
}
