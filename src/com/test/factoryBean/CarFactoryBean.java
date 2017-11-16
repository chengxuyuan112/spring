package com.test.factoryBean;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {
    //返回bean对象
    @Override
    public Car getObject() throws Exception {
        return  new Car("BWM",1000000);
    }

    /**
     * 返回bean的类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
