package com.shop.service;
import com.shop.dao.ProductMapper;
import com.shop.domain.Product;
import com.shop.domain.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> getProductList() {

        ProductExample example = new ProductExample();
//        List<Product> list = productMapper.selectByExample(example);
// 在数据库中查询的结果集中，包含text类型的字段，一定要用selectByExampleWithBLOBs，否则会查不到对应字段的结果集
        List<Product> list = productMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }
}
