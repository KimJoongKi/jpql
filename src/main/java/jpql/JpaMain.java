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

//타입 표현
//        String query = "select m.username, 'HELLO', TRUE from Member m " +
//        "where m.memberType = :userType";
//        List<Object[]> result = em.createQuery(query)
//        .setParameter("userType", MemberType.ADMIN)
//        .getResultList();
//
//        for (Object[] objects : result) {
//        System.out.println("objects[0] = " + objects[0]);
//        System.out.println("objects[0] = " + objects[1]);
//        System.out.println("objects[0] = " + objects[2]);
//        }

//조건식
//        String query =
//        "select " +
//        "case when m.age <= 10 then '학생요금'" +
//        "     when m.age >= 60 then '경로요금'" +
//        " else '일반요금' end " +
//        "  from Member m";
//        List<String> query1 = em.createQuery(query, String.class).getResultList();
//String query = "select coalesce(m.username, '이름없는 회원') from Member m";
//String query = "select nullif(m.username, '관리자') as username " +
//        "from Member m";
//        List<String> query1 = em.createQuery(query, String.class).getResultList();
//
//        for (String s : query1) {
//        System.out.println("s = " + s);
//        }


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            Member member = new Member();
            member.setUsername("관리자1");
            em.persist(member);

            Member member1 = new Member();
            member1.setUsername("관리자2");
            em.persist(member1);


            em.flush();
            em.clear();

            String query = "select function('group_concat', m.username) From Member m";
            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
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
