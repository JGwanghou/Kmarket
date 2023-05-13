package kr.co.kmarket.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "km_member")
public class UserEntity {
    @Id
    private String uid;
    private String pass;  // MemberService 에서 비밀번호 암호화 시킴
    private String pass2; // 회원가입 시 일치여부 확인 때문에 필요
    private String name;
    private Integer gender;
    private String hp;
    private String email;
    private int type;
    private int point;
    private int level;
    private String zip;
    private String addr1;
    private String addr2;
    private String regip;
    private String wdate;
    private String rdate;

    // 판매회원 추가필드
    private String company;
    private String ceo;
    private String bizRegNum;
    private String manager;
    private String managerHp;
}
