package jsonNinjaResult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
	@SerializedName("verified")
	@Expose
	public Boolean verified;
	@SerializedName("w")
	@Expose
	public Integer w;
	@SerializedName("h")
	@Expose
	public Integer h;
	@SerializedName("ilvl")
	@Expose
	public Integer ilvl;
	@SerializedName("icon")
	@Expose
	public String icon;
	@SerializedName("league")
	@Expose
	public String league;
	@SerializedName("name")
	@Expose
	public String name;
	@SerializedName("typeLine")
	@Expose
	public String typeLine;
	@SerializedName("identified")
	@Expose
	public Boolean identified;
	@SerializedName("note")
	@Expose
	public String note;
	@SerializedName("properties")
	@Expose
	public List<Property> properties = null;
	@SerializedName("descrText")
	@Expose
	public String descrText;
	@SerializedName("frameType")
	@Expose
	public Integer frameType;
	@SerializedName("category")
	@Expose
	public Category category;
	@SerializedName("corrupted")
	@Expose
	public Boolean corrupted;
	@SerializedName("explicitMods")
	@Expose
	public List<String> explicitMods = null;
	@SerializedName("duplicated")
	@Expose
	public Boolean duplicated;
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public Integer getIlvl() {
		return ilvl;
	}
	public void setIlvl(Integer ilvl) {
		this.ilvl = ilvl;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeLine() {
		return typeLine;
	}
	public void setTypeLine(String typeLine) {
		this.typeLine = typeLine;
	}
	public Boolean getIdentified() {
		return identified;
	}
	public void setIdentified(Boolean identified) {
		this.identified = identified;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	public String getDescrText() {
		return descrText;
	}
	public void setDescrText(String descrText) {
		this.descrText = descrText;
	}
	public Integer getFrameType() {
		return frameType;
	}
	public void setFrameType(Integer frameType) {
		this.frameType = frameType;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Boolean getCorrupted() {
		return corrupted;
	}
	public void setCorrupted(Boolean corrupted) {
		this.corrupted = corrupted;
	}
	public List<String> getExplicitMods() {
		return explicitMods;
	}
	public void setExplicitMods(List<String> explicitMods) {
		this.explicitMods = explicitMods;
	}
	public Boolean getDuplicated() {
		return duplicated;
	}
	public void setDuplicated(Boolean duplicated) {
		this.duplicated = duplicated;
	}
}