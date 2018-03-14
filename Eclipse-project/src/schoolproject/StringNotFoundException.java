package schoolproject;

public class StringNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    String error;

    public StringNotFoundException() {
        error = null;
    }

    public StringNotFoundException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        if (error == null)
            return "StringWasNotFound";
        else
            return error;
    }
}