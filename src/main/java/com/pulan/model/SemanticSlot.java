package com.pulan.model;

public class SemanticSlot {
	private String slot_id;
	private String template_id;
	private String slot_name;
	private String slot_code;
	private String slot_value;
	private String required;
	private String default_value;
	private String prompt;
	private String word_class;
	private int slot_order;
    private int try_count;
	public SemanticSlot() {
		// TODO Auto-generated constructor stub
	}
	public SemanticSlot( String template_id, String slot_name, String slot_code, String slot_value,
			String required, String default_value, String prompt, String word_class, int slot_order, int try_count) {
		this.template_id = template_id;
		this.slot_name = slot_name;
		this.slot_code = slot_code;
		this.slot_value = slot_value;
		this.required = required;
		this.default_value = default_value;
		this.prompt = prompt;
		this.word_class = word_class;
		this.slot_order = slot_order;
		this.try_count = try_count;
	}
 


	public SemanticSlot(String slot_id, String template_id, String slot_name, String slot_code, String slot_value,
			String required, String default_value, String prompt, String word_class, int slot_order, int try_count) {
		this.slot_id = slot_id;
		this.template_id = template_id;
		this.slot_name = slot_name;
		this.slot_code = slot_code;
		this.slot_value = slot_value;
		this.required = required;
		this.default_value = default_value;
		this.prompt = prompt;
		this.word_class = word_class;
		this.slot_order = slot_order;
		this.try_count = try_count;
	}
   



	public int getTry_count() {
		return try_count;
	}
	public void setTry_count(int try_count) {
		this.try_count = try_count;
	}
	public String getSlot_id() {
		return slot_id;
	}
	public void setSlot_id(String slot_id) {
		this.slot_id = slot_id;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getSlot_name() {
		return slot_name;
	}
	public void setSlot_name(String slot_name) {
		this.slot_name = slot_name;
	}
	public String getSlot_code() {
		return slot_code;
	}
	public void setSlot_code(String slot_code) {
		this.slot_code = slot_code;
	}
	public String getSlot_value() {
		return slot_value;
	}
	public void setSlot_value(String slot_value) {
		this.slot_value = slot_value;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getWord_class() {
		return word_class;
	}
	public void setWord_class(String word_class) {
		this.word_class = word_class;
	}

	public int getSlot_order() {
		return slot_order;
	}
	public void setSlot_order(int slot_order) {
		this.slot_order = slot_order;
	}
	public boolean equals(Object obj) {
		if (this == obj) // 传入的对象就是它自己，如s.equals(s)；肯定是相等的；
			return true;
		if (obj == null) // 如果传入的对象是空，肯定不相等
			return false;
		if (getClass() != obj.getClass()) // 如果不是同一个类型的，如SemanticSlot类
											// 也不用比较了，肯定是不相等的
			return false;
		SemanticSlot other = (SemanticSlot) obj;
		if (slot_id == "") {
			if (other.slot_id != "")
				return false;
		} else if (!slot_id.equals(other.slot_id)) // 如果id属性相等，则相等
			return false;
		return true;
	}
}
