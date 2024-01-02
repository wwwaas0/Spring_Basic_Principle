package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    @DisplayName("MemberServiceImpl의 멤버 변수 MemberRepository와 OrderServiceImpl의 멤버 변수 MemberRepository는 같은 인스턴스이다")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    @DisplayName("AppConfig 파일 자세하게 보기")
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean =ac.getBean(AppConfig.class);

        System.out.println("bean = "+bean.getClass());
        //출력하면 다음처럼 나온다
        //bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$acbb0914
        //$$EnhancerBySpringCGLIB$$acbb0914 부분이 이상하다! 이게 뭘까?

        /*
        순수한 클래스라면 bean = class hello.core.AppConfig 이렇게만 출력된다
        뒤에 ~~CGLIB가 붙은 것은 내가 만든 클래스가 아니라, 스프링이 빈을 등록할 때 뭔가 조작했다는 뜻
            스프링이 CGLIB라는 바이트코드를 조작하는 라이브러리를 사용
            -> AppConfig 클래스를 상속받는 임의의 클래스 만들기
            -> 이 임의의 클래스(AppConfig@CGLIB)에 AppConfig 메소드를 오버라이딩
             -> 이걸 스프링 빈으로 등록함
         */
    }
}
