package com.bank.loan.repository;

import com.bank.loan.Loan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class LoanRepositoryImpl implements LoanRepository {

    @Inject
    EntityManager entityManager;

//    @Override
//    public List<Loan>

    @Override
    public void saveLoan(Loan loan){
        entityManager.persist(loan);
    }

    @Override
    public Optional<Loan> getById(Long id){
        return entityManager
                .createQuery("""
                        SELECT l
                        FROM Loan l
                        WHERE l.id = :id
                        AND l.isDeleted = 0
                        """,Loan.class)
                .setParameter("id",id)
                .getResultStream().findFirst();
    }

    @Override
    public Optional<Loan> getByProductId(Long productId){
        return entityManager
                .createQuery("""
                        SELECT l
                        FROM Loan l
                        WHERE l.financialProductId = :productId
                        AND l.isDeleted = 0
                        """,Loan.class)
                .setParameter("productId",productId)
                .getResultStream().findFirst();
    }
}
