package pages;

public interface IPage {
	String getTitle();
	String getUrl();
	public Boolean isRedirected();
	void start();
	void open();
}
