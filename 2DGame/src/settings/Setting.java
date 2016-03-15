package settings;

public class Setting {
	
	private int id;
	private String name;
	private String value;
	
	public Setting(int id, String name, String value)
	{
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getValue() { return value; }
}
