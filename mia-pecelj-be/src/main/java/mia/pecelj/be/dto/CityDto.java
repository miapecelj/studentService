package mia.pecelj.be.dto;

public class CityDto implements MyDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long postalCode;
	private String name;

	public CityDto() {
		// TODO Auto-generated constructor stub
	}

	public CityDto(long postalCode, String name) {
		super();
		this.postalCode = postalCode;
		this.name = name;
	}

	public long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(long postalCode) {
		this.postalCode = postalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CityEntity [postalCode=" + postalCode + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (postalCode ^ (postalCode >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityDto other = (CityDto) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postalCode != other.postalCode)
			return false;
		return true;
	}

}
