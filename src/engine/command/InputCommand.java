package engine.command;

import java.util.HashMap;

/**
 * Created by thomas on 4-2-17.
 */
public class InputCommand {

    public static class Type {
        public String getTypeString() {
            return typeString;
        }

        private final String typeString;

        public Type(String typeString) {
            this.typeString = typeString;
        }
    }

    public static HashMap<String, Type> TYPES = new HashMap<>();

    //TODO have initializer load strings from config file
    static {
        registerType("CONFIRM");
        registerType("CANCEL");
        registerType("UP");
        registerType("DOWN");
        registerType("LEFT");
        registerType("RIGHT");
        registerType("START");
        registerType("STRING");
    }

    private Type type;

    private String message;

    public InputCommand(Type type) {
        this.type = type;
        this.message = "";
    }

    public InputCommand(String message) {
        this.type = TYPES.get("STRING");
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

    private static void registerType(String type) {
        TYPES.put(type, new Type(type));
    }

    public static Type getTypeObject(String typeString) {
        assert TYPES.containsKey(typeString);
        return TYPES.get(typeString);
    }
}
