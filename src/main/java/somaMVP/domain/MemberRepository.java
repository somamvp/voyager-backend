package somaMVP.domain;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import somaMVP.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
