package com.proektwp.patient_evidence_app.persistence.hibernateSearch;


import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class HibernateSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public <T>  List<T> searchKeyword(Class<T> entityClass, String keyword, String... fields) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(entityClass).get();

        Query query = queryBuilder.keyword()
                                   .wildcard()
                                   .onFields(fields)
                                   .matching(keyword + "*")
                                   .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query);

        return jpaQuery.getResultList();

    }
}
