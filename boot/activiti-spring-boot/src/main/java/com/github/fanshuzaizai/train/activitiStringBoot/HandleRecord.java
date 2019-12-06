package com.github.fanshuzaizai.train.activitiStringBoot;

import lombok.Data;

import java.util.List;

/**
 * @author Jiangzy
 * @date 2019/12/5
 */
@Data
public class HandleRecord {

    private Integer nextHandlerIndex;

    private List<Long> handlers;

    public boolean haveNext() {
        return handlers.size() > nextHandlerIndex + 1;
    }

}
