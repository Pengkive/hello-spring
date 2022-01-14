package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface에서 interface를 받기 위해서는 implements 대신 extends 사용
public interface SpringDataJpaRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL : select m from Member m where m.name=?
    @Override
    Optional<Member> findByName(String name);
}

/**
    스프링 데이터 JPA 제공 기능
    - findByName(), findByEmail() 처럼 메서드 이름만으로 조회기능 제공
    - 페이징 기능 자동 제공
 */