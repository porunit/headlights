package com.headlightbackend.mappers;

import com.headlightbackend.data.domain.Brand;
import com.headlightbackend.data.domain.BrandModels;
import com.headlightbackend.data.domain.CarModelGeneration;
import com.headlightbackend.data.domain.CommonType;
import com.headlightbackend.data.domain.Headlight;
import com.headlightbackend.data.dto.HeadlightCatalogDTO;
import com.headlightbackend.data.dto.HeadlightDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-03T17:53:30+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
public class HeadlightMapperImpl implements HeadlightMapper {

    @Override
    public HeadlightDTO toDTO(Headlight headlight) {
        if ( headlight == null ) {
            return null;
        }

        HeadlightDTO.HeadlightDTOBuilder headlightDTO = HeadlightDTO.builder();

        headlightDTO.commonType( headlightCommonTypeName( headlight ) );
        headlightDTO.model( headlightCarModelGenerationBrandModelName( headlight ) );
        headlightDTO.brand( headlightCarModelGenerationBrandModelBrandName( headlight ) );
        headlightDTO.id( headlight.getId() );
        headlightDTO.articul( headlight.getArticul() );
        headlightDTO.name( headlight.getName() );
        if ( headlight.getPrice() != null ) {
            headlightDTO.price( String.valueOf( headlight.getPrice() ) );
        }
        headlightDTO.country( headlight.getCountry() );
        headlightDTO.manufacturer( headlight.getManufacturer() );
        headlightDTO.frontOrBackSideType( headlight.getFrontOrBackSideType() );
        headlightDTO.lightType( headlight.getLightType() );
        headlightDTO.lamps( headlight.getLamps() );
        headlightDTO.corrector( headlight.getCorrector() );
        headlightDTO.headlightReleasePeriod( headlight.getHeadlightReleasePeriod() );
        headlightDTO.leftOrRightSideType( headlight.getLeftOrRightSideType() );
        headlightDTO.sendableByRuPost( headlight.getSendableByRuPost() );
        headlightDTO.isOrigNumberReplaced( headlight.getIsOrigNumberReplaced() );
        headlightDTO.PTComplection( headlight.getPTComplection() );
        headlightDTO.PTMeasure( headlight.getPTMeasure() );
        headlightDTO.TUInCase( headlight.getTUInCase() );
        headlightDTO.TUMoldColour( headlight.getTUMoldColour() );
        headlightDTO.TUTuning( headlight.getTUTuning() );
        headlightDTO.imageUrl( headlight.getImageUrl() );

        return headlightDTO.build();
    }

    @Override
    public HeadlightCatalogDTO toCatalogDTO(Headlight headlight) {
        if ( headlight == null ) {
            return null;
        }

        HeadlightCatalogDTO.HeadlightCatalogDTOBuilder headlightCatalogDTO = HeadlightCatalogDTO.builder();

        headlightCatalogDTO.commonType( headlightCommonTypeName( headlight ) );
        headlightCatalogDTO.headlightReleasePeriod( headlight.getHeadlightReleasePeriod() );
        headlightCatalogDTO.model( headlightCarModelGenerationBrandModelName( headlight ) );
        headlightCatalogDTO.brand( headlightCarModelGenerationBrandModelBrandName( headlight ) );
        headlightCatalogDTO.id( headlight.getId() );
        headlightCatalogDTO.articul( headlight.getArticul() );
        headlightCatalogDTO.name( headlight.getName() );
        if ( headlight.getPrice() != null ) {
            headlightCatalogDTO.price( String.valueOf( headlight.getPrice() ) );
        }
        headlightCatalogDTO.country( headlight.getCountry() );
        headlightCatalogDTO.manufacturer( headlight.getManufacturer() );
        headlightCatalogDTO.imageUrl( headlight.getImageUrl() );

        return headlightCatalogDTO.build();
    }

    @Override
    public List<HeadlightCatalogDTO> toCatalogDTOList(List<Headlight> headlights) {
        if ( headlights == null ) {
            return null;
        }

        List<HeadlightCatalogDTO> list = new ArrayList<HeadlightCatalogDTO>( headlights.size() );
        for ( Headlight headlight : headlights ) {
            list.add( toCatalogDTO( headlight ) );
        }

        return list;
    }

    private String headlightCommonTypeName(Headlight headlight) {
        if ( headlight == null ) {
            return null;
        }
        CommonType commonType = headlight.getCommonType();
        if ( commonType == null ) {
            return null;
        }
        String name = commonType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String headlightCarModelGenerationBrandModelName(Headlight headlight) {
        if ( headlight == null ) {
            return null;
        }
        CarModelGeneration carModelGeneration = headlight.getCarModelGeneration();
        if ( carModelGeneration == null ) {
            return null;
        }
        BrandModels brandModel = carModelGeneration.getBrandModel();
        if ( brandModel == null ) {
            return null;
        }
        String name = brandModel.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String headlightCarModelGenerationBrandModelBrandName(Headlight headlight) {
        if ( headlight == null ) {
            return null;
        }
        CarModelGeneration carModelGeneration = headlight.getCarModelGeneration();
        if ( carModelGeneration == null ) {
            return null;
        }
        BrandModels brandModel = carModelGeneration.getBrandModel();
        if ( brandModel == null ) {
            return null;
        }
        Brand brand = brandModel.getBrand();
        if ( brand == null ) {
            return null;
        }
        String name = brand.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
