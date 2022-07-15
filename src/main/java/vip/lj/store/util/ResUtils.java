package vip.lj.store.util;

import lombok.Data;
import vip.lj.store.util.common.JsonData;

@Data
public class ResUtils {
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
