package kr.co.kmarket.service;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.repository.UserRepo;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.TermsVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
@Slf4j
public class MemberService {

    @Autowired
    private MemberDAO dao;
    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder PasswordEncoder;

    public int insertMember(MemberVO memberVO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        WebAuthenticationDetails details = (WebAuthenticationDetails)auth.getDetails();
        memberVO.setRegip(details.getRemoteAddress()); // regip 설정 첫번째 방법
        // request.getRemoteAddr(); regip 설정 두번째 방법

        memberVO.setPass(PasswordEncoder.encode(memberVO.getPass())); // 비밀번호 암호화

        if(memberVO.getType() == 1){ // 일반회원
            return dao.insertMember(memberVO);
        }

        return dao.insertMemberSeller(memberVO); // 판매회원 (member.xml에 level=5 설정해뒀기 떄문)
    }

    public TermsVO selectTerms(){
        return dao.selectTerms();
    }

    public Integer selectCheckUid(String uid){
        Integer result = repo.countUserEntityByUid(uid);
        return result;
    }
}
