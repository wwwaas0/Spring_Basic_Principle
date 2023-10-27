package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        /*
            원래는 AppConfig 객체를 직접 찾아왔지만, 이제 스프링 컨테이너를 통해서 찾아오게 만듬

            아래는 AppConfig 직접 찾아오는 코드
            AppConfig appConfig = new AppConfig();
            MemberService memberService = appConfig.memberService();
         */

        /*
            아래는 스프링 컨테이너를 통해서 필요한 객체를 가져오는 코드.
            ApplicationContext가 스프링 컨테이너라고 보면 됨. 얘가 @Bean 붙은 메소드들을 관리
            AnnotationConfigApplicationContext는 어노테이션 기반으로 config하고 있을때 사용?

            applicationContext.getBean("@Bean에 등록된 메소드 이름", 반환될 인터페이스 객체?)
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
