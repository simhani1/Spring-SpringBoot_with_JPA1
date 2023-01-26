package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.reposiotry.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void SIGN_ON() throws Exception {
        // given
        Member member = new Member();
        member.setName("심종한");

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void EXIST_MEMBER_EXCEPTION() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("심종한");

        Member member2 = new Member();
        member2.setName("심종한");

        // when
        memberService.join(member1);

        // then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);  // 여기서 예외가 발생해야 한다.
        });
    }

}