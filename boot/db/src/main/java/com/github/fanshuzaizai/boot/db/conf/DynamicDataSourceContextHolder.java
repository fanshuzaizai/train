package com.github.fanshuzaizai.boot.db.conf;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<DataSourceEnum> currentDataSource = new ThreadLocal<>();


    /**
     * 获取当前使用的数据源
     *
     * @return 当前使用数据源的ID
     */
    public static DataSourceEnum get() {
        DataSourceEnum dataSourceEnum = currentDataSource.get();
        currentDataSource.remove();
        return dataSourceEnum;
    }

    /**
     * 设置当前使用的数据源
     *
     * @param value 需要设置的数据源ID
     */
    public static void set(DataSourceEnum value) {
        currentDataSource.set(value);
    }

    /**
     * 设置从从库读取数据
     * 采用简单生成随机数的方式切换不同的从库
     */
    public static void setSlave() {
//        if (RandomUtils.nextInt(0, 2) > 0) {
//            DynamicDataSourceContextHolder.set(DataSourceKey.DB_SLAVE2);
//        } else {
//            DynamicDataSourceContextHolder.set(DataSourceKey.DB_SLAVE1);
//        }
    }
}