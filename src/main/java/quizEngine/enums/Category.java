package quizEngine.enums;
//breaking the enum out of the QuizQuestion  entity to see of it still functions like expected.
// changed the call string to get label to show an alternate coding method
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
