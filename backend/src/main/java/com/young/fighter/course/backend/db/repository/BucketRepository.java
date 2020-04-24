package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
