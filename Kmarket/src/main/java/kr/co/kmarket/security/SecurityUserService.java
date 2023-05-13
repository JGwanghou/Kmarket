package kr.co.kmarket.security;

import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *	UserDetailsService : 유저의 정보를 가져오는 인터페이스
 */

@Service
public class SecurityUserService implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // (1) 이러한 uid가 들어왔는데 DB에 있니?

		// 스프링 시큐리티 인증 동작방식 아이디만 이용해서 사용자 정보를 로딩하고 나중에 패스워드를 검증하는 방식
		UserEntity user = repo.findById(username).get(); // uid 추출

		if(user == null) { // 오류 방지
			throw new UsernameNotFoundException(username);
		}

		// (2) 응 있어. 반환 타입이 UserDetails 얘를 구현한게 MyUserDetails 이구나 MyUserDetails 객체를 만들어서 보낼게.
		UserDetails myUser = MyUserDetails
								.builder()
								.user(user)
								.build();
		return myUser; // Security 기본 사용자 객체생성
	}

}
