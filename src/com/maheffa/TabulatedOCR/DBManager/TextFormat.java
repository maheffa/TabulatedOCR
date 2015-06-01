package com.maheffa.TabulatedOCR.DBManager;


/**
 * TextFormat generated by hbm2java
 */
public class TextFormat implements java.io.Serializable {

	private Integer idTextFormat;
	private Format format;
	private String content;

	public TextFormat() {
	}

	public TextFormat(Format format, String content) {
		this.format = format;
		this.content = content;
	}

	public Integer getIdTextFormat() {
		return this.idTextFormat;
	}

	public void setIdTextFormat(Integer idTextFormat) {
		this.idTextFormat = idTextFormat;
	}

	public Format getFormat() {
		return this.format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
