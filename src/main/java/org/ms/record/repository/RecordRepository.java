package org.ms.record.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.ms.record.entity.Record;

@ApplicationScoped
public class RecordRepository implements PanacheRepository<Record> {
}
