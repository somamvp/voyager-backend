package somaMVP.domain;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class GisRepository {
    @PersistenceContext
    EntityManager em;
    public Long save(Gis gis) {
        em.persist(gis);
        return gis.getId();
    }
    public Gis findById(Long id) {
        return em.find(Gis.class, id);
    }

    public List<Gis> findAll() {
        return em.createQuery("select m from Gis m", Gis.class).getResultList();
    }
}
