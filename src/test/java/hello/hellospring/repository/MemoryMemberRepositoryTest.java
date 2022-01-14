package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
// 참고
//TDD : test 주도개발
//테스트를 먼저 만들고 개발을 하는 경우

//아래는 개발 후 테스트 진행

//test는 굳이 public 할 필요없음 , test를 class 단위로도 돌릴 수 있음
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test는 순서와 관계없이(의존관계 없이) 동작하도록 설계가 되어야한다.
    //@AfterEach : 하나의 메소드가 끝난 후 할 동작을 지정한다. -> 콜백 메소드
    //메모리 클리어하는 메소드를 작성하여 메소드가 돌때마다 메모리를 클리어 후 다른 메소드가 실행될 수 있게 해주어야 한다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();   //findbyId의 반환값이 optional. 값을 꺼낼떄 get() 사용함
                                                                    //바로 꺼내는건 좋은 방법은 아니지만 test이기 때문에 사용
        //검증
        //방법1 (기대값(member)과 실제 결과(result)가 같은지 확인) 결과가 true 일때는 에러 x
        //Assertions.assertEquals(member, result);

        //방법2 (assertJ) static.import 하면 Assertions. 없이 사용가능 / 요즘 많이 사용하는 스타일
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift+F6 -> rename 하면 객체이름 한번에 바뀜
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        //기대하는 성공값은 2개가 나와야 한다.
        assertThat(result.size()).isEqualTo(2);

    }

}
