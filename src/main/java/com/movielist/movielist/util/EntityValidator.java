package com.movielist.movielist.util;

import com.movielist.movielist.genericentitydto.BaseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface EntityValidator<T extends BaseEntity> {

    List<String> validate(T entity);

}
