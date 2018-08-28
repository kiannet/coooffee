package com.coffee.dao;


import com.coffee.entity.Sort;

import java.util.List;

public interface CommonDAO {
    double findPriceBySortName(String sortName);
    List<Sort> findAllSortNames();
}
