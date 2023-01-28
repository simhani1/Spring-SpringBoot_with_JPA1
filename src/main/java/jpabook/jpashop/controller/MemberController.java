package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // 오류가 발생했을때 오류가 result에 담긴 상태로 코드가 돌아간다.
    // Member도메인 객체를 바로 사용하지 않고 MemberForm객체를 사용하는 이유?
    // -> validation처리를 하기 위해 Member 도메인변수에 설정을 하다보면 코드가 더러워진다. 따라서 화면에서 입력받는 값을 위한 Form 데이터를 만들어서 도메인 객체로 주입시키는 것이 옳은 방법이다.
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if (result.hasErrors()) {  // 에러가 발생하더라고 memberForm에 들어있는 값은 유지되므로 정상적인 값들은 화면에 유지된다.
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
