import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LoxArray {
    private List<Object> elements = new ArrayList<>();
    private Map<String, LoxCallable> methods = new HashMap<>();

    LoxArray(List<Object> elements) {
        this.elements = elements;
        this.methods = createMethods(this);
    }

    private static Map<String, LoxCallable> createMethods(LoxArray loxArray) {
        Map<String, LoxCallable> methods = new HashMap<>();

        methods.put("add", new LoxCallable() {
            Token token = null;

            @Override
            public int arity() {
                return 1;
            }

            @Override
            public String toString() {
                return "<lox callable>";
            }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                return loxArray.elements.add(arguments.get(0));
            }

            @Override
            public LoxCallable withToken(Token token) {
                this.token = token;
                return this;
            }

        });

        methods.put("getAt", new LoxCallable() {
            Token token = null;

            @Override
            public int arity() {
                return 1;
            }

            @Override
            public String toString() {
                return "<lox callable>";
            }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                if (!(arguments.get(0) instanceof Double))
                    return new RuntimeError(token, "List can only be indexed by numbers");

                int index = ((Double) arguments.get(0)).intValue();
                return loxArray.elements.get(index);
            }

            @Override
            public LoxCallable withToken(Token token) {
                this.token = token;
                return this;
            }
        });

        methods.put("length", new LoxCallable() {
            Token token = null;

            @Override
            public int arity() {
                return 0;
            }

            @Override
            public String toString() {
                return "<lox callable>";
            }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                return Double.valueOf(loxArray.elements.size());
            }

            @Override
            public LoxCallable withToken(Token token) {
                this.token = token;
                return this;
            }
        });

        methods.put("removeAt", new LoxCallable() {
            Token token = null;

            @Override
            public int arity() {
                return 1;
            }

            @Override
            public String toString() {
                return "<lox callable>";
            }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                if (!(arguments.get(0) instanceof Double))
                    return new RuntimeError(token, "List can only be indexed by numbers");

                int index = ((Double) arguments.get(0)).intValue();
                return loxArray.elements.remove(index);
            }

            @Override
            public LoxCallable withToken(Token token) {
                this.token = token;
                return this;
            }
        });

        return methods;
    }

    public Object get(Token name) {
        LoxCallable method = methods.get(name.lexeme);
        if (method != null)
            return method.withToken(name);

        throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
    }
}
