package mia.pecelj.be.dto;

public class TitleDto {
	private Long id;
	private String name;

	public TitleDto() {
		// TODO Auto-generated constructor stub
	}

	public TitleDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TitleDto [id=" + id + ", name=" + name + "]";
	}

}
