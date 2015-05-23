// default package
// Generated May 22, 2015 4:03:54 PM by Hibernate Tools 3.4.0.CR1

/**
 * ColumnCharacteristic generated by hbm2java
 */
public class ColumnCharacteristic implements java.io.Serializable {

	private Integer idColumnCharacteristic;
	private TableFormat tableFormat;
	private Integer position;
	private String name;
	private String type;

	public ColumnCharacteristic() {
	}

	public ColumnCharacteristic(TableFormat tableFormat, Integer position,
			String name, String type) {
		this.tableFormat = tableFormat;
		this.position = position;
		this.name = name;
		this.type = type;
	}

	public Integer getIdColumnCharacteristic() {
		return this.idColumnCharacteristic;
	}

	public void setIdColumnCharacteristic(Integer idColumnCharacteristic) {
		this.idColumnCharacteristic = idColumnCharacteristic;
	}

	public TableFormat getTableFormat() {
		return this.tableFormat;
	}

	public void setTableFormat(TableFormat tableFormat) {
		this.tableFormat = tableFormat;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{Column: position=").append(position)
                .append(", name=").append(name)
                .append(", type=").append(type)
                .append("}");
        return str.toString();
    }
}
