//package com.example.demo;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
//
//import java.util.Properties;
//
//public class OrderPrimaryKeyGenerator implements ShardingKeyGenerator {
//    @Override
//    public Comparable<?> generateKey() {
//        return System.currentTimeMillis();
//    }
//
//    @Override
//    public String getType() {
//        return "ORDERKEY";
//    }
//
//    @Getter
//    @Setter
//    private Properties properties = new Properties();
//}
