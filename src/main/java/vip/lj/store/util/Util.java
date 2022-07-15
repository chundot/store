package vip.lj.store.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
public class Util {
    public static JsonData ok() {
        return new JsonData();
    }

    public static JsonData ok(Object data) {
        return new JsonData(data);
    }

    public static JsonData br(String msg) {
        return new JsonData(400, msg);
    }

    public static JsonData r(int state, String msg) {
        return new JsonData(state, msg);
    }

    public static JsonData r(int state, String msg, Object data) {
        return new JsonData(state, msg, data);
    }
}
