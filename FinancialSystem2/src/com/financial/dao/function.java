package com.financial.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.financial.model.HttpUtils;
import com.financial.model.JsonTest;


public class function {
	
	//用户登录操作
	public String login(Map<String,String> params){
		String result="";
		HttpUtils utils=new HttpUtils();
		utils.PATH="http://localhost:8080/FinancialManage/LoginAction";
		HttpUtils.setUrl(utils.PATH);
		result=utils.sendPostMessage(params,"utf-8",utils.url);
		return result;
	}
	
	//用户注册操作
	public String register(Map<String,String> params){
		String result="";
		HttpUtils utils=new HttpUtils();
		utils.PATH="http://localhost:8080/FinancialManage/RegisterAction";
		HttpUtils.setUrl(utils.PATH);
		result=utils.sendPostMessage(params,"utf-8",utils.url);
		return result;
	}
	
	public List<Map<String,Object>> getList(Map<String,String> params){
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		HttpUtils utils=new HttpUtils();
		utils.PATH="http://localhost:8080/FinancialManage/SearchAction";
		HttpUtils.setUrl(utils.PATH);
		String list="";
		list=utils.sendPostMessage(params,"utf-8",utils.url);
		result=JsonTest.listKeyMaps("record",list);
		System.out.println(result);
		return result;
	}
	
	public Map<String,Object> getStatistic(Map<String,String> params){
		Map<String,Object> result=new HashMap<String,Object>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list=getList(params);
		int outcome=0;
		int income=0;
		for(Map m:list){
			if(m.get("divide").equals(0))
				outcome+=Integer.parseInt(m.get("num").toString());
			else if(m.get("divide").equals(1))
				income+=Integer.parseInt(m.get("num").toString());
		}
		result.put("outcome",outcome);
		result.put("income", income);
		return result;
	}
	
	public Map<String,Object> getBudget(Map<String,String> params){
		Map<String,Object> result=new HashMap<String,Object>();
		HttpUtils utils=new HttpUtils();
		utils.PATH="http://localhost:8080/FinancialManage/budget_searchAction";
		HttpUtils.setUrl(utils.PATH);
		String list="";
		list=utils.sendPostMessage(params,"utf-8",utils.url);
		//System.out.println("原始"+list);
		List<Map<String,Object>> result2=new ArrayList<Map<String,Object>>();
		result2=JsonTest.listKeyMaps("budg",list);
		//System.out.println("liebiao"+result2.toString());
		if(result2.isEmpty()){
			return result;
		}else
			return result2.get(0);
	}
	
	public String Add_record(Map<String,String> params){
		HttpUtils utils=new HttpUtils();
		utils.PATH="http://localhost:8080/FinancialManage/AddrecordAction";
		HttpUtils.setUrl(utils.PATH);
		String list="";
		list=utils.sendPostMessage(params,"utf-8",utils.url);
		return list;
	}
	
	public String Modify_record(Map<String,String> params){
		HttpUtils utils=new HttpUtils();
		utils.PATH="http://localhost:8080/FinancialManage/ModifyrecordAction";
		HttpUtils.setUrl(utils.PATH);
		String list="";
		System.out.println(params);
		list=utils.sendPostMessage(params,"utf-8",utils.url);
		return list;
	}
	
	public String Modify_budget(Map<String,String> params){
		HttpUtils utils=new HttpUtils();
		utils.PATH="http://localhost:8080/FinancialManage/BodgetAction";
		HttpUtils.setUrl(utils.PATH);
		String list="";
		//System.out.println(params);
		list=utils.sendPostMessage(params,"utf-8",utils.url);
		return list;
	}
	
	public String Delete_record(Map<String,String> params)
	{
		HttpUtils utils=new HttpUtils();
		utils.PATH="http://localhost:8080/FinancialManage/DeleteAction";
		HttpUtils.setUrl(utils.PATH);
		String list="";
		//System.out.println(params);
		list=utils.sendPostMessage(params,"utf-8",utils.url);
		return list;
	}

}
