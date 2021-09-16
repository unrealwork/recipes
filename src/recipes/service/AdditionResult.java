package recipes.service;

public class AdditionResult {
    private final int id;

    public static AdditionResult of(final int id) {
        return new AdditionResult(id);
    }

    private AdditionResult(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
