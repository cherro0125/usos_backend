package org.fibi.usos.repository.scholarship.request;


import org.fibi.usos.model.scholarship.ScholarshipRequestDocumentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipRequestDocumentRepository extends CrudRepository<ScholarshipRequestDocumentModel,Long> {

}
