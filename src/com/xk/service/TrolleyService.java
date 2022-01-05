package com.xk.service;

import com.xk.entity.Trolley;

import java.util.List;

public interface TrolleyService {
    int addTrolley(Trolley trolley);
    Integer selectTrolleyCount(int uid);
    List<Trolley> selectAllTrolley(int uid);
    int deleteOrAll(String str);
    Trolley selectByTid(int tid);


}
