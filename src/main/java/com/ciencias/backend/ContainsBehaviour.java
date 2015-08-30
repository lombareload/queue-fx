package com.ciencias.backend;

public class ContainsBehaviour implements SearchBehaviour<String> {
    @Override
    public boolean matches(String value, String queryItem) {
        return value.contains(queryItem);
    }
}
