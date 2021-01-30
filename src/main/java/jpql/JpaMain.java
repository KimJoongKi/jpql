package jpql;

import javax.persistence.*;
import java.util.List;

//페이징
//for (int i = 0; i < 100; i++) {
//        Member member = new Member();
//        member.setUserName("member" + i);
//        member.setAge(i);
//        em.persist(member);
//        }
//
//        em.flush();
//        em.clear();
//
//        List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
//        .setFirstResult(0)
//        .setMaxResults(10)
//        .getResultList();
//
//        System.out.println("resultList.size() = " + resultList.size());
//        for (Member member1 : resultList) {
//        System.out.println("member1 = " + member1);
//        }


//조인
//        Team team = new Team();
//        team.setName("teamA");
//        em.persist(team);
//
//        Member member = new Member();
//        member.setUsername("teamA");
//        member.setAge(10);
//
//        member.setTeam(team);
//
//        em.persist(member);
//
//        em.flush();
//        em.clear();
//
//        String query = "select m from Member m left join Team t on m.username = t.name";
//        List<Member> resultList = em.createQuery(query, Member.class)
//        .getResultList();
//
//        System.out.println("resultList = " + resultList.size());

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.setMemberType(MemberType.ADMIN);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m.username, 'HELLO', TRUE from Member m " +
                    "where m.memberType = :userType";
            List<Object[]> result = em.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();

            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[0] = " + objects[1]);
                System.out.println("objects[0] = " + objects[2]);
            }

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
