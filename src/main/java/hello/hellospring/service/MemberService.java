package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Service는 비즈니스를 처리하는게 Roll

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //D.I

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
    * 회원가입
    */

    public long join(Member member){
        //같은 이름이 있는 중복 회원 x

        /** Optional<Member> result = memberRepository.findByName(member.getName());    //ctrl+alt+v : 반환값 자동완성 단축키
        // if (result!= null) 대신 -> 널일 가능성이 있으면 optional로 감싸서 ifPresent 사용
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

         //Optional로 직접 반환하는건 권장하지 않는다.
         */

        //findbyName의 반환값은 Optional 이므로 바로 ifPresent 사용 가능하다.
        validateDuplicationMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        //alt+enter - extract method
        //findByName이라는 로직이 나온다. 이러한 경우에는 method를 뽑는게 유리.
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
