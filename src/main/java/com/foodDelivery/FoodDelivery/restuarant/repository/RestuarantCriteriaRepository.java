package com.foodDelivery.FoodDelivery.restuarant.repository;

import com.foodDelivery.FoodDelivery.restuarant.entity.Restuarant;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantPage;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RestuarantCriteriaRepository {
    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public RestuarantCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder= entityManager.getCriteriaBuilder();
    }

    public Page<Restuarant> findAllWithFilters(RestuarantPage restuarantPage, RestuarantSearchCriteria restuarantSearchCriteria){
        CriteriaQuery<Restuarant> criteriaQuery= criteriaBuilder.createQuery(Restuarant.class);
        Root<Restuarant> restuarantRoot= criteriaQuery.from(Restuarant.class);
        Predicate predicate= getPredicate(restuarantSearchCriteria,restuarantRoot);
        criteriaQuery.where(predicate);
        setOrder(restuarantPage,criteriaQuery,restuarantRoot);

        TypedQuery<Restuarant> typedQuery= entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(restuarantPage.getPageNumber() * restuarantPage.getPageSize());
        typedQuery.setMaxResults(restuarantPage.getPageSize());
        Pageable pageable= getPageable(restuarantPage);
        long restuarantCount= getRestuarantCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(),pageable,restuarantCount);
    }

    private long getRestuarantCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery= criteriaBuilder.createQuery(Long.class);
        Root<Restuarant> countRoot= countQuery.from(Restuarant.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private void setOrder(RestuarantPage restuarantPage, CriteriaQuery<Restuarant> criteriaQuery, Root<Restuarant> restuarantRoot) {
        if(restuarantPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(restuarantRoot.get(restuarantPage.getSortBy())));
        } else{
            criteriaQuery.orderBy(criteriaBuilder.desc(restuarantRoot.get(restuarantPage.getSortBy())));
        }
    }

    private Predicate getPredicate(RestuarantSearchCriteria restuarantSearchCriteria, Root<Restuarant> restuarantRoot) {
        List<Predicate> predicates= new ArrayList<>();
        if(Objects.nonNull(restuarantSearchCriteria.getName())){
            predicates.add(criteriaBuilder.like(restuarantRoot.get("name"), "%" + restuarantSearchCriteria.getName() + "%")
            );
        }
        if(Objects.nonNull(restuarantSearchCriteria.getCity())){
            predicates.add(criteriaBuilder.like(restuarantRoot.get("address").get("city"), "%" + restuarantSearchCriteria.getCity() + "%")
            );
        }
        if(Objects.nonNull(restuarantSearchCriteria.getState())){
            predicates.add(criteriaBuilder.like(restuarantRoot.get("address").get("state"), "%" + restuarantSearchCriteria.getState() + "%")
            );
        }
        if(Objects.nonNull(restuarantSearchCriteria.getCountry())){
            predicates.add(criteriaBuilder.like(restuarantRoot.get("address").get("country"), "%" + restuarantSearchCriteria.getCountry() + "%")
            );
        }

        if(restuarantSearchCriteria.getMinOrder() != 0){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(restuarantRoot.get("minOrder"),restuarantSearchCriteria.getMinOrder()));
        }

        if(restuarantSearchCriteria.getMinCost() != 0){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(restuarantRoot.get("minCost"),restuarantSearchCriteria.getMinCost()));
        }

        if(restuarantSearchCriteria.getDeliverytime() != 0){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(restuarantRoot.get("deliverytime"),restuarantSearchCriteria.getDeliverytime()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Pageable getPageable(RestuarantPage restuarantPage){
        Sort sort= Sort.by(restuarantPage.getSortDirection(),restuarantPage.getSortBy());
        return PageRequest.of(restuarantPage.getPageNumber(),restuarantPage.getPageSize(),sort);
    }


}
