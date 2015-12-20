package lab0.P1;

/**
 * Lab 0 Part 1
 */
public class ABunchOfStringMethods {
	public boolean isAPicture(String fileName) {
		if (fileName.contains(".png")) {
			return true;
		}
		else {
			return false;
		}
			
	}
	
	/*
	 * Write this one yourself, use name.equalsIgnoreCase() case so bob is valid as well as BoB
	 */
	public boolean isBob(String name) {
		if (name.equalsIgnoreCase("bob")) {
			return true;
		}
		else {
			return false;
		}
	}

	public String sayHelloTo(String name) {
		return "Hello, " + name;
	}

	public boolean shouldSkipLine(String string) {
		String trimmed = string.trim();
		if (trimmed.charAt(0) == '#') {
			return true;
		}
		else {
			return false;
		}
	}
}