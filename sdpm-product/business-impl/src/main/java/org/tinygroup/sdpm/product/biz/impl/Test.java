package org.tinygroup.sdpm.product.biz.impl;

import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.product.dao.pojo.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/11/12.
 */
public class Test {
    public static void main(String[] args){
        List<Product> products = new ArrayList<Product>();
        for(int i =0;i<100000;i++){
            Product p = new Product();
            p.setProductId(i);
            products.add(p);
        }
        List<Integer> ids1 = new ArrayList<Integer>();
//        long t1 = System.currentTimeMillis();
//        System.out.println(t1);
        for(Product product:products){
            ids1.add(product.getProductId());
        }
//        long t2 = System.currentTimeMillis();
//        System.out.println(t2-t1);
        List<Integer> ids2 = Collections3.extractToList(products,"productId");
//        System.out.println(System.currentTimeMillis()-t2);
        long t1 = System.currentTimeMillis();
        System.out.println(t1);
        for(Product product:products){
            ids1.add(product.getProductId());
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        List<Integer> ids3 = Collections3.extractToList(products,"productId");
        System.out.println(System.currentTimeMillis()-t2);
        System.out.println(ids1.size());
        System.out.println(ids2.size());
    }
}
