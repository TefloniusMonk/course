package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.BucketView;

public interface BucketService {
    BucketView saveToBucket(BucketView view);

    void clear(Long id);

    BucketView findByCustomerId(Long id);
}
