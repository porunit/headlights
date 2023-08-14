package com.headlightbackend.repositories;

import com.headlightbackend.data.domain.Headlight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadlightRepository extends JpaRepository<Headlight, Long>, JpaSpecificationExecutor<Headlight> {
    @NonNull
    List<Headlight> findAll();

    @Query("SELECT DISTINCT h.manufacturer FROM Headlight h WHERE h.carModelGeneration.brandModel.brand.id = :brand")
    List<String> findManufacturersWithCarBrandId(@Param("brand") String brandId);

    @Query("SELECT DISTINCT h.leftOrRightSideType FROM Headlight h WHERE h.carModelGeneration.brandModel.brand.id = :brand")
    List<String> findLeftOrRightWithCarBrandId(@Param("brand") String brandId);

    @Query("SELECT DISTINCT h.frontOrBackSideType FROM Headlight h WHERE h.carModelGeneration.brandModel.brand.id = :brand")
    List<String> findFrontOrBackWithCarBrandId(@Param("brand") String brandId);

    @Query("SELECT DISTINCT h.lightType FROM Headlight h WHERE h.carModelGeneration.brandModel.brand.id = :brand")
    List<String> findLightTypeWithCarBrandId(@Param("brand") String brandId);

    @Query("SELECT DISTINCT h.lamps FROM Headlight h WHERE h.carModelGeneration.brandModel.brand.id = :brand")
    List<String> findLampsWithCarBrandId(@Param("brand") String brandId);

    @Query("SELECT DISTINCT h.manufacturer FROM Headlight h WHERE h.commonType.name = :brand")
    List<String> findManufacturersWithCommonType(@Param("brand") String type);

    @Query("SELECT DISTINCT h.leftOrRightSideType FROM Headlight h WHERE h.commonType.name = :brand")
    List<String> findLeftOrRightWithCommonType(@Param("brand") String type);

    @Query("SELECT DISTINCT h.frontOrBackSideType FROM Headlight h WHERE h.commonType.name = :brand")
    List<String> findFrontOrBackWithCommonType(@Param("brand") String brandId);

    @Query("SELECT DISTINCT h.lightType FROM Headlight h WHERE h.commonType.name = :brand")
    List<String> findLightTypeWithCommonType(@Param("brand") String brandId);

    @Query("SELECT DISTINCT h.lamps FROM Headlight h WHERE h.commonType = :brand")
    List<String> findLampsWithCommonType(@Param("brand") String type);

    @Query("SELECT DISTINCT h.manufacturer FROM Headlight h")
    List<String> findManufacturers();

    @Query("SELECT DISTINCT h.leftOrRightSideType FROM Headlight h")
    List<String> findLeftOrRight();

    @Query("SELECT DISTINCT h.frontOrBackSideType FROM Headlight h")
    List<String> findFrontOrBack();

    @Query("SELECT DISTINCT h.lightType FROM Headlight h")
    List<String> findLightType();

    @Query("SELECT DISTINCT h.lamps FROM Headlight h")
    List<String> findLamps();

    @NonNull
    Page<Headlight> findAll(@NonNull Pageable pageable);

    Headlight findFirstById(Long id);

    Page<Headlight> findAllByCarModelGenerationBrandModelBrandId(String brand, Pageable pageable);

    Page<Headlight> findAllByCarModelGenerationId(Long modelsGenerationId, Pageable pageable);

    Page<Headlight> findAllByCommonTypeName(String typeName, Pageable pageable);

    Headlight findFirstByName(String name);
}
