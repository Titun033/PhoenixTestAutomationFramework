package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DAODemoRunner {
	
	public static void main(String[] args){
		List<CreateJobBean> beanList=CreateJobPayloadDataDAO.getCreateJobPayloadData();
		List<CreateJobPayload> payloadList=new ArrayList<CreateJobPayload>();
		
		for(CreateJobBean bean:beanList) {
			payloadList.add(CreateJobBeanMapper.mapper(bean));
		}
		
		System.out.println("============================");
		
		for(CreateJobPayload payload:payloadList) {
			System.out.println(payload);
		}
	}

}
