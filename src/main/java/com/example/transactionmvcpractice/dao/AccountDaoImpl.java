package com.example.transactionmvcpractice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.security.spec.NamedParameterSpec;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountDaoImpl implements AccountDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void transfer(Integer fromAccountId, Integer toAccountId, Integer money) {
        //轉出帳號 ->扣款
        String outSql = "UPDATE account SET balance = balance - :money WHERE id = :fromAccountId";

        Map<String, Object> outMap = new HashMap<>();
        outMap.put("money", money);
        outMap.put("fromAccountId", fromAccountId);
        namedParameterJdbcTemplate.update(outSql, outMap);

        //轉入帳號 ->入帳
        String inSql = "UPDATE account SET balance = balance + :money WHERE id = :toAccountId";

        Map<String, Object> inMap = new HashMap<>();
        inMap.put("money", money);
        inMap.put("toAccountId", toAccountId);
        namedParameterJdbcTemplate.update(inSql, inMap);
    }

    @Override
    public int check(Integer accountId) {
        String sql = "SELECT balance FROM account WHERE id = :accountId";
        Map<String, Object> map = new HashMap<>();
        map.put("accountId", accountId);
        return namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
    }

    @Override
    public int deposit(Integer accountId, Integer money) {
        String sql = "UPDATE account SET balance = balance + :money WHERE id = :accountId";
        Map<String, Object> map = new HashMap<>();
        map.put("money", money);
        map.put("accountId", accountId);
        namedParameterJdbcTemplate.update(sql, map);

        //查詢更新後的balance值 ->給前端
        String balanceSql = "SELECT balance FROM account WHERE id = :accountId";
        return namedParameterJdbcTemplate.queryForObject(balanceSql, map, Integer.class);
    }
}
