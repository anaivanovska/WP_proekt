package com.proektwp.patient_evidence_app.persistence.hibernateSearch;

import java.util.List;

public interface HibernateSearchRepository {
    public <T> List<T> searchKeyword(Class<T> entityClass, String keyword, String... fields);
    public <T> List<T> searchPhrase(Class<T> entityClass, String text, String... fields);
}
