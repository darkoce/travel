package com.zepp.rpp.repositories.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.zepp.rpp.domains.Notice;

import java.util.Optional;

public interface NoticeRepository extends CrudRepository<Notice, Integer>{
	Page<Notice> findAll(Pageable pageable);
}
