package com.atguigu.spring.service;

/**
 * @author Drew
 * @create 2023-03
 */
public interface CheckoutService {

    /**
     * 结账
     * @param userId
     * @param bookIds
     */
    void checkout(Integer userId, Integer[] bookIds);
}
