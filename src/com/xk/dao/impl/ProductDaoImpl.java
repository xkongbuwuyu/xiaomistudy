package com.xk.dao.impl;

import com.xk.dao.ProductDao;
import com.xk.entity.Product;
import com.xk.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> selectByState(int state) {
        //获取操作数据库对象
        List<Product> productList = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "select * from product where state=?";
            productList = qr.query(sql, new BeanListHandler<Product>(Product.class), state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> selectCname(String cname) {
        List<Product> productList = null;
        try {
            String sql = "SELECT p.* FROM category c INNER JOIN product p ON c.cid= p.cid WHERE c.name like ?";
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            productList = qr.query(sql, new BeanListHandler<Product>(Product.class), cname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product selectByPid(int pid) {
        Product product = null;
        try {
            String sql = "select * from product where pid=?";
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            product = qr.query(sql, new BeanHandler<Product>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> selectProductPage(String name, Integer state, String
            startTime, String endTime, Integer currentPageNo, Integer pageSize) {
        List<Product> productList = null;
        try {
            StringBuffer sb = new StringBuffer("SELECT p.*,(SELECT c.name FROM category c WHERE p.cid=c.cid) AS cname FROM product p WHERE 1=1");
            List<Object> parmList = new ArrayList<>();
            if (name != null && !"".equals(name)) {
                sb.append(" and name like ?");
                parmList.add("%" + name + "%");
            }
            if (state != null) {
                sb.append(" and state =?");
                parmList.add(state);
            }
            if (startTime != null && !"".equals(startTime)) {
                sb.append(" and product_date > ?");
                parmList.add(startTime);
            }
            if (endTime != null && !"".equals(endTime)) {
                sb.append(" and product_date < ?");
                parmList.add(endTime);
            }
            sb.append(" limit ?,?");
            parmList.add((currentPageNo - 1) * pageSize);
            parmList.add(pageSize);
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            if (parmList != null && parmList.size() > 0) {
                productList = qr.query(sb.toString(), new
                        BeanListHandler<Product>(Product.class), parmList.toArray());
            } else {
                productList = qr.query(sb.toString(), new
                        BeanListHandler<Product>(Product.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


    @Override
    public Integer selectProductCount(String name, Integer state, String startTime, String endTime) {
        Long count = null;
        try {
            StringBuffer sb = new StringBuffer("SELECT count(1) FROM product p WHERE 1=1");
            List<Object> parmList = new ArrayList<>();
            if (name != null && !"".equals(name)) {
                sb.append(" and name like ?");
                parmList.add("%" + name + "%");
            }
            if (state != null) {
                sb.append(" and state =?");
                parmList.add(state);
            }
            if (startTime != null && !"".equals(startTime)) {
                sb.append(" and product_date > ?");
                parmList.add(startTime);
            }
            if (endTime != null && !"".equals(endTime)) {
                sb.append(" and product_date < ?");
                parmList.add(endTime);
            }
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            if (parmList != null && parmList.size() > 0) {
                count = (Long) qr.query(sb.toString(), new ScalarHandler(), parmList.toArray());
            } else {
                count = (Long) qr.query(sb.toString(), new ScalarHandler());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(count + "");

    }

    @Override
    public int updateProduct(Product product) {
        int num = 0;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "update product set cid=?,name=?,color=?,size=?,price=?,"+
                    "description=?,state=?,version=?,product_date=? where pid=?";
            Object[] objects = {
                    product.getCid(),product.getName(),product.getColor(), product.getSize(),
                    product.getPrice(), product.getDescription(), product.getState(),
                    product.getVersion(),product.getProduct_date(),product.getPid()
            };
            num = qr.update(sql,objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public int addProduct(Product product) {
        int num = 0;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "insert into product (cid,name,color,size,price,"+
                    "description,state,version,product_date,pid)values(?,?,?,?,?,?,?,?,?,?)";
            Object[] objects = {
                    product.getCid(),product.getName(),product.getColor(), product.getSize(),
                    product.getPrice(), product.getDescription(), product.getState(),
                    product.getVersion(),product.getProduct_date(),product.getPid()
            };
            num = qr.update(sql,objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

}
