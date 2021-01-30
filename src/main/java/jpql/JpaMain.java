package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);
            em.persist(member);

            Member result = em.createQuery("select m from Member m where m.userName = :userName", Member.class)
                    .setParameter("userName", "member1")
                    .getSingleResult();

            System.out.println("result = " + result.getUsername());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
