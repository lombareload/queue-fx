package com.ciencias.backend;

public interface SearchBehaviour <T> {
    boolean matches(T value, T queryItem);
}
