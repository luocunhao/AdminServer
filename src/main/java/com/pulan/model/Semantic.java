package com.pulan.model;

public class Semantic {
	private String template_id;
	private String template_code;
	private String template_name;
	private String creator_name;
	private String create_time;
	private String action_url;
	private String action_success;
	private String action_failure;
	private String last_response;
	
	public Semantic() {
		// TODO Auto-generated constructor stub
	}

	public Semantic(String template_id, String template_code, String template_name, String creator_name,
			String create_time, String action_url, String action_success, String action_failure, String last_response) {
		this.template_id = template_id;
		this.template_code = template_code;
		this.template_name = template_name;
		this.creator_name = creator_name;
		this.create_time = create_time;
		this.action_url = action_url;
		this.action_success = action_success;
		this.action_failure = action_failure;
		this.last_response = last_response;
	}
	public Semantic(String template_code, String template_name, String creator_name,
			String create_time, String action_url, String action_success, String action_failure, String last_response) {
		this.template_code = template_code;
		this.template_name = template_name;
		this.creator_name = creator_name;
		this.create_time = create_time;
		this.action_url = action_url;
		this.action_success = action_success;
		this.action_failure = action_failure;
		this.last_response = last_response;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTemplate_code() {
		return template_code;
	}

	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	public String getCreator_name() {
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getAction_url() {
		return action_url;
	}

	public void setAction_url(String action_url) {
		this.action_url = action_url;
	}

	public String getAction_success() {
		return action_success;
	}

	public void setAction_success(String action_success) {
		this.action_success = action_success;
	}

	public String getAction_failure() {
		return action_failure;
	}

	public void setAction_failure(String action_failure) {
		this.action_failure = action_failure;
	}

	public String getLast_response() {
		return last_response;
	}

	public void setLast_response(String last_response) {
		this.last_response = last_response;
	}

	@Override
	public String toString() {
		return "Semantic [template_id=" + template_id + ", template_code=" + template_code + ", template_name="
				+ template_name + ", creator_name=" + creator_name + ", create_time=" + create_time + ", action_url="
				+ action_url + ", action_success=" + action_success + ", action_failure=" + action_failure
				+ ", last_response=" + last_response + "]";
	}
	
	
    
}
