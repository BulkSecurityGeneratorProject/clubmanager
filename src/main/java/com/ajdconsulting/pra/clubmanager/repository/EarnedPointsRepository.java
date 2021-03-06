package com.ajdconsulting.pra.clubmanager.repository;

import com.ajdconsulting.pra.clubmanager.domain.EarnedPoints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the EarnedPoints entity.
 */
public interface EarnedPointsRepository extends JpaRepository<EarnedPoints,Long> {

    @Query("select e from EarnedPoints e where e.verified = true and e.paid = false and e.member.email = :userId and year(e.date) = :year order by e.date")
    public Page<EarnedPoints> findForUser(Pageable pageable, @Param("userId") String userId, @Param("year") Integer year);

    @Query("select e from EarnedPoints e where e.member.id = :memberId and e.paid = false and year(e.date) = :year order by e.date")
    public Page<EarnedPoints> findForMemberId(Pageable pageable, @Param("memberId") Long memberId, @Param("year") Integer year);

    @Query("select e from EarnedPoints e where e.verified = false and year(e.date) = year(current_date()) order by e.date, e.signup.job.sortOrder, e.member.lastName")
    public Page<EarnedPoints> findAllOrdered(Pageable pageable);

    @Query("select e from EarnedPoints e where e.verified = false and e.member.status <> 9 order by e.member.lastName")
    public Page<EarnedPoints> findUnverified(Pageable pageable);

    @Query("select e from EarnedPoints e where e.member.id = :id and year(e.date) = :year and e.verified = true and e.paid = false order by e.date")
    public List<EarnedPoints> findByMemberId(@Param("id") long id, @Param("year") int year);

    @Query("select e from EarnedPoints e where e.member.id = :id and e.verified = true and year(e.date) = year(current_date()) and e.date <= current_date() order by e.date")
    public List<EarnedPoints> findByMemberIdThisYear(@Param("id") long id);

    @Query("select e from EarnedPoints e where e.signup.id = :id")
    public List<EarnedPoints> findBySignupId(@Param("id") long id);
}
