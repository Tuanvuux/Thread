package com.trinh.threadversiontwo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.concurrent.Flow;

public interface FlowRepository extends JpaRepository<Flow, Integer> {
}
