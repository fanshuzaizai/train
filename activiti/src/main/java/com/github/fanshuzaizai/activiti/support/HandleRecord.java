package com.github.fanshuzaizai.activiti.support;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiangzy
 * @date 2019/12/5
 */
@Data
public class HandleRecord implements Serializable {

    private Integer nextHandlerIndex;

    private List<Long> handlers;

    public boolean haveNext() {
        return handlers.size() > nextHandlerIndex + 1;
    }

}
