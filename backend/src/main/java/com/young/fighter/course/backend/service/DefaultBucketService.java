package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.repository.BucketRepository;
import com.young.fighter.course.backend.dto.BucketView;
import com.young.fighter.course.backend.service.api.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultBucketService implements BucketService {
    private final BucketRepository bucketRepository;

    @Autowired
    public DefaultBucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    @Override
    public BucketView saveToBucket(BucketView view) {
        return null;
    }

    @Override
    public void clear(Long id) {

    }

    @Override
    public BucketView findByCustomerId(Long id) {
        return null;
    }

}
