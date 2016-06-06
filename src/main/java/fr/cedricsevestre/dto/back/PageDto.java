package fr.cedricsevestre.dto.back;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import fr.cedricsevestre.entity.back.Page;

public class PageDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String context;
	
	private TemplateDto model;

	public PageDto() {

	}

	public PageDto(Integer id, String name, Date dateAdd, String description, String context, TemplateDto model) {
		super(id, name, dateAdd, description);
		this.context = context;
		this.model = model;
	}

	public static PageDto from(Page page) {
		return new PageDto(page.getId(), page.getName(), page.getDateAdd(), page.getDescription(),
				page.getContext(), TemplateDto.from(page.getModel()));
	}

	public static Page to(PageDto pageDto){
		return new Page(pageDto.getId(), pageDto.getName(), pageDto.getDateAdd(), pageDto.getDescription(), pageDto.getContext(), TemplateDto.to(pageDto.getModel()));
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public TemplateDto getModel() {
		return model;
	}

	public void setModel(TemplateDto model) {
		this.model = model;
	}

	

}
