package org.ms.record.util;

public class Result<T> {
    private final T value;
    private final String error;

    private Result(T value, String error){
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(String error){
        return new Result<>(null,error);
    }

    public boolean isSuccess(){
        return error == null;
    }

    public T getValue() {
        if (error != null) {
            throw new IllegalStateException("cannot get value from a failed result");
        }
        return value;
    }

    public String getError() {
        return error;
    }
}
