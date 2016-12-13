package ww.cmp.grid.pojo;

import java.io.Serializable;

public class GridPlugin implements Serializable {

	private static final long serialVersionUID = 1L;

	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
