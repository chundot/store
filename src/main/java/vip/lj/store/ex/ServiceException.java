package vip.lj.store.ex;

import lombok.Getter;

public class ServiceException extends RuntimeException {
    @Getter
    public Detail detail;
    public ServiceException() {
    }

    public ServiceException(Detail detail) {
        super(detail.name());
        this.detail = detail;
    }

    public enum Detail {
        defaultFailure,
        usernameNotUnique,
        userNotExists,
        passwordFailed,
        disabled,
        invalidToken,
        expiredToken,
        forbidden,
        notLogged
    }
}
