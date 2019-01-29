package com.zepp.rpp.services.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zepp.rpp.domains.Notice;
import com.zepp.rpp.repositories.notice.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService{

	private final NoticeRepository noticeRepository;

	@Autowired
	public NoticeServiceImpl(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}

	@Override
	public Page<Notice> findNotices(Pageable pageable){
		return noticeRepository.findAll(pageable);
	}
	
	
}
