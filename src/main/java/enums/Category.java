package enums;

public enum Category {
	ALL("All"), CORE_JAVA("Core Java"), JAVA_IO("Java IO"), SQL("SQL Code"), JAVA_DATABASE("JDBC Database"), SERVLET(
			"Servlet Code"), JSP("JSP Code"), HTML(
					"HTML"), CSS("Css Stylesheet"), SPRING("Spring"), HIBERNATE("Hibernate"), ANDROID("Android");

	private String label;

	Category(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
