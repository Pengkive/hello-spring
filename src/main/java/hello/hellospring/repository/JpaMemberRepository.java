package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    //JPA 는 EntityManager 로 동작한다. 스프링에서 JPA를 사용하려면 EntityManager를 주입받아야한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //persist 영속하다, 영구 저장하다
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //em.find (entity class, primary key)
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
    //pk가 아닌 경우, JPQL(객체지향 쿼리 언어)사용해야함 (엔티티 기반의 sql)
        List<Member> result = em.createQuery("select m from Member m where m.name =:name",
                Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //alt+enter : introduce local variable 단축키
        //          : inline variable (result 선택한 상태에서 alt+enter)
        //  List<Member> result = em.createQuery("select m from Member m", Member.class)
        //        .getResultList();
        //  return result;

        // inline 적용 후
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
