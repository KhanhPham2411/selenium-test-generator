package models;

import java.util.List;

public class Page {
	private String urlOriginal;
	private String urlRedirected;
	private String title;
	private String name;
	private List<Element> elements;
	
	/**
	 * @return the urlOriginal
	 */
	public String getUrlOriginal() {
		return urlOriginal;
	}

	/**
	 * @return the elements
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	/**
	 * @return the urlRedirected
	 */
	public String getUrlRedirected() {
		return urlRedirected;
	}

	/**
	 * @param urlRedirected the urlRedirected to set
	 */
	public void setUrlRedirected(String urlRedirected) {
		this.urlRedirected = urlRedirected;
	}

	/**
	 * @param urlOriginal the urlOriginal to set
	 */
	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
