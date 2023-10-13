package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //MemberServiceImpl이 MemberRepository(추상화), MemoryMemberRepository(구현체)에 둘 다 의존 -> DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
