package jsonNinjaResult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

	@SerializedName("result")
	@Expose
	public List<Result_> result = null;

	public List<Result_> getResult() {
		return result;
	}

	public void setResult(List<Result_> result) {
		this.result = result;
	}
}