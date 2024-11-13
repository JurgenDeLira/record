package org.ms.record.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ms.record.dto.RecordRequest;
import org.ms.record.dto.RecordResponse;
import org.ms.record.entity.Record;
import org.ms.record.exception.RecordException;
import org.ms.record.repository.RecordRepository;
import org.ms.record.util.Result;

import java.util.List;

@ApplicationScoped
public class RecordService {
    @Inject
    RecordRepository recordRepository;

    public Uni<Result<List<Record>>> listAllRecords() {
        return recordRepository.listAll()
                .onItem().transform(Result::success)
                .onFailure().recoverWithItem(e -> Result.failure("Error fetching records: " + e.getMessage()));
    }

    public Uni<Result<Record>> findRecordById(Long id){
        return recordRepository.findById(id)
                .onItem().transform(element -> {
                    if (element == null) {
                        throw new RecordException("Error record not found: " + id);
                    }
                    return Result.success(element);
                })
                .onFailure().recoverWithItem(e -> Result.failure("Error fetching customer: " + e.getMessage()));
    }

    public Uni<RecordResponse> createRecord(RecordRequest record){
        return Uni.createFrom().item(record)
                .onItem().transform(r -> {
                    return new Record(record.getName(), record.getArtist(), record.getYear(), record.getGenre(), record.getFormat());
                }).onItem().transformToUni(record1 -> {
                    return recordRepository.persist(record1);
                }).onItem().transform(record2 -> {
                    RecordResponse response = new RecordResponse();
                    response.setId(record2.getId());
                    response.setName(record2.getName());
                    response.setArtist(record2.getArtist());
                    response.setYear(record2.getYear());
                    response.setGenre(record2.getGenre());
                    response.setFormat(record2.getFormat());
                    return response;
                });
    }

    public Uni<Result<Boolean>> deleteRecord(Long id) {
        return recordRepository.deleteById(id).onItem().transform(Result::success)
                .onFailure().recoverWithItem(e -> Result.failure("Error deleting record: " + e.getMessage()));
    }
