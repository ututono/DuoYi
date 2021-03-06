package com.duoyi.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoyi.dao.PhotoGeneratorMapper;
import com.duoyi.model.po.PhotoGenerator;
import com.duoyi.web.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoGeneratorMapper PhotoMapper;
	
	@Override
	public void insert(PhotoGenerator Photo) {
		PhotoMapper.insert(Photo);

	}

	@Override
	public List<String> getImgByGoodsId(int goodsid) {
		// TODO Auto-generated method stub
		return PhotoMapper.getImgByGoodsId(goodsid);
	}

}
