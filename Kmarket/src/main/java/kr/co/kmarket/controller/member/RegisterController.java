package kr.co.kmarket.controller.member;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
    회원가입 폼 데이터 컨트롤러
 */

@Slf4j
@Controller
public class RegisterController {

    @Autowired
    private MemberService memberService;

    // 개인회원/판매회원 메서드 통합
    @PostMapping("register/member")
    public String insertMember(MemberVO memberVO){
        memberService.insertMember(memberVO); // Service에서 로직 처리

        return "redirect:/member/login";
    }

    @GetMapping("member/uidCheck")
    @ResponseBody
    public Map<String, Integer> checkUid(@RequestParam("uid") String uid){
        log.info(" uid : "+ uid);
        Integer result = memberService.selectCheckUid(uid);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        log.info("resultMap"+ resultMap);
        return resultMap;
    }


}
