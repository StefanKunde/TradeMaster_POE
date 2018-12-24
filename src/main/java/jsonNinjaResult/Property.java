package jsonNinjaResult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Property {

	@SerializedName("name")
	@Expose
	public String name;
	@SerializedName("values")
	@Expose
	public List<List<String>> values = null;
	@SerializedName("displayMode")
	@Expose
	public Integer displayMode;
	@SerializedName("type")
	@Expose
	public Integer type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<List<String>> getValues() {
		return values;
	}
	public void setValues(List<List<String>> values) {
		this.values = values;
	}
	public Integer getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}