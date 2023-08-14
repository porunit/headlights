package com.headlightbackend.util;

import com.headlightbackend.data.domain.Headlight;
import com.headlightbackend.data.dto.SearchFiltersDTO;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;
@Log4j2
public class HeadlightSpecification {
    public static Specification<Headlight> matchesSearchFilters(SearchFiltersDTO filters) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();
            if (filters.getCommonType() != null && !filters.getCommonType().isEmpty()) {
                Predicate commonTypePredicate = cb.disjunction();
                for (String commonType : filters.getCommonType()) {
                    commonTypePredicate = cb.or(commonTypePredicate, cb.equal(root.get("commonType").get("name"), commonType));
                }
                p = cb.and(p, commonTypePredicate);
            }

            if (filters.getBrand() != null && !filters.getBrand().isEmpty()) {
                Predicate brandPredicate = cb.disjunction();
                for (String brand : filters.getBrand()) {
                    brandPredicate = cb.or(brandPredicate, cb.equal(root.get("carModelGeneration").get("brandModel").get("brand").get("id"), brand));
                }
                p = cb.and(p, brandPredicate);
            }

            if (filters.getManufacturer() != null && !filters.getManufacturer().isEmpty()) {
                Predicate manufacturerPredicate = cb.disjunction();
                for (String manufacturer : filters.getManufacturer()) {
                    manufacturerPredicate = cb.or(manufacturerPredicate, cb.equal(root.get("manufacturer"), manufacturer));
                }
                p = cb.and(p, manufacturerPredicate);
            }

            if (filters.getCarModelGenerationId() != null && !filters.getCarModelGenerationId().isEmpty()) {
                Predicate generationPredicate = cb.disjunction();
                for (String generation : filters.getCarModelGenerationId()) {
                    int generationInt = Integer.parseInt(generation);
                    generationPredicate = cb.or(generationPredicate, cb.equal(root.get("carModelGeneration").get("id"), generationInt));
                }
                p = cb.and(p, generationPredicate);
            }
            if (filters.getLamps() != null && !filters.getLamps().isEmpty()) {
                Predicate lampsPredicate = cb.disjunction();
                for (String lamp : filters.getLamps()) {
                    lampsPredicate = cb.or(lampsPredicate, cb.equal(root.get("lamps"),lamp));
                }
                p = cb.and(p, lampsPredicate);
            }
            if (filters.getLightType() != null && !filters.getLightType().isEmpty()) {
                Predicate lightTypePredicate = cb.disjunction();
                for (String liteType : filters.getLightType()) {
                    lightTypePredicate = cb.or(lightTypePredicate, cb.equal(root.get("lightType"),liteType));
                }
                p = cb.and(p, lightTypePredicate);
            }
            if (filters.getLeftOrRight() != null && !filters.getLeftOrRight().isEmpty()) {
                Predicate lrPredicate = cb.disjunction();
                for (String lr : filters.getLeftOrRight()) {
                    lrPredicate = cb.or(lrPredicate, cb.equal(root.get("leftOrRightSideType"),lr));
                }
                p = cb.and(p, lrPredicate);
            }
            if (filters.getFrontOrBack() != null && !filters.getFrontOrBack().isEmpty()) {
                Predicate fbPredicate = cb.disjunction();
                for (String fb : filters.getFrontOrBack()) {
                    fbPredicate = cb.or(fbPredicate, cb.equal(root.get("frontOrBackSideType"),fb));
                }
                p = cb.and(p, fbPredicate);
            }
            return p;
        };
    }
}
