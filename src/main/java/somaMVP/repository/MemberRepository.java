package somaMVP.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import somaMVP.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class MemberRepository {
    @PersistenceContext
    EntityManager em;
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}
