package com.atguigu.spring.service;

/**
 * @author Drew
 * @create 2023-03
 */
public interface BookService {

    /**
     * 买书
     * @param userId
     * @param bookId
     */
    void buyBook(Integer userId, Integer bookId);
}
