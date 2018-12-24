package jsonNinjaResult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

	@SerializedName("maps")
	@Expose
	public List<Object> maps = null;

	public List<Object> getMaps() {
		return maps;
	}

	public void setMaps(List<Object> maps) {
		this.maps = maps;
	}
	
	
}