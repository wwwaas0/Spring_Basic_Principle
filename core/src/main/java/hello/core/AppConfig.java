package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/*
    각 인터페이스에 어떤 구현체를 주입할지 정하는 클래스
 */
public class AppConfig {
    public MemberService memberService() {
        //MemberServiceImpl의 생성자를 이용해서 원하는 구현체 주입 -> 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    //AppConfig 내의 의존 관계 생각해보기 - new 중복 제거
    //원래 memberRepository를 바꾸면 memberService, orderService 메소드를 일일이 바꿔야했음
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
