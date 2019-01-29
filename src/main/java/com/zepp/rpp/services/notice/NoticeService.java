package com.zepp.rpp.services.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zepp.rpp.domains.Notice;

public interface NoticeService {

	public Page<Notice> findNotices(Pageable pageable);
}
