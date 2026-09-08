package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DaoDemoRunner {

	public static void main(String[] args) {
		List<CreateJobBean> beanList=CreateJobPayloadDataDAO.getCreateJobPayloadData();
		List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
		for(CreateJobBean bean:beanList) {
			CreateJobPayload payload=CreateJobBeanMapper.mapper(bean);
			payloadList.add(payload);
		}
		payloadList.forEach(i -> System.out.println(i));

	}

}
