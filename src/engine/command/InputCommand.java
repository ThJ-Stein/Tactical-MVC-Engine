package engine.command;

/**
 * Created by thomas on 4-2-17.
 */
public class InputCommand {
    //TODO: allow user to generate own set of types
    public enum Type {
        CONFIRM,
        CANCEL,
        UP,
        DOWN,
        LEFT,
        RIGHT,
        START,
        STRING
    }

    private Type type;

    private String message;

    public InputCommand(Type type) {
        this.type = type;
        this.message = "";
    }

    public InputCommand(String message) {
        this.type = Type.STRING;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "InputCommand{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}
