package com.davada.application.product.service;

import com.davada.application.product.persistence.ProductPersistenceAdapter;
import com.davada.application.product.service.port.ProductCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.product.entity.Product;
import com.davada.domain.product.error.ProductErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import static com.davada.domain.common.util.StringHelper.isEmpty;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ProductCrudService implements ProductCrudUseCase {

    public final ProductPersistenceAdapter productCrudPort;
    private final ProductDomainMapper productDomainMapper;

    @Override
    public IdValue createProduct(Product product) {
        String productUuid = ErpId.newId().getUuid().toString();
        //Product product = productDomainMapper.toDomain(productUuid, command);
        product.setProductUuid(productUuid);

        // 매입단가
        if (product.getPurchaseUnitPrice() != null) {
            product.getPurchaseUnitPrice().setPurchaseUnitPriceUuid(ErpId.newId().getUuid().toString());
        }
        // 판매단가
        if (product.getSellingUnitPrice() != null) {
            product.getSellingUnitPrice().setSellingUnitPriceUuid(ErpId.newId().getUuid().toString());
        }
        product.refineValues();

        productCrudPort.create(product);
        return new IdValue("productUuid", productUuid);
    }

    @Override
    public Product retrieveProduct(String productUuid) {
        return productCrudPort.retrieve(productUuid).orElseThrow(() -> new ErpRuntimeException(ProductErrorCodes.PRODUCT_1000, productUuid));
    }

    @Override
    public List<Product> retrieveAllProduct(String wholesalerUuid) {
        if (isEmpty(wholesalerUuid)) {
            throw new ErpEntityNotFoundException(wholesalerUuid);
        }
        return productCrudPort.retrieveAllProduct(wholesalerUuid);
    }

    @Override
    public BooleanValue updateProduct(String productUuid, NameValuePairs nameValuePairs) {
        return productCrudPort.retrieve(productUuid).map(product -> {
            boolean modified = productCrudPort.update(productUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new ProductRemovedEvent(product, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(productUuid));
    }

    @Override
    public BooleanValue deleteProduct(String productUuid, boolean permanent) {
        return productCrudPort.retrieve(productUuid).map(product -> {
            boolean removed = productCrudPort.delete(product, permanent);
            if (removed) {
                // domainEventPublisher.publish(new ProductRemovedEvent(product, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(productUuid));
    }
}
