package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    //test 실행 전 무조건 실행되는 것
    //text 하나당 한번 실행됨
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    @DisplayName("유저 정보를 저장한다")
    void join(){
        //given 이런 상황이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then 이렇게 된다.
        //Assertions -> org.assertj.core.api
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
