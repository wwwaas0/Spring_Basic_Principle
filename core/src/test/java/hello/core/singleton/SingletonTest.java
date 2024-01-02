package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //아래의 두 참조값이 같게 나온다. 싱글톤 패턴 적용했기 때문
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        //isEqual은 문자가 같은 거 비교
        //isSameAs는 객체가 같은지 비교
    }

    /*
    싱글톤 패턴 문제점
        특정 같은 코드를 무조건 넣어야함.
        instance를 가져올 때 MemberServiceImpl(구현체).getInstance()와 같은 방식으로 가져와야함
            -> 클라이언트가 구체 클래스에 의존(DIP 위반) + OCP 위반할 가능성 높다.

        유연성 떨어짐 등등 안좋은점

        -> 스프링 컨테이너(싱글톤 컨테이너)는 위 문제를 해결하면서 객체 인스턴스를 싱글톤으로 관리
     */
}
