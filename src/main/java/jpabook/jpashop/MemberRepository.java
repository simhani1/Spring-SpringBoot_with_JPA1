package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    /**
     * command와 쿼리를 분리해라 라는 원칙
     * member를 저장하고 나면 가급적 side-effect를 일으킬 가능성이 있으므로 반환하지 않도록 설정한다.
     *
     * */
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
