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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    각 인터페이스에 어떤 구현체를 주입할지 정하는 클래스
 */

/*
    스프링 컨테이너는 @Configuration이 붙은 클래스를 설정 정보로 사용
    여기서 @Bean이 붙은 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록
    이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라고 한다.
    또한, 스프링 빈은 @Bean이 붙은 메서드 명을 스프링 빈의 이름으로 사용. 물론 @Beam(name = "바꿀 이름")으로 이름 바꿀 수 있음
    ex) 'memberService', 'orderService'
 */
@Configuration //이 클래스는 DI 컨테이너라는 뜻
public class AppConfig {

    //@Bean 어노테이션을 붙여주면 해당 메소드들이 스프링 컨테이너에 등록됨
    @Bean
    public MemberService memberService() {
        //MemberServiceImpl의 생성자를 이용해서 원하는 구현체 주입 -> 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    //AppConfig 내의 의존 관계 생각해보기 - new 중복 제거
    //원래 memberRepository를 바꾸면 memberService, orderService 메소드를 일일이 바꿔야했음
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
