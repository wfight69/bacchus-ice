package com.davada.application.common;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CriteriaQueryHelper<T> {

    EntityManager entityManager;
    Class<T> domainClass;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery query;
    Root<T> root;
    List<Predicate> predicates = new ArrayList<>();

    public static enum Order {
        ASC,
        DESC
    }

    public CriteriaQueryHelper(EntityManager entityManager, Class<T> domainClass) {
        this.entityManager = entityManager;
        this.domainClass = domainClass;
        criteriaBuilder = entityManager.getCriteriaBuilder();
        query = criteriaBuilder.createQuery();
        root = query.from(domainClass);
    }

    public CriteriaQuery<T> getQuery() {
        return query.select(root).where(predicates.toArray(new Predicate[0]));
    }

    public CriteriaQuery<T> getQueryOrderBy(Expression<?> orderBy, Order order) {
        CriteriaQuery<T> queryOrderBy = getQuery();
        if (order == Order.ASC) {
            return queryOrderBy.orderBy(criteriaBuilder.asc(orderBy));
        } else {
            return queryOrderBy.orderBy(criteriaBuilder.desc(orderBy));
        }
    }

    public CriteriaQuery<Long> getQueryForCount() {
        return query.select(criteriaBuilder.count(root)).where(predicates.toArray(new Predicate[0]));
    }

    public List<T> list() {
        TypedQuery<T> q = this.entityManager.createQuery(this.getQuery());
        return q.getResultList();
    }

    public List<T> list(String key, Order order) {
        TypedQuery<T> q = this.entityManager.createQuery(this.getQueryOrderBy(root.get(key), order));
        return q.getResultList();
    }

    public List<T> list(int offset, int limit) {
        TypedQuery<T> q = this.entityManager.createQuery(this.getQuery());
        if (offset >= 0)
            q.setFirstResult(offset);
        if (limit > 0)
            q.setMaxResults(limit);
        return q.getResultList();
    }

    public List<T> list(String key, Order order, int offset, int limit) {
        TypedQuery<T> q = this.entityManager.createQuery(this.getQueryOrderBy(root.get(key), order));
        if (offset >= 0)
            q.setFirstResult(offset);
        if (limit > 0)
            q.setMaxResults(limit);
        return q.getResultList();
    }

    public List<T> list(String key1, String key2, Order order, int offset, int limit) {
        TypedQuery<T> q = this.entityManager.createQuery(this.getQueryOrderBy(root.get(key1).get(key2), order));
        if (offset >= 0)
            q.setFirstResult(offset);
        if (limit > 0)
            q.setMaxResults(limit);
        return q.getResultList();
    }

    public Long count() {
        TypedQuery<Long> q = this.entityManager.createQuery(this.getQueryForCount());
        return q.getSingleResult();
    }

    public <Y> Path<Y> getPath(String pathName) {
        if (isNullValue(pathName)) return null;
        String[] split = pathName.split(".");
        Path<Y> path = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            path = path.get(split[i]);
        }
        return path;
    }

    public void addEqual(String key, Object value) {
        if (isNullValue(value)) return;
        predicates.add(criteriaBuilder.equal(root.get(key), value));
    }

    public <Y extends Comparable<? super Y>> Predicate greaterThan(String key, Y value) {
        if (isNullValue(value)) return null;
        Predicate predicate = criteriaBuilder.greaterThan(root.get(key), value);
        predicates.add(predicate);
        return predicate;
    }

    public void addEqual(String key1, String key2, Object value) {
        if (isNullValue(value)) return;
        predicates.add(criteriaBuilder.equal(root.get(key1).get(key2), value));
    }

    public void addBetween(String key, String value1, String value2) {
        if (isNullValue(value1)) return;
        if (isNullValue(value2)) return;
        predicates.add(criteriaBuilder.between(root.get(key), value1, value2));
    }

    public void isNotNull(String key1, String key2) {
        predicates.add(criteriaBuilder.isNotNull(root.get(key1).get(key2)));
    }

    public void isNull(String key1, String key2) {
        predicates.add(criteriaBuilder.isNull(root.get(key1).get(key2)));
    }

    public void isTrue(String key1) {
        predicates.add(criteriaBuilder.isTrue(root.get(key1)));
    }

    public void addLike(String key, String value) {
        if (isNullValue(value)) return;
        predicates.add(criteriaBuilder.like(root.get(key), "%" + value + "%"));
    }

    public void addIn(String key, Object... values) {
        predicates.add(root.get(key).in(values));
    }

    private boolean isNullValue(Object value) {
        if (value instanceof String) {
            return "".equals(((String) value).trim());
        } else {
            return value == null;
        }
    }

}