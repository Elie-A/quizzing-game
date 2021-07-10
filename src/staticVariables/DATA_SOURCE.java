package staticVariables;

public enum DATA_SOURCE {
	TEXT("1- TEXT"), XML("2- XML"), DATABASE("3- DATABASE");

	private final String source;

	private DATA_SOURCE(String source) {
		this.source = source;
	}

	public String getSource() {
		return this.source;
	}

	public static void printEnum() {
		for (DATA_SOURCE source : DATA_SOURCE.values()) {
			System.out.println(source.getSource());
		}
	}
	
	public static boolean validateIfInEnum(String value) {
		for (DATA_SOURCE source : DATA_SOURCE.values()) {
			if(value.equals(source.getSource().split("- ")[0].trim())) {
				return true;
			}
		}
		return false;
	}
}