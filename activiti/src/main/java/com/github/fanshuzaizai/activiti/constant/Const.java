package com.github.fanshuzaizai.activiti.constant;

/**
 * @author Jiangzy
 * @date 2019/12/5
 */
public class Const {

    /**
     * 处理人
     */
    public enum HandlerType {

        /**
         * 上级
         */
        superior,

        /**
         * 指定人
         */
        point_person;
    }

    /**
     * 节点类型
     */
    public enum NodeType {

        /**
         * 或签
         */
        OR,

        /**
         * 会签
         */
        and;

    }

}
