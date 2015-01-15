package com.cloudwise.smartagent.model;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.cloudwise.smartagent.component.discover.model.ServiceInfo;
import com.cloudwise.smartagent.utils.ContextHandle;
import com.cloudwise.smartagent.utils.Debug;
import com.cloudwise.smartagent.utils.StringUtil;
import com.google.common.collect.Maps;

@JsonIgnoreProperties({ "id", "mac" })
public class ServiceReportInfo {
	public static final int WINDOWS = 2;
	public static final int LINUX = 1;
	private String hostKey;
	private String mac;
	private Map<String, ServiceInfo> service_items;

	public ServiceReportInfo(String mac){
		this.mac = mac;
	}
	public Map<String, ServiceInfo> getService_items() {
		if (this.service_items == null) {
			this.service_items = Maps.newHashMap();
		}
		return this.service_items;
	}

	public void addService(String serviceId,ServiceInfo serviceInfo) {
		this.service_items.put(serviceId, serviceInfo);
	}

	public void deleteService(String serviceId) {
		this.service_items.remove(serviceId);
	}

	public String getId() {
		if (hostKey == null) {
			hostKey = StringUtil.MD5(ContextHandle.getAccountId() + mac);
		}
		Debug.logVerbose("account is is : "
				+ ContextHandle.getAccountId()
				+ " ; mac is : "
				+ mac
				+ " ;	hostKey is StringUtil.MD5(CacheCenter.getAccountId() + mac) : "
				+ hostKey);
		return hostKey;
	}

	public String getMac() {
		return mac;
	}
}