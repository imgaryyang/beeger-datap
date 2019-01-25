package com.xishui.beeger.datap.common.chain;

import java.util.HashMap;
import java.util.Map;

/**
 * 链式上下文
 */
public class ChainContext<T> {
    /**
     * 上下文传递的数据,每次传递都 new ChainContext()
     */
    private T contextData;
    /**
     * 可以传递额外都数据
     */
    private Map<String, Object> extendParams;
    /**
     * 是否阻断,告诉下游，你可以阻断，不需要执行你都handler内容，default 不阻断
     */
    private boolean pause = false;
    /**
     * 阻断都原因
     */
    private String pauseCause;

    public static <T> ChainContext<T> context() {
        return new ChainContext<>();
    }

    public ChainContext<T> data(T contextData) {
        this.contextData = contextData;
        return this;
    }

    public ChainContext<T> pause(boolean isPause) {
        this.pause = pause;
        return this;
    }

    public ChainContext<T> pauseCause(String pauseCause) {
        this.pauseCause = pauseCause;
        return this;
    }

    public ChainContext<T> extendParam(String key, Object value) {
        if (this.extendParams == null) {
            this.extendParams = new HashMap<>();
        }
        this.extendParams.put(key, value);
        return this;
    }

    public T getContextData() {
        return contextData;
    }

    public void setContextData(T contextData) {
        this.contextData = contextData;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public String getPauseCause() {
        return pauseCause;
    }

    public void setPauseCause(String pauseCause) {
        this.pauseCause = pauseCause;
    }
}
