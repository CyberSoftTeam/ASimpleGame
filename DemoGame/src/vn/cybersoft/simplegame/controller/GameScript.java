package vn.cybersoft.simplegame.controller;

import java.util.ArrayList;
import java.util.List;

import vn.cybersoft.simplegame.model.Rule;

/**
 * @author VietDung<vietdung.cybersoft@gmail.com>
 *
 */
public class GameScript {
	private String id;
	private List<Rule> listRule = new ArrayList<Rule>();
	private String idClass;
	
	public GameScript(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public List<Rule> getListRule() {
		return listRule;
	}

	public void setListRule(List<Rule> listRule) {
		this.listRule = listRule;
	}

	public String getIdClass() {
		return idClass;
	}

	public void setIdClass(String idClass) {
		this.idClass = idClass;
	}
	
}
