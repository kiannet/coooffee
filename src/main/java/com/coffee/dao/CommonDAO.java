package com.coffee.dao;


import java.util.List;

public interface CommonDAO {
    double findPriceBySortName(String sortName);
    List<String> findAllSortNames();
}
