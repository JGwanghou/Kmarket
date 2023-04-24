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

        memberVO.setPass(PasswordEncoder.encode(memberVO.getPass1()));
        memberVO.setRegip(details.getRemoteAddress());

        if(memberVO.getType() == 1){
            return dao.insertMember(memberVO);
        }
        return dao.insertMemberSeller(memberVO);
    }

    public TermsVO selectTerms(){
        return dao.selectTerms();
    }

    public Integer selectCheckUid(String uid){
        Integer result = repo.countUserEntityByUid(uid);
        return result;
    }
}
