package vip.lj.store.util;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonData implements Serializable {
    Integer state;
    String message;
    Object data;

    public JsonData() {
        this.state = 200;
    }

    public JsonData(Integer state) {
        this.state = state;
    }

    public JsonData(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonData(Object data) {
        this.state = 200;
        this.data = data;
    }

    public JsonData(Integer state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}