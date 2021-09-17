package com.amr.project.util;

import javax.persistence.TypedQuery;

public class QueryResultWrapper {

    public static <T> T wrapGetSingleResult(TypedQuery<T> query) {
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}

